package com.example.element_service.controller;

import com.example.element_service.model.CarBrandSimple;
import com.example.element_service.service.CarModelService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/brands")  // endpoint do komunikacji z category-service
public class CarBrandSyncController {

    private final CarModelService service;

    public CarBrandSyncController(CarModelService service) {
        this.service = service;
    }

    @PostMapping
    public void addBrand(@RequestBody CarBrandSimple brand) {
        service.createSimplifiedBrand(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable UUID id) {
        service.deleteSimplifiedBrand(id);
    }
}
