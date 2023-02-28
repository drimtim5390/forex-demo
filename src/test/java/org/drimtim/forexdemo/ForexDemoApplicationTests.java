package org.drimtim.forexdemo;

import org.drimtim.forexdemo.controller.CurrencyController;
import org.drimtim.forexdemo.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ForexDemoApplicationTests {

	@Autowired
	private CurrencyController currencyController;

	@Autowired
	private CurrencyService currencyService;

	@Test
	void contextLoads() {
		assertThat(currencyController).isNotNull();
		assertThat(currencyService).isNotNull();
	}
}
