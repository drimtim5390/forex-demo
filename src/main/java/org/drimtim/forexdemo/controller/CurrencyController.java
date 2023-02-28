package org.drimtim.forexdemo.controller;

import jakarta.annotation.Nullable;
import org.drimtim.forexdemo.controller.vm.TimeSeriesRequestVM;
import org.drimtim.forexdemo.service.CurrencyService;
import org.drimtim.forexdemo.service.dto.TimeSeriesResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public Mono<TimeSeriesResponseDTO> getCurrencies(
            @NotNull @RequestParam LocalDate startDate,
            @NotNull @RequestParam LocalDate endDate,
            @Nullable @RequestParam String baseCurrency,
            @Nullable @RequestParam List<String> targetCurrencies,
            @Nullable @RequestParam Double amount) {
        log.debug("REST request to get currency rates with params: startDate={}, endDate={}, baseCurrency={}, targetCurrencies={}, amount={}", startDate, endDate, baseCurrency, targetCurrencies, amount);
        return currencyService.getRates(startDate, endDate, baseCurrency, targetCurrencies, amount);
    }

    @PostMapping
    public Mono<TimeSeriesResponseDTO> getCurrencies(@Valid @RequestBody TimeSeriesRequestVM request) {
        log.debug("REST request to get currency rates: {}", request);
        return currencyService.getRates(request);
    }
}
