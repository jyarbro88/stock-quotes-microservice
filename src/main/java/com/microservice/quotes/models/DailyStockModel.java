package com.microservice.quotes.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

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

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public DailyStockModel() {
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
                        "\nHigh Price For The Day:     $" + df2.format(getHighPriceForDay()) +
                        "\nLow Price For The Day:      $" + String.format( "%.2f", getLowPriceForDay()) +
                        "\n------------------------------------" +
                        "\nTotal Volume Traded:        " + NumberFormat.getNumberInstance(Locale.US).format(getTotalVolumeTradedForDay()) +
                        "\n------------------------------------" +
                        "\nOpening Price:              $" + df2.format(getOpeningPriceForDay()) +
                        "\nClosing Price:              $" + df2.format(getClosingPriceForDay());
    }
}
