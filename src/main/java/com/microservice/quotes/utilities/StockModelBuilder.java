package com.microservice.quotes.utilities;

import com.microservice.quotes.models.DailyStockModel;
import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.repositories.QuoteRepository;
import com.sun.org.apache.xpath.internal.operations.Quo;

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

        List<QuoteModel> lowPriceForTheDay = quoteRepository.findLowPriceForTheDay(stockId, dateToSearch);
        List<QuoteModel> highPriceForTheDay = quoteRepository.findHighPriceForTheDay(stockId, dateToSearch);

        QuoteModel lowPriceModel = lowPriceForTheDay.get(0);
        QuoteModel highPriceModel = highPriceForTheDay.get(0);

        List<QuoteModel> listOfQuotesFoundBySymbolAndDate = quoteRepository.findAllBySymbolAndDate(stockId, dateToSearch);

        Integer volumeTradedForDay = 0;

        for (QuoteModel tempQuoteModel : listOfQuotesFoundBySymbolAndDate) {
            Integer volume = tempQuoteModel.getVolume();

            volumeTradedForDay += volume;
        }

        dailyStockModel.setLowPriceForDay(lowPriceModel.getPrice());
        dailyStockModel.setHighPriceForDay(highPriceModel.getPrice());
        dailyStockModel.setSearchDate(dateToSearch);
        dailyStockModel.setClosingPriceForDay(closingPriceModel.getPrice());
        dailyStockModel.setOpeningPriceForDay(openingPriceModel.getPrice());
        dailyStockModel.setTotalVolumeTradedForDay(volumeTradedForDay);

        return dailyStockModel;
    }
}
