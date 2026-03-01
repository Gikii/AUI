package com.example.lab2.dto;

import java.util.UUID;

public record CarModelListDTO(
        UUID id,
        String modelName,
        String brandName
) {}