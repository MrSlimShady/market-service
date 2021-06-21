package com.stockmarket.marketservice.repository;

import com.stockmarket.marketservice.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange,Long> {
}
