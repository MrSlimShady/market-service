package com.stockmarket.marketservice.service;

import com.stockmarket.marketservice.entity.Sector;
import com.stockmarket.marketservice.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SectorService {

    @Autowired
    SectorRepository sectorRepository;


    @Transactional
    public void addSector(Sector sector){
        sectorRepository.save(sector);
    }

    public  List<Sector> getAll(){
        return sectorRepository.findAll();
    }

}
