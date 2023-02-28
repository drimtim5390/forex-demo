package org.drimtim.forexdemo.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.drimtim.forexdemo.controller.vm.TimeSeriesRequestVM;
import org.drimtim.forexdemo.service.dto.TimeSeriesResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyService {

    private final WebClient webClient;

    private final Cache<TimeSeriesRequestVM, TimeSeriesResponseDTO> timeSeriesCache;

    public CurrencyService(WebClient.Builder webClientBuilder, @Value("${exchange.rates.api.host}") String apiHost) {
        webClient = webClientBuilder.baseUrl(apiHost).build();
        timeSeriesCache = Caffeine.newBuilder().maximumSize(1000).expireAfterWrite(Duration.ofHours(1)).build();
    }

    public Mono<TimeSeriesResponseDTO> getRates(TimeSeriesRequestVM request) {
        TimeSeriesResponseDTO result = timeSeriesCache.getIfPresent(request);
        if (result != null) {
            return Mono.just(result);
        }
        return fetchRates(request);
    }

    public Mono<TimeSeriesResponseDTO> getRates(LocalDate startDate, LocalDate endDate, String baseCurrency, List<String> targetCurrencies, Double amount) {
        TimeSeriesRequestVM request = new TimeSeriesRequestVM(startDate, endDate, baseCurrency, targetCurrencies, amount);
        return getRates(request);
    }

    private Mono<TimeSeriesResponseDTO> fetchRates(TimeSeriesRequestVM request) {
        return webClient.get().uri("/timeseries?start_date={starDate}&end_date={endDate}&base={baseCurrency}&symbols={targetCurrencies}&amount={amount}",
                        request.getStartDate(), request.getEndDate(), request.getBaseCurrency(), String.join(",", request.getTargetCurrencies()), request.getAmount())
                .retrieve().bodyToMono(TimeSeriesResponseDTO.class)
                .map(timeSeriesResponseDTO -> {
                    timeSeriesResponseDTO.setTargetCurrencies(request.getTargetCurrencies());
                    timeSeriesResponseDTO.setAmount(request.getAmount());
                    return timeSeriesResponseDTO;
                })
                .doOnNext(timeSeriesResponseDTO -> {
                    timeSeriesCache.put(request, timeSeriesResponseDTO);
                });
    }
}
