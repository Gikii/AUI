package com.example.element_service.controller;

import com.example.element_service.model.CarBrandSimple;
import com.example.element_service.model.CarModel;
import com.example.element_service.service.CarModelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/models")
public class CarModelController {

    private final CarModelService service;

    public CarModelController(CarModelService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarModel> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CarModel getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public CarModel create(@RequestBody CarModel model) {
        model.setId(UUID.randomUUID());
        return service.save(model);
    }

    @PutMapping("/{id}")
    public CarModel update(@PathVariable UUID id, @RequestBody CarModel model) {
        CarModel existing = service.findById(id);
        existing.setModelName(model.getModelName());
        existing.setProductionYear(model.getProductionYear());
        existing.setBrand(model.getBrand());
        return service.save(existing);
    }

    @GetMapping("/brand/{brandId}")
    public List<CarModel> getModelsByBrand(@PathVariable UUID brandId) {
        return service.findByBrandId(brandId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteById(id);
    }


    @PostMapping("/brands")
    public ResponseEntity<Void> createSimplifiedBrand(@RequestBody CarBrandSimple brand) {
        service.createSimplifiedBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/by-brand/{brandId}")
    public ResponseEntity<Void> deleteModelsByBrand(@PathVariable UUID brandId) {
        service.deleteSimplifiedBrand(brandId);
        return ResponseEntity.noContent().build();
    }

}
