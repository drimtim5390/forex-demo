package org.drimtim.forexdemo.controller;

import org.drimtim.forexdemo.controller.vm.TimeSeriesRequestVM;
import org.drimtim.forexdemo.enumeration.Currency;
import org.drimtim.forexdemo.service.dto.TimeSeriesResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.drimtim.forexdemo.enumeration.Currency.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    private static TimeSeriesRequestVM request;

    private static TimeSeriesRequestVM requestShort;

    private static WebClient webClient;

    @BeforeAll
    static void setUp() {
        webClient = WebClient.create();
        request = new TimeSeriesRequestVM(
                LocalDate.now().minusDays(10),
                LocalDate.now(),
                USD.toString(),
                List.of(EUR.toString(), RUB.toString(), UZS.toString()),
                1D
        );

        requestShort = new TimeSeriesRequestVM(
                LocalDate.now().minusDays(10),
                LocalDate.now()
        );
    }

    @Test
    void testCurrenciesGetEndpoint() {
        webClient.get()
                .uri("http://localhost:" + port + "/api/currencies?startDate={startDate}&endDate={endDate}&baseCurrency={baseCurrency}&targetCurrencies={targetCurrencies}&amount={amount}",
                        request.getStartDate(), request.getEndDate(), request.getBaseCurrency(), String.join(",", request.getTargetCurrencies()), request.getAmount())
                .retrieve().bodyToMono(TimeSeriesResponseDTO.class)
                .subscribe(this::assertions);
    }

    @Test
    void testCurrenciesPostEndpoint() {
        webClient.post()
                .uri("http://localhost:" + port + "/api/currencies")
                .body(BodyInserters.fromValue(request))
                .retrieve().bodyToMono(TimeSeriesResponseDTO.class)
                .subscribe(this::assertions);
    }

    private void assertions(TimeSeriesResponseDTO response) {
        assertNotNull(response);
        assertEquals(response.getStartDate(), request.getStartDate());
        assertEquals(response.getEndDate(), request.getEndDate());
        assertEquals(response.getBaseCurrency(), request.getBaseCurrency());
        assertEquals(response.getTargetCurrencies(), request.getTargetCurrencies());
        assertEquals(response.getAmount(), request.getAmount());
        assertNotNull(response.getRates());
    }


    @Test
    void testCurrenciesGetEndpointShort() {
        webClient.get()
                .uri("http://localhost:" + port + "/api/currencies?startDate={startDate}&endDate={endDate}",
                        request.getStartDate(), request.getEndDate())
                .retrieve().bodyToMono(TimeSeriesResponseDTO.class)
                .subscribe(this::assertionsShort);
    }

    @Test
    void testCurrenciesPostEndpointShort() {
        webClient.post()
                .uri("http://localhost:" + port + "/api/currencies")
                .body(BodyInserters.fromValue(requestShort))
                .retrieve().bodyToMono(TimeSeriesResponseDTO.class)
                .subscribe(this::assertionsShort);
    }

    private void assertionsShort(TimeSeriesResponseDTO response) {
        assertNotNull(response);
        assertEquals(response.getStartDate(), requestShort.getStartDate());
        assertEquals(response.getEndDate(), requestShort.getEndDate());
        assertEquals(response.getBaseCurrency(), Currency.USD.toString());
        assertThat(response.getTargetCurrencies().size()).isGreaterThan(0);
        assertEquals(response.getAmount(), 1D);
        assertNotNull(response.getRates());
    }
}