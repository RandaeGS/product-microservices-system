package com.randaegs.domain.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection = "products")
public class Product {
    public ObjectId id;
    public String name;
    public LocalDateTime creationDate;
    public Boolean deleted;

    public ProductStock productStock;
}
