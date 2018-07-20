package com.microservice.quotes.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.repositories.QuoteRepository;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/stockQuotes"})
public class QuoteController {

    private final QuoteRepository quoteRepository;
    private EurekaClient discoveryClient;

    @Autowired
    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping(value = {"/getAllQuotes"}, produces = {"application/json"})
    @ResponseBody
    public List<QuoteModel> showAllStocks() {

        return quoteRepository.findAll();
    }

    @GetMapping(
            value = {"/{stockId}"},
            produces = {"application/json"})
    @ResponseBody
    public String findSymbolById(
            @PathVariable(name = "stockId") Long stockId
    ) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String stockKeyUrl = "http://localhost:8081/stockSymbolKey/" + stockId;

        ResponseEntity<String> response = restTemplate.getForEntity(stockKeyUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode companySymbol = root.path("symbol");



        return String.valueOf(companySymbol);
    }

//    @GetMapping(
//            value = {"/{stockId}"},
//            produces = {"application/json"})
//    @ResponseBody
//    public List<QuoteModel> findStockById(
//            @Valid
//            @PathVariable(value = "stockId") Long stockId) {
//    }
}
