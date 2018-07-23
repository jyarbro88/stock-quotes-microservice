package com.microservice.quotes.utilities;

import com.microservice.quotes.models.DailyStockModel;
import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.repositories.QuoteRepository;

import java.util.Date;
import java.util.List;

public class StockModelBuilder {

    private final QuoteRepository quoteRepository;

    public StockModelBuilder(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public DailyStockModel buildDailyStockModel(String stockId, Date dateToSearch) {

        DailyStockModel dailyStockModel = new DailyStockModel();

        QuoteModel closingPriceModel = quoteRepository.findClosingPrice(stockId, dateToSearch);
        QuoteModel openingPriceModel = quoteRepository.findOpeningPrice(stockId, dateToSearch);

        List<QuoteModel> listOfQuotesFoundBySymbolAndDate = quoteRepository.findAllBySymbolAndDate(stockId, dateToSearch);

        Double highPrice = 0.00;
        Double lowPrice = 1000000.00;
        Integer volumeTradedForDay = 0;

        for (QuoteModel tempQuoteModel : listOfQuotesFoundBySymbolAndDate) {
            Double price = tempQuoteModel.getPrice();
            Integer volume = tempQuoteModel.getVolume();

            if (price > highPrice) {
                highPrice = price;
            }
            if (price < lowPrice) {
                lowPrice = price;
            }
            volumeTradedForDay += volume;
        }

        dailyStockModel.setHighPriceForDay(highPrice);
        dailyStockModel.setLowPriceForDay(lowPrice);
        dailyStockModel.setTotalVolumeTradedForDay(volumeTradedForDay);
        dailyStockModel.setClosingPriceForDay(closingPriceModel.getPrice());
        dailyStockModel.setOpeningPriceForDay(openingPriceModel.getPrice());

        return dailyStockModel;
    }
}
