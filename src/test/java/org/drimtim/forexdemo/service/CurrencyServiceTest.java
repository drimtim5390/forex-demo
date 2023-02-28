package org.drimtim.forexdemo.service;

import org.drimtim.forexdemo.controller.vm.TimeSeriesRequestVM;
import org.drimtim.forexdemo.service.dto.TimeSeriesResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.drimtim.forexdemo.enumeration.Currency.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CurrencyServiceTest {

    private static TimeSeriesRequestVM request;

    @Autowired
    private CurrencyService currencyService;

    @BeforeAll
    static void setUp() {
        request = new TimeSeriesRequestVM(
                LocalDate.now().minusDays(20),
                LocalDate.now(),
                USD.toString(),
                List.of(EUR.toString(), RUB.toString(), UZS.toString()),
                1D
        );
    }

    @Test
    void getRatesObject() {
        currencyService
                .getRates(request)
                .subscribe(this::assertions);
    }

    @Test
    void getRatesParameterized() {
        currencyService
                .getRates(request.getStartDate(), request.getEndDate(), request.getBaseCurrency(), request.getTargetCurrencies(), request.getAmount())
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
}