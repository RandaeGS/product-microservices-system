package com.randaegs.domain.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "product_stock")
public class ProductStock {
    public ObjectId id;
    public int actualStock;
    public int minimumStock;
    public int maximumStock;
    public Product product;
}
