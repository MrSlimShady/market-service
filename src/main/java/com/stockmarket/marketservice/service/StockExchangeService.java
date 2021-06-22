package com.stockmarket.marketservice.service;

import com.stockmarket.marketservice.entity.Company_Exchanges;
import com.stockmarket.marketservice.entity.StockExchange;
import com.stockmarket.marketservice.repository.Company_ExchangesRepository;
import com.stockmarket.marketservice.repository.StockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockExchangeService {

    @Autowired
    StockExchangeRepository stockExchangeRepository;

    @Autowired
    Company_ExchangesRepository company_exchangesRepository;

    @Transactional
    public void saveAll(List<StockExchange> stockExchangeList){
        stockExchangeRepository.saveAll(stockExchangeList);
    }

    @Transactional
    public void saveStockExchange(StockExchange stockExchange){
        stockExchangeRepository.save(stockExchange);
    }

    public List<StockExchange> getAllStockExchange(){
        return stockExchangeRepository.findAll();
    }

    @Transactional
    public  void deleteExchange(StockExchange stockExchange){
        List<Company_Exchanges> company_exchangesList = company_exchangesRepository.findCompany_ExchangesByStockExchangeId(stockExchange.getId());
        company_exchangesList.stream().forEach(c -> company_exchangesRepository.delete(c));
        stockExchangeRepository.delete(stockExchange);
    }
}
