package com.microservice.quotes.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MicroServiceConnector {

    private ObjectMapper mapper = new ObjectMapper();

    public MicroServiceConnector() { }

    public List<String> getTickerSymbolAndCompanyName(String stockSymbolToFind, RestTemplate restTemplate) throws IOException {

        List<String> companyInfoList = new ArrayList<>();
        String eurekaInstanceOfStocksApp = "http://stocks-app/stockSymbolKey/" + stockSymbolToFind;

        ResponseEntity<String> response = restTemplate.getForEntity(eurekaInstanceOfStocksApp, String.class);

        JsonNode root = mapper.readTree(response.getBody());
        String stockId = root.path("id").textValue();
        String stockSymbol = root.path("symbol").textValue();
        String companyName = root.path("companyName").textValue();

        companyInfoList.add(stockId);
        companyInfoList.add(stockSymbol);
        companyInfoList.add(companyName);

        return companyInfoList;
    }
}
