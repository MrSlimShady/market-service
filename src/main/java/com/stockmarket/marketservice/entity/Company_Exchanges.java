package com.stockmarket.marketservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Data
@NoArgsConstructor
public class Company_Exchanges {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(name = "company_id")
    private long companyId;

    @JoinColumn(name = "stock_exchange_id")
    private long stockExchangeId;

    private String companyCodeInExchange;

    public Company_Exchanges(long companyId, long stockExchangeId, String companyCodeInExchange) {
        this.companyId = companyId;
        this.stockExchangeId = stockExchangeId;
        this.companyCodeInExchange = companyCodeInExchange;
    }
}
