package com.example.lab2.service;

import com.example.lab2.model.CarModel;
import com.example.lab2.model.CarBrand;
import com.example.lab2.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarModelService {

    private final CarModelRepository repository;

    public CarModelService(CarModelRepository repository) {
        this.repository = repository;
    }


    public List<CarModel> findAll() {
        return repository.findAll();
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public Optional<CarModel> findById(UUID id) {
        return repository.findById(id);
    }

    public CarModel findByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Model not found: " + id
                ));
    }

    public List<CarModel> findByBrand(CarBrand carBrand) {
        return repository.findByBrand(carBrand);
    }

    public CarModel save(CarModel carModel) {
        return repository.save(carModel);
    }

//    public void deleteById(UUID id) {
//        repository.deleteById(id);
//    }
}