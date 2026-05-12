package com.randaegs;

import com.randaegs.domain.entities.Product;
import com.randaegs.repositories.ProductRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> hello() {
        return productRepository.findAll().list();
    }
}
