package com.randaegs.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductSoldMessage(
        @NotNull @NotEmpty String id,
        @NotNull @NotEmpty String name,
        @NotNull BigDecimal price,
        @NotNull @Min(1) @Max(9999) Integer amount
) {
}
