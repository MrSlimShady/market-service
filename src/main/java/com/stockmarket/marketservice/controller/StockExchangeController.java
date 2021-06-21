package com.stockmarket.marketservice.controller;


import com.stockmarket.marketservice.entity.Company;
import com.stockmarket.marketservice.entity.StockExchange;
import com.stockmarket.marketservice.model.MessageResponse;
import com.stockmarket.marketservice.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
    public ResponseEntity<?> addStockExchange(@RequestBody StockExchange stockExchange){
        stockExchangeService.saveStockExchange(stockExchange);
        return ResponseEntity.ok(new MessageResponse("Exchange added successfully!"));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllExchange(){

        return ResponseEntity.ok(stockExchangeService.getAllStockExchange());
    }

//    @PostMapping("/companies")
//    public ResponseEntity<?> getAllCompaniesInExchange(@RequestBody StockExchange stockExchange){
//
//        //REST template call to company-service
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//
//
//        HttpEntity<StockExchange> entity = new HttpEntity<>(stockExchange, headers);
////        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/api/company/companies", entity,String.class);
//
//        ResponseEntity<List<Optional<Company>>> response = restTemplate.exchange("http://localhost:8081/api/company/companies", HttpMethod.POST, entity, new ParameterizedTypeReference<List<Optional<Company>>>() {});
////        System.out.println(response.getBody());
//        return ResponseEntity.ok(response.getBody());
//    }

}
