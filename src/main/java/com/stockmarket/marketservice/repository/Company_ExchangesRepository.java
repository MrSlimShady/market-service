package com.stockmarket.marketservice.repository;

import com.stockmarket.marketservice.entity.Company_Exchanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Company_ExchangesRepository extends JpaRepository<Company_Exchanges,Long> {

    List<Company_Exchanges> findCompany_ExchangesByStockExchangeId(Long stockExchangeId);
    List<Company_Exchanges> findCompany_ExchangesByCompanyId(Long companyId);
    Company_Exchanges findCompany_ExchangesByCompanyIdAndStockExchangeId(Long companyId,Long stockExchangeId);

}
