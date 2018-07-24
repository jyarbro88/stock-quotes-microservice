package com.microservice.quotes.utilities;

import com.microservice.quotes.models.TempStockModel;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class MicroServiceConnector {

    public TempStockModel getTickerSymbolAndCompanyName(String stockSymbolToFind, RestTemplate restTemplate) throws IOException {

        String eurekaInstanceOfStocksApp = "http://stocks-app/stockSymbolKey/" + stockSymbolToFind;

        return restTemplate.getForObject(eurekaInstanceOfStocksApp, TempStockModel.class);
    }
}
