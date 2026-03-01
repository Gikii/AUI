package com.example.element_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "car_models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarModel {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String modelName;

    private int productionYear;

    @Embedded
    private CarBrandSimple brand;
}
