package com.example.element_service.repository;

import com.example.element_service.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, UUID> {
    List<CarModel> findByBrandBrandId(UUID brandId);
    void deleteByBrandBrandId(UUID brandId);
}
