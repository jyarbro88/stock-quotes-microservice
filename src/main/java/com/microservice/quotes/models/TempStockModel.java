package com.microservice.quotes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TempStockModel {

    private String id;
    private String symbol;
    private String companyName;
}
