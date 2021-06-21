package com.stockmarket.marketservice.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Company {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String companyName;
    private float companyTurnover;
    private String companyCEO;
    private String boardOfDirectors;
    @ManyToMany
    private List<StockExchange> listedExchanges = new ArrayList<>();
    @ManyToOne
    private Sector sector ;
    private String companyDesc;

    public Company(String companyName, float companyTurnover, String companyCEO, List<StockExchange> listedExchanges) {
        this.companyName = companyName;
        this.companyTurnover = companyTurnover;
        this.companyCEO = companyCEO;
        this.listedExchanges = listedExchanges;

    }

    public void addStockExchange(StockExchange stockExchange){
        listedExchanges.add(stockExchange);
    }

}
