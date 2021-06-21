package com.stockmarket.marketservice.repository;

import com.stockmarket.marketservice.entity.IPODetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPODetailsRepository extends JpaRepository<IPODetails,Long> {

    IPODetails findIPODetailsByCompanyNameAndAndStockExchange(String companyName, String exchangeName);


    List<IPODetails> findIPODetailsByCompanyName(String companyName);
}
