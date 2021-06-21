package com.stockmarket.marketservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String companyCode;
    private String stockExchange;
    private Double currentPrice;
    private Date date;
    private String time;

}
