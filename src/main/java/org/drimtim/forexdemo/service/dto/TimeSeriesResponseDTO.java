package org.drimtim.forexdemo.service.dto;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

@JsonPropertyOrder({"startDate", "endDate", "baseCurrency", "targetCurrencies", "amount", "rates"})
public class TimeSeriesResponseDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private String baseCurrency;

    private List<String> targetCurrencies;

    private Double amount;

    private TreeMap<String, HashMap<String, Double>> rates;

    @JsonGetter("baseCurrency")
    public String getBaseCurrency() {
        return baseCurrency;
    }

    @JsonSetter("base")
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public List<String> getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(List<String> targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonGetter("startDate")
    public LocalDate getStartDate() {
        return startDate;
    }

    @JsonSetter("start_date")
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @JsonGetter("endDate")
    public LocalDate getEndDate() {
        return endDate;
    }

    @JsonSetter("end_date")
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TreeMap<String, HashMap<String, Double>> getRates() {
        return rates;
    }

    public void setRates(TreeMap<String, HashMap<String, Double>> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeriesResponseDTO that = (TimeSeriesResponseDTO) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(baseCurrency, that.baseCurrency) && Objects.equals(targetCurrencies, that.targetCurrencies) && Objects.equals(amount, that.amount) && Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, baseCurrency, targetCurrencies, amount, rates);
    }

    @Override
    public String toString() {
        return "TimeSeriesResponseDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrencies=" + targetCurrencies +
                ", amount=" + amount +
                ", rates=" + rates +
                '}';
    }
}
