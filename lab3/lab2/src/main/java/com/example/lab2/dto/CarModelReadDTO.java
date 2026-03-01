package com.example.lab2.dto;

import java.util.UUID;

public record CarModelReadDTO(
        UUID id,
        String modelName,
        int productionYear,
        UUID brandId,
        String brandName
) {}