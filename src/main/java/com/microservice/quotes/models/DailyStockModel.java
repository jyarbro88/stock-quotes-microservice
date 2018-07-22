package com.microservice.quotes.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class DailyStockModel {

    @Id
    @GeneratedValue
    private Long id;
    private String tickerSymbol;
    private String companyName;
    private Integer totalVolumeTradedForDay;
    private Double highPriceForDay;
    private Double lowPriceForDay;
    private Double openingPriceForDay;
    private Double closingPriceForDay;

    @OneToMany
    private List<QuoteModel> quoteModelList;

    public DailyStockModel() {
    }

    public DailyStockModel(String tickerSymbol, Integer totalVolumeTradedForDay, Double highPriceForDay, Double lowPriceForDay, Double openingPriceForDay, Double closingPriceForDay) {
        this.tickerSymbol = tickerSymbol;
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

    public List<QuoteModel> getQuoteModelList() {
        return quoteModelList;
    }

    public void setQuoteModelList(List<QuoteModel> quoteModelList) {
        this.quoteModelList = quoteModelList;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    @Override
    public String toString() {
        return
                "Daily Stats for Ticker Symbol: " + getTickerSymbol() +
                "\n\n      Name of Company: " + getCompanyName() +
                        "\n------------------------------------" +
                        "\nHigh Price For The Day:   $" + getHighPriceForDay() +
                        "\nLow Price For The Day:    $" + getLowPriceForDay() +
                        "\n------------------------------------" +
                        "\nTotal Volume Traded:      " + getTotalVolumeTradedForDay() +
                        "\n------------------------------------" +
                        "\nOpening Price:            $" + getOpeningPriceForDay() +
                        "\nClosing Price:            $" + getClosingPriceForDay();
    }
}
