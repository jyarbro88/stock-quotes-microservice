package com.microservice.quotes.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.quotes.models.DailyStockModel;
import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.repositories.QuoteRepository;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = {"/dailyStockQuotes"})
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
            value = {"/lookUpSymbol/{stockId}"},
            produces = {"application/json"})
    @ResponseBody
    public String findSymbolById(
            @PathVariable(name = "stockId") String stockId
    ) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String stockKeyUrl = "http://localhost:8081/stockSymbolKey/" + stockId;

        ResponseEntity<String> response = restTemplate.getForEntity(stockKeyUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode companySymbol = root.path("symbol");
        return String.valueOf(companySymbol);
    }

    @GetMapping(
            value = {"/{stockId}/{dateToSearch}"},
            produces = {"application/json"} )
    @ResponseBody
    public String findSymbolByIdAndDate(
            @PathVariable(name = "stockId") String stockId,
            @PathVariable("dateToSearch")
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateToSearch
            ) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String stockKeyUrl = "http://localhost:8081/stockSymbolKey/" + stockId;

        ResponseEntity<String> response = restTemplate.getForEntity(stockKeyUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode companySymbol = root.path("symbol");

        DailyStockModel dailyStockModel = getDailyStockModel(stockId, dateToSearch);

        return dailyStockModel.toString();
    }



    private DailyStockModel getDailyStockModel(String stockId, Date dateToSearch) {
        List<QuoteModel> listOfLowestPrices = quoteRepository.findLowestPrice(stockId, dateToSearch);
        List<QuoteModel> listOfHighestPrices = quoteRepository.findHighestPrice(stockId, dateToSearch);
        Integer totalVolume = quoteRepository.findTotalVolume(stockId, dateToSearch);

        DailyStockModel dailyStockModel = new DailyStockModel();

        QuoteModel highPriceModel = listOfHighestPrices.get(0);
        QuoteModel lowPriceModel = listOfLowestPrices.get(0);
//        QuoteModel totalVolumeModel = totalVolume.get(0);
//        dailyStockModel.setTotalVolumeTradedForDay(totalVolumeModel.getVolume());
        dailyStockModel.setTotalVolumeTradedForDay(totalVolume);
        dailyStockModel.setLowPriceForDay(lowPriceModel.getPrice());
        dailyStockModel.setHighPriceForDay(highPriceModel.getPrice());
        return dailyStockModel;
    }
}
