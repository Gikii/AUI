package com.example.lab2.controller;

import com.example.lab2.dto.*;
import com.example.lab2.model.CarBrand;
import com.example.lab2.model.CarModel;
import com.example.lab2.service.CarBrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brands")
public class CarBrandController {

    private final CarBrandService brandService;

    public CarBrandController(CarBrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<CarBrandListDTO> allBrands() {
        return brandService.findAll().stream()
                .map(b -> new CarBrandListDTO(b.getId(), b.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarBrandReadDTO getBrand(@PathVariable UUID id) {
        CarBrand b = brandService.findByIdOrThrow(id);
        List<CarModelListDTO> models = b.getModels().stream()
                .map(m -> new CarModelListDTO(m.getId(), m.getModelName(), b.getName()))
                .collect(Collectors.toList());
        return new CarBrandReadDTO(b.getId(), b.getName(), b.getCountry(), models);
    }

    @PostMapping
    public ResponseEntity<CarBrandReadDTO> createBrand(@RequestBody CarBrandCreateDTO dto) {
        CarBrand toSave = new CarBrand();
        toSave.setId(UUID.randomUUID());
        toSave.setName(dto.name());
        toSave.setCountry(dto.country());
        toSave.setModels(new java.util.ArrayList<>());

        CarBrand saved = brandService.save(toSave);
        CarBrandReadDTO read = new CarBrandReadDTO(saved.getId(), saved.getName(), saved.getCountry(), List.of());
        return ResponseEntity.created(URI.create("/api/brands/" + saved.getId())).body(read);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarBrandReadDTO> updateBrand(@PathVariable UUID id, @RequestBody CarBrandCreateDTO dto) {
        CarBrand payload = new CarBrand();
        payload.setName(dto.name());
        payload.setCountry(dto.country());
        CarBrand updated = brandService.update(id, payload);
        return ResponseEntity.ok(new CarBrandReadDTO(updated.getId(), updated.getName(), updated.getCountry(),
                updated.getModels().stream()
                        .map(m -> new CarModelListDTO(m.getId(), m.getModelName(), updated.getName()))
                        .collect(Collectors.toList())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID id) {
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}