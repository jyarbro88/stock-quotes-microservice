package com.microservice.quotes.controllers;

import com.microservice.quotes.models.DailyStockModel;
import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.models.TempStockModel;
import com.microservice.quotes.repositories.QuoteRepository;
import com.microservice.quotes.utilities.MicroServiceConnector;
import com.microservice.quotes.utilities.StockModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = {"/dailyStockQuotes"})
public class QuoteController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping(
            value = {"/allStocks"},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<QuoteModel> displayAll(){

        return quoteRepository.findAll();
    }

    @GetMapping(
            value = {"/{stockSymbolToFind}/{dateToSearch}"},
            produces = {"application/json"} )
    @ResponseBody
    public String findSymbolByIdAndDate(
            @PathVariable(name = "stockSymbolToFind") String stockSymbolToFind,
            @PathVariable("dateToSearch")
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateToSearch
            ) throws IOException {

        String symbolToFind = stockSymbolToFind.toUpperCase();
        MicroServiceConnector microServiceConnector = new MicroServiceConnector();
        StockModelBuilder stockModelBuilder = new StockModelBuilder(quoteRepository);

        TempStockModel foundStockModel = microServiceConnector.getTickerSymbolAndCompanyName(symbolToFind, restTemplate);

        DailyStockModel dailyStockModel = stockModelBuilder.buildDailyStockModel(foundStockModel.getId(), dateToSearch);
        dailyStockModel.setTickerSymbol(foundStockModel.getSymbol());
        dailyStockModel.setCompanyName(foundStockModel.getCompanyName());

        return dailyStockModel.toString();
    }
}
