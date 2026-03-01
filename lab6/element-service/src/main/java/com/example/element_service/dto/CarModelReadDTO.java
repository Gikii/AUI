package com.example.element_service.dto;

import java.util.UUID;

public record CarModelReadDTO(
        UUID id,
        String modelName,
        int productionYear,
        UUID brandId,
        String brandName
) {}