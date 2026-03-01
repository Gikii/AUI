package com.example.lab2.dto;

import java.util.List;
import java.util.UUID;

public record CarBrandReadDTO(
        UUID id,
        String name,
        String country,
        List<CarModelListDTO> models
) {}