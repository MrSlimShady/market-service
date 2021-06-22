package com.stockmarket.marketservice.service;

import com.stockmarket.marketservice.entity.Company;
import com.stockmarket.marketservice.entity.IPODetails;
import com.stockmarket.marketservice.repository.IPODetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPOService {
    @Autowired
    IPODetailsRepository ipoDetailsRepository;

    public List<IPODetails> getCompanyIPO(Company company){
        return ipoDetailsRepository.findIPODetailsByCompanyName(company.getCompanyName());
    }
    public void addIPO(IPODetails ipo){
        ipoDetailsRepository.save(ipo);
    }

    public List<IPODetails> getAll(){
        return ipoDetailsRepository.findAll(Sort.by(Sort.Direction.ASC, "openingDate"));
    }

    public void deleteIPO(IPODetails ipoDetails){
        ipoDetailsRepository.delete(ipoDetails);
    }
}
