package com.example.category_service.service;

import com.example.category_service.model.CarBrand;
import com.example.category_service.repository.CarBrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CarBrandService {

    private final CarBrandRepository repository;
    private final RestTemplate restTemplate;



    public CarBrandService(CarBrandRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public List<CarBrand> findAll() {
        return repository.findAll();
    }

    public CarBrand findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found: " + id));
    }

    public CarBrand save(CarBrand brand) {
        boolean isNew = (brand.getId() == null);
        if (isNew) brand.setId(UUID.randomUUID());
        CarBrand saved = repository.save(brand);

        if (isNew) {
            String createBrandUrl = "http://localhost:8082/api/models/brands";
            try {
                restTemplate.postForObject(createBrandUrl, saved, Void.class);
            } catch (Exception e) {
                System.err.println("Nie udało się powiadomić element-service: " + e.getMessage());
            }
        }

        return saved;
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
        String deleteBrandUrl = "http://localhost:8082/api/models/by-brand/" + id;
        try {
            restTemplate.delete(deleteBrandUrl);
        } catch (Exception e) {
            System.err.println("Nie udało się powiadomić element-service: " + e.getMessage());
        }
    }
}
