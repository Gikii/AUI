package com.example.category_service.controller;

import com.example.category_service.model.CarBrand;
import com.example.category_service.service.CarBrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
public class CarBrandController {

    private final CarBrandService service;

    public CarBrandController(CarBrandService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarBrand> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CarBrand getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public CarBrand create(@RequestBody CarBrand brand) {
        brand.setId(UUID.randomUUID());
        return service.save(brand);
    }

    @PutMapping("/{id}")
    public CarBrand update(@PathVariable UUID id, @RequestBody CarBrand brand) {
        CarBrand existing = service.findById(id);
        existing.setName(brand.getName());
        existing.setCountry(brand.getCountry());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
