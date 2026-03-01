package com.example.lab2.service;

import com.example.lab2.model.CarBrand;
import com.example.lab2.repository.CarBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarBrandService {

    private final CarBrandRepository repository;

    public CarBrandService(CarBrandRepository repository) {
        this.repository = repository;
    }

    public List<CarBrand> findAll() {
        return repository.findAll();
    }



    public CarBrand save(CarBrand carBrand) {
        return repository.save(carBrand);
    }

    public CarBrand findById(UUID id) {
        return repository.findById(id).orElse(null);
    }
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}