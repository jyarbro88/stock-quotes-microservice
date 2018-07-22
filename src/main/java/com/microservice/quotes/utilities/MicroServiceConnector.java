package com.microservice.quotes.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MicroServiceConnector {

    private ObjectMapper mapper = new ObjectMapper();

    public MicroServiceConnector() { }

    public List<JsonNode> getTickerSymbolAndCompanyName(String stockId, RestTemplate restTemplate) throws IOException {

        List<JsonNode> companyInfoList = new ArrayList<>();
        String eurekaInstanceOfStocksApp = "http://stocks-app/stockSymbolKey/" + stockId;

        ResponseEntity<String> response = restTemplate.getForEntity(eurekaInstanceOfStocksApp, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode companySymbol = root.path("symbol");
        JsonNode companyName = root.path("companyName");

        companyInfoList.add(companySymbol);
        companyInfoList.add(companyName);

        return companyInfoList;
    }
}
