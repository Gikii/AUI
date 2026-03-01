package com.example.lab2.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Table(name = "car_brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarBrand {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarModel> models = new ArrayList<>();


}
