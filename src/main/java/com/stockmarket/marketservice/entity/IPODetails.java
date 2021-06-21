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
public class IPODetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String companyName;
    private String stockExchange;
    private float pricePerShare;
    private long totalShares;
    private Date openingDate;
    private String remarks;
}
