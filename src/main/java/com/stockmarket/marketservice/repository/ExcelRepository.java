package com.stockmarket.marketservice.repository;

import com.stockmarket.marketservice.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExcelRepository extends JpaRepository<StockPrice,Long> {
}
