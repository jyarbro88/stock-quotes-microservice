package com.microservice.quotes.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.microservice.quotes.models.DailyStockModel;
import com.microservice.quotes.repositories.QuoteRepository;
import com.microservice.quotes.utilities.MicroServiceConnector;
import com.microservice.quotes.utilities.StockModelBuilder;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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

    private EurekaClient discoveryClient;

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping(
            value = {"/{stockId}/{dateToSearch}"},
            produces = {"application/json"} )
    @ResponseBody
    public String findSymbolByIdAndDate(
            @PathVariable(name = "stockId") String stockId,
            @PathVariable("dateToSearch")
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateToSearch
            ) throws IOException {

        MicroServiceConnector microServiceConnector = new MicroServiceConnector();
        StockModelBuilder stockModelBuilder = new StockModelBuilder(quoteRepository);

        List<JsonNode> tickerSymbolAndCompanyName = microServiceConnector.getTickerSymbolAndCompanyName(stockId, restTemplate);

        JsonNode companySymbol = tickerSymbolAndCompanyName.get(0);
        JsonNode companyName = tickerSymbolAndCompanyName.get(1);

        DailyStockModel dailyStockModel = stockModelBuilder.buildDailyStockModel(stockId, dateToSearch);
        dailyStockModel.setTickerSymbol(String.valueOf(companySymbol));
        dailyStockModel.setCompanyName(String.valueOf(companyName));
        return dailyStockModel.toString();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
