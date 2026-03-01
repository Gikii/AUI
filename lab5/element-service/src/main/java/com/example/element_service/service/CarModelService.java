package com.example.element_service.service;

import com.example.element_service.model.CarBrandSimple;
import com.example.element_service.model.CarModel;
import com.example.element_service.repository.CarModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CarModelService {

    private final CarModelRepository repository;

    private final Map<UUID, CarBrandSimple> brands = new HashMap<>();

    public CarModelService(CarModelRepository repository) {
        this.repository = repository;
    }

    public List<CarModel> findAll() {
        return repository.findAll();
    }

    public CarModel findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found: " + id));
    }

    public CarModel save(CarModel model) {
        return repository.save(model);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    // Metody wywoływane przez category-service
    public void createSimplifiedBrand(CarBrandSimple brand) {
        brands.put(brand.getBrandId(), brand);
        System.out.println("Dodano uproszczoną markę: " + brand.getName());
    }

    @Transactional
    public void deleteSimplifiedBrand(UUID id) {
        repository.deleteByBrandBrandId(id);
        brands.remove(id);
        System.out.println("Usunięto uproszczoną markę oraz wszystkie jej modele o ID: " + id);
    }

    public List<CarModel> findByBrandId(UUID brandId) {
        return repository.findByBrandBrandId(brandId);
    }
}
