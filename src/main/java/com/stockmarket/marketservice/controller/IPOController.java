package com.stockmarket.marketservice.controller;

import com.stockmarket.marketservice.entity.IPODetails;
import com.stockmarket.marketservice.model.MessageResponse;
import com.stockmarket.marketservice.service.IPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/ipo")
public class IPOController {

    @Autowired
    IPOService ipoService;

    @GetMapping("/all")
    public ResponseEntity<?> getIPOs(){
        return ResponseEntity.ok(ipoService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIPO(@RequestBody IPODetails ipo){
        ipoService.addIPO(ipo);
        return ResponseEntity.ok(new MessageResponse("IPO added successfully!"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody IPODetails ipo){
        ipoService.deleteIPO(ipo);
        return ResponseEntity.ok(new MessageResponse("IPO deleted successfully!"));
    }
}
