package com.stockmarket.marketservice.service;

import com.stockmarket.marketservice.entity.StockPrice;
import com.stockmarket.marketservice.excelconfig.ExcelConfig;
import com.stockmarket.marketservice.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    ExcelRepository excelRepository;


    public void save(MultipartFile file) {
        try {

            List<StockPrice> stockPriceList = ExcelConfig.excelToStockPrice(file.getInputStream());
            excelRepository.saveAll(stockPriceList);


        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<StockPrice> getAllStockPrice(){
        return excelRepository.findAll();
    }
}
