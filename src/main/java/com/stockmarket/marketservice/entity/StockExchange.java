package com.stockmarket.marketservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class StockExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String stockExchange;
    private String brief;
    private String contactAddress;
    private String remarks;

    public StockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public StockExchange(long id, String stockExchange) {
        this.id = id;
        this.stockExchange = stockExchange;
    }
}
