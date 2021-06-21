package com.stockmarket.marketservice.model;

import com.stockmarket.marketservice.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompanyIO {

    private Company company;

    private List<String> companyCode;

    public CompanyIO(Company company, List<String> companyCode) {
        this.company = company;
        this.companyCode = companyCode;
    }
}
