package com.stockmarket.marketservice.repository;

import com.stockmarket.marketservice.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector,Long> {
}
