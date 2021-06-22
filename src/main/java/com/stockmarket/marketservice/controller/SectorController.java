package com.stockmarket.marketservice.controller;

import com.stockmarket.marketservice.entity.Sector;
import com.stockmarket.marketservice.model.MessageResponse;
import com.stockmarket.marketservice.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/sector")
public class SectorController {

    @Autowired
    SectorService sectorService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addSector(@RequestBody Sector sector){

        sectorService.addSector(sector);
        return ResponseEntity.ok(new MessageResponse("Sector added successfully!"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSector(){
        return ResponseEntity.ok(sectorService.getAll());
    }

}
