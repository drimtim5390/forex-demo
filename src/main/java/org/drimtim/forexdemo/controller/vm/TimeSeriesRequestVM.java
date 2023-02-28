package org.drimtim.forexdemo.controller.vm;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TimeSeriesRequestVM {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private String baseCurrency;

    private List<String> targetCurrencies;

    private Double amount;

    public TimeSeriesRequestVM(LocalDate startDate, LocalDate endDate, String base, List<String> targetCurrencies, Double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.baseCurrency = base;
        this.targetCurrencies = targetCurrencies;
        this.amount = amount;
    }

    public TimeSeriesRequestVM(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.baseCurrency = "USD";
        this.amount = 1D;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeriesRequestVM that = (TimeSeriesRequestVM) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(baseCurrency, that.baseCurrency) && Objects.equals(targetCurrencies, that.targetCurrencies) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, baseCurrency, targetCurrencies, amount);
    }

    @Override
    public String toString() {
        return "TimeSeriesRequestVM{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrencies=" + targetCurrencies +
                ", amount=" + amount +
                '}';
    }
}
