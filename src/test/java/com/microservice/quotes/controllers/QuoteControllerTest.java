package com.microservice.quotes.controllers;

import com.microservice.quotes.models.QuoteModel;
import com.microservice.quotes.utilities.StockModelBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

public class QuoteControllerTest {

    @InjectMocks
    QuoteModel givenQuoteModel;

    @InjectMocks
    StockModelBuilder stockModelBuilder;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(givenQuoteModel).build();
    }

    @Configuration
    static class Config {
        @Bean
        public QuoteController getStockController() {
            return new QuoteController();
        }
    }

    @Test
    public void findSymbolById() {


    }

    @Test
    public void testFindSymbolByIdAndDate() {


    }
}