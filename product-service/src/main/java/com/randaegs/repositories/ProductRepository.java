package com.randaegs.repositories;

import com.randaegs.domain.entities.Product;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {

}
