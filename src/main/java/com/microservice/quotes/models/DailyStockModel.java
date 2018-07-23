package com.microservice.quotes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Locale;

@Getter @Setter
@NoArgsConstructor
public class DailyStockModel {

    private String tickerSymbol;
    private String companyName;
    private Integer totalVolumeTradedForDay;
    private Double highPriceForDay;
    private Double lowPriceForDay;
    private Double openingPriceForDay;
    private Double closingPriceForDay;

    @Override
    public String toString() {
        return
                "Daily Stats for Ticker Symbol: " + getTickerSymbol() +
                        "\n\n      Name of Company: " + getCompanyName() +
                        "\n------------------------------------" +
                        "\nHigh Price For The Day:     $" + formatDecimals(getHighPriceForDay()) +
                        "\nLow Price For The Day:      $" + String.format("%.2f", getLowPriceForDay()) +
                        "\n------------------------------------" +
                        "\nTotal Volume Traded:        " + NumberFormat.getNumberInstance(Locale.US).format(getTotalVolumeTradedForDay()) +
                        "\n------------------------------------" +
                        "\nOpening Price:              $" + formatDecimals(getOpeningPriceForDay()) +
                        "\nClosing Price:              $" + formatDecimals(getClosingPriceForDay());
    }

    private String formatDecimals(Double doubleToFormat) {

        return String.format("%.2f", doubleToFormat);
    }
}
