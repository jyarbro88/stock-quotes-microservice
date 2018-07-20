package com.microservice.quotes.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.repositories.QuoteRepository;

import java.net.URL;

public class JsonReader {

    public void readJsonFile(QuoteRepository quoteRepository) throws Exception {

        String stockUrl = "https://bootcamp-training-files.cfapps.io/week4/week4_stocks.json";
        ObjectMapper mapper = new ObjectMapper();

        QuoteModel[] quoteModelList = mapper.readValue(new URL(stockUrl), QuoteModel[].class);

        int counter = 0;
        while (counter < (quoteModelList.length - 1)) {
            counter++;
            try {
                quoteRepository.save(quoteModelList[counter]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
