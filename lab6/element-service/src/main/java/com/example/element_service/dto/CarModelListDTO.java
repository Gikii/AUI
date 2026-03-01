package com.example.element_service.dto;

import java.util.UUID;

public record CarModelListDTO(
        UUID id,
        String modelName,
        String brandName
) {}