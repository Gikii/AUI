package com.example.lab2.repository;

import com.example.lab2.model.CarModel;
import com.example.lab2.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, UUID> {
    List<CarModel> findByBrand(CarBrand carBrand);
}