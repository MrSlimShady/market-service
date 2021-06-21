package com.stockmarket.marketservice.repository;

import com.stockmarket.marketservice.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice,Long> {

    List<StockPrice> findStockPriceByCompanyCode(String companyCode);
    List<StockPrice> findStockPriceByCompanyCodeAndDateBetween(String companyCode,Date startDate,Date endDate);

}
