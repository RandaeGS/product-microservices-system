package com.randaegs.domain.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductStock {

    @NotNull
    @Min(0)
    public Integer actualStock;

    @NotNull
    @Min(0)
    public Integer minimumStock;

    @NotNull
    @Min(0)
    public Integer maximumStock;
}
