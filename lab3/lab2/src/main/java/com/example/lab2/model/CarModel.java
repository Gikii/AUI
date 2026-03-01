package com.example.lab2.model;

import com.example.lab2.model.CarBrand;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "car_models")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarModel {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String modelName;

    private int productionYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private CarBrand brand;
}