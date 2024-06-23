package com.rca.mysecurity.controller;


import jakarta.validation.Valid;

import com.rca.mysecurity.dto.LaptopDTO;
import com.rca.mysecurity.entity.Laptop;
import com.rca.mysecurity.exceptions.ResourceNotFoundException;
import com.rca.mysecurity.services.LaptopService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public List<Laptop> getAllLaptops() {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable int id) {
        return laptopService.getLaptopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<?> createLaptop(@Valid @RequestBody LaptopDTO laptop, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If validation errors exist, return Bad Request with error details
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getDefaultMessage()).append(", ");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }

        Laptop createdLaptop = laptopService.createLaptop(laptop);
        return ResponseEntity.ok(createdLaptop);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable int id, @RequestBody Laptop laptopDetails) {
        try {
            return ResponseEntity.ok(laptopService.updateLaptop(id, laptopDetails));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable int id) {
        laptopService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }
}