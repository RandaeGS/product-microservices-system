package com.randaegs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SellProductDto(
        @NotNull @NotEmpty String id,
        @NotNull @Min(1) @Max(9999) Integer amount
) {
}
