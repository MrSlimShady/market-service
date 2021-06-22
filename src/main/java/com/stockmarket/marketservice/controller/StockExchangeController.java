package com.stockmarket.marketservice.controller;


import com.stockmarket.marketservice.entity.Company;
import com.stockmarket.marketservice.entity.StockExchange;
import com.stockmarket.marketservice.model.MessageResponse;
import com.stockmarket.marketservice.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/exchange")
public class StockExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addStockExchange(@RequestBody StockExchange stockExchange){
        stockExchangeService.saveStockExchange(stockExchange);
        return ResponseEntity.ok(new MessageResponse("Exchange added successfully!"));
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteStockExchange(@RequestBody StockExchange stockExchange){
        stockExchangeService.deleteExchange(stockExchange);
        return ResponseEntity.ok(new MessageResponse("Exchange deleted successfully!"));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllExchange(){

        return ResponseEntity.ok(stockExchangeService.getAllStockExchange());
    }

}
