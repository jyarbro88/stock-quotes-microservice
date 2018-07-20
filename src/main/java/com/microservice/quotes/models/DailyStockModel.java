package com.microservice.quotes.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DailyStockModel {

    @Id
    @GeneratedValue
    private Long id;
    private String symbol;
    private Integer totalVolumeTradedForDay;
    private Double highPriceForDay;
    private Double lowPriceForDay;
    private Double openingPriceForDay;
    private Double closingPriceForDay;

    public DailyStockModel() {
    }

    public DailyStockModel(String symbol, Integer totalVolumeTradedForDay, Double highPriceForDay, Double lowPriceForDay, Double openingPriceForDay, Double closingPriceForDay) {
        this.symbol = symbol;
        this.totalVolumeTradedForDay = totalVolumeTradedForDay;
        this.highPriceForDay = highPriceForDay;
        this.lowPriceForDay = lowPriceForDay;
        this.openingPriceForDay = openingPriceForDay;
        this.closingPriceForDay = closingPriceForDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getTotalVolumeTradedForDay() {
        return totalVolumeTradedForDay;
    }

    public void setTotalVolumeTradedForDay(Integer totalVolumeTradedForDay) {
        this.totalVolumeTradedForDay = totalVolumeTradedForDay;
    }

    public Double getHighPriceForDay() {
        return highPriceForDay;
    }

    public void setHighPriceForDay(Double highPriceForDay) {
        this.highPriceForDay = highPriceForDay;
    }

    public Double getLowPriceForDay() {
        return lowPriceForDay;
    }

    public void setLowPriceForDay(Double lowPriceForDay) {
        this.lowPriceForDay = lowPriceForDay;
    }

    public Double getOpeningPriceForDay() {
        return openingPriceForDay;
    }

    public void setOpeningPriceForDay(Double openingPriceForDay) {
        this.openingPriceForDay = openingPriceForDay;
    }

    public Double getClosingPriceForDay() {
        return closingPriceForDay;
    }

    public void setClosingPriceForDay(Double closingPriceForDay) {
        this.closingPriceForDay = closingPriceForDay;
    }
}
