package com.microservice.quotes.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.quotes.models.QuoteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MicroServiceConnector {


    public MicroServiceConnector() { }

    public List<JsonNode> getTickerSymbolAndCompanyName(String stockId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

//        URI uri = UriComponentsBuilder.fromUriString("//stocks-app/stockSymbolKey")
//                .queryParam("stockId", stockId)
//                .build()
//                .toUri();

//        Greeting greeting = rest.getForObject(uri, Greeting.class);

//        QuoteModel returnedObject = restTemplate.getForObject(uri, QuoteModel.class);

        String cloudFoundryUrl = "stocks-symbol-app-proud-bushbuck.cfapps.io/" + stockId;
        String stockKeyUrl = "http://localhost:8081/stockSymbolKey/" + stockId;

        List<JsonNode> companyInfoList = new ArrayList<>();

        ResponseEntity<String> response = restTemplate.getForEntity(stockKeyUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode companySymbol = root.path("symbol");
        JsonNode companyName = root.path("companyName");

        companyInfoList.add(companySymbol);
        companyInfoList.add(companyName);

        return companyInfoList;
    }
}
