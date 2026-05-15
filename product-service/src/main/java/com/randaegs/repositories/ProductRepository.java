package com.randaegs.repositories;

import com.randaegs.domain.entities.Product;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {

    public List<Product> findActiveProducts(Integer page, Integer size) {
        return find("deleted", false).page(page, size).list();
    }
}
