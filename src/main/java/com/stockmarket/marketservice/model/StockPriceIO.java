package com.stockmarket.marketservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class StockPriceIO {

    private long companyID;
    private long exchangeID;
    private Date startDate;
    private Date endDate;

}
