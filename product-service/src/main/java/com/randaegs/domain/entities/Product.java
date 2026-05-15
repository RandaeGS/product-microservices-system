package com.randaegs.domain.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection = "products")
public class Product {
    public ObjectId id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    public String name;

    public LocalDateTime creationDate;

    public Boolean deleted;

    @NotNull
    @Valid
    public ProductStock productStock;

    public Product() {
        this.creationDate = LocalDateTime.now();
        this.deleted = false;
    }
}
