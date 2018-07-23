package com.microservice.quotes;

import com.microservice.quotes.repositories.QuoteRepository;
import com.microservice.quotes.utilities.JsonReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class QuotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(QuoteRepository quoteRepository){

        JsonReader jsonReader = new JsonReader();
        return args -> jsonReader.readJsonFile(quoteRepository);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
