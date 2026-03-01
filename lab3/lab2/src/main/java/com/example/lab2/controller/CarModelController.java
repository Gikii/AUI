package com.example.lab2.controller;

import com.example.lab2.dto.*;
import com.example.lab2.model.CarBrand;
import com.example.lab2.model.CarModel;
import com.example.lab2.service.CarBrandService;
import com.example.lab2.service.CarModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarModelController {

    private final CarModelService modelService;
    private final CarBrandService brandService;

    public CarModelController(CarModelService modelService, CarBrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    // GET all models (all categories)
    @GetMapping("/models")
    public List<CarModelListDTO> allModels() {
        return modelService.findAll().stream()
                .map(m -> new CarModelListDTO(m.getId(), m.getModelName(),
                        m.getBrand() == null ? "Unknown" : m.getBrand().getName()))
                .collect(Collectors.toList());
    }

    // GET model by id
    @GetMapping("/models/{id}")
    public CarModelReadDTO getModel(@PathVariable UUID id) {
        CarModel m = modelService.findByIdOrThrow(id);
        return new CarModelReadDTO(m.getId(), m.getModelName(), m.getProductionYear(),
                m.getBrand() == null ? null : m.getBrand().getId(),
                m.getBrand() == null ? null : m.getBrand().getName());
    }

    // GET models by brand
    @GetMapping("/brands/{brandId}/models")
    public ResponseEntity<List<CarModelListDTO>> modelsByBrand(@PathVariable UUID brandId) {
        CarBrand brand;
        try {
            brand = brandService.findByIdOrThrow(brandId);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build(); // category does not exist
        }
        List<CarModel> models = modelService.findByBrand(brand);
        // if empty -> return 200 with empty list (different from 404)
        List<CarModelListDTO> dto = models.stream()
                .map(m -> new CarModelListDTO(m.getId(), m.getModelName(), brand.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    // POST create model under brand
    @PostMapping("/brands/{brandId}/models")
    public ResponseEntity<CarModelReadDTO> createModel(@PathVariable UUID brandId, @RequestBody CarModelCreateDTO dto) {
        CarBrand brand = brandService.findByIdOrThrow(brandId);
        CarModel toSave = new CarModel();
        toSave.setId(UUID.randomUUID());
        toSave.setModelName(dto.modelName());
        toSave.setProductionYear(dto.productionYear());
        toSave.setBrand(brand);

        CarModel saved = modelService.save(toSave);

        // set bidirectional (optional; JPA cascade can maintain it)
        brand.getModels().add(saved);
        brandService.save(brand);

        CarModelReadDTO read = new CarModelReadDTO(saved.getId(), saved.getModelName(), saved.getProductionYear(),
                brand.getId(), brand.getName());
        return ResponseEntity.created(URI.create("/api/models/" + saved.getId())).body(read);
    }

    // PUT update model (under brand resource) - category set by URL
    @PutMapping("/brands/{brandId}/models/{modelId}")
    public ResponseEntity<CarModelReadDTO> updateModel(
            @PathVariable UUID brandId,
            @PathVariable UUID modelId,
            @RequestBody CarModelCreateDTO dto) {

        CarBrand brand = brandService.findByIdOrThrow(brandId);
        CarModel existing = modelService.findByIdOrThrow(modelId);
        if (existing.getBrand() == null || !existing.getBrand().getId().equals(brandId)) {
            // model exists but not in this category — choose policy: treat as 400
            return ResponseEntity.badRequest().build();
        }
        existing.setModelName(dto.modelName());
        existing.setProductionYear(dto.productionYear());
        CarModel updated = modelService.save(existing);

        CarModelReadDTO read = new CarModelReadDTO(updated.getId(), updated.getModelName(), updated.getProductionYear(),
                brand.getId(), brand.getName());
        return ResponseEntity.ok(read);
    }

    // DELETE model by id
    @DeleteMapping("/models/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable UUID id) {
        modelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}