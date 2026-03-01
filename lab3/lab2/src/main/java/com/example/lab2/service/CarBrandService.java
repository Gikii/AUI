package com.example.lab2.service;

import com.example.lab2.model.CarBrand;
import com.example.lab2.repository.CarBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@Service
public class CarBrandService {

    private final CarBrandRepository repository;

    public CarBrandService(CarBrandRepository repository) {
        this.repository = repository;
    }

    public List<CarBrand> findAll() {
        return repository.findAll();
    }

public CarBrand findByIdOrThrow(UUID id) {
    return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Brand not found: " + id
            ));
}
    public CarBrand update(UUID id, CarBrand updated) {
        CarBrand existing = findByIdOrThrow(id);
        existing.setName(updated.getName());
        existing.setCountry(updated.getCountry());
        return repository.save(existing);
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