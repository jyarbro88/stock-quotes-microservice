package com.microservice.quotes.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "stock_data")
public class QuoteModel {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "price")
    private Double price;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "trading_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

}
