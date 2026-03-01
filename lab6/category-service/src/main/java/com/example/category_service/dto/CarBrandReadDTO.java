package com.example.category_service.dto;

import java.util.List;
import java.util.UUID;

public record CarBrandReadDTO(
        UUID id,
        String name,
        String country

) {}