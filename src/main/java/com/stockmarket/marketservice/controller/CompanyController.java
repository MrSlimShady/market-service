package com.stockmarket.marketservice.controller;



import com.stockmarket.marketservice.entity.Company;
import com.stockmarket.marketservice.entity.Sector;
import com.stockmarket.marketservice.entity.StockExchange;
import com.stockmarket.marketservice.model.CompanyIO;
import com.stockmarket.marketservice.model.MessageResponse;
import com.stockmarket.marketservice.model.StockPriceIO;
import com.stockmarket.marketservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@RequestBody CompanyIO companyIO){

        companyService.addNewCompany(companyIO);

        return ResponseEntity.ok(new MessageResponse("Company added successfully!"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyIO companyIO){
        companyService.updateCompany(companyIO);

        return ResponseEntity.ok(new MessageResponse("Company updated successfully!"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompany(){

        List<Company> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok(companyList);
    }



    @PostMapping("/exchange/companies")
    public ResponseEntity<List<Optional<Company>>> getCompanyByExchange(@RequestBody StockExchange stockExchange){

        return ResponseEntity.ok(companyService.getCompanyListInStockExchanges(stockExchange));
    }
    @PostMapping("/sector/companies")
    public ResponseEntity<List<Optional<Company>>> getCompanyBySector(@RequestBody Sector sector){

        return ResponseEntity.ok(companyService.getCompanyListInSector(sector));
    }

    @PostMapping("/company/delete")
    public ResponseEntity<?> deleteCompany(@RequestBody Company company){


        return ResponseEntity.ok(new MessageResponse("Company deleted successfully!"));

    }

    @PostMapping("/price")
    public ResponseEntity<?> getStockPrice(@RequestBody StockPriceIO stockPriceIO){
        return ResponseEntity.ok(companyService.getCompanyStockPrice(stockPriceIO.getCompanyID(),stockPriceIO.getExchangeID(),stockPriceIO.getStartDate(),stockPriceIO.getEndDate()));
    }


}
