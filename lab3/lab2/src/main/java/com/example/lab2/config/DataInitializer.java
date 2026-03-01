package com.example.lab2.config;

import com.example.lab2.model.CarBrand;
import com.example.lab2.model.CarModel;
import com.example.lab2.service.CarBrandService;
import com.example.lab2.service.CarModelService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {

    public DataInitializer(CarBrandService carBrandService, CarModelService carModelService) {
        CarBrand Volvo = new CarBrand(UUID.randomUUID(), "Volvo", "Sweden", new ArrayList<>());
        CarBrand Porsche = new CarBrand(UUID.randomUUID(), "Porsche", "Germany", new ArrayList<>());
        CarBrand Honda = new CarBrand(UUID.randomUUID(), "Honda", "Japan", new ArrayList<>());

        carBrandService.save(Volvo);
        carBrandService.save(Porsche);
        carBrandService.save(Honda);

        CarModel XC60 = new CarModel(UUID.randomUUID(), "XC60", 2011, Volvo);
        CarModel C70 = new CarModel(UUID.randomUUID(), "C70", 2003, Volvo);
        CarModel GT3RS = new CarModel(UUID.randomUUID(), "GT3RS", 2023, Porsche);
        CarModel Civic = new CarModel(UUID.randomUUID(), "Civic", 2015, Honda);
        CarModel NSX = new CarModel(UUID.randomUUID(), "NSX", 1997, Honda);

        carModelService.save(XC60);
        carModelService.save(C70);
        carModelService.save(GT3RS);
        carModelService.save(Civic);
        carModelService.save(NSX);

    }
}