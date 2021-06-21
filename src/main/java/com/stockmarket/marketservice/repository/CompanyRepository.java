package com.stockmarket.marketservice.repository;


import com.stockmarket.marketservice.entity.Company;
import com.stockmarket.marketservice.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyName(String companyName);
    List<Optional<Company>> findCompanyBySector(Sector sector);
}
