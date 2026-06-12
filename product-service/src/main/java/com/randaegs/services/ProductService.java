package com.randaegs.services;

import com.randaegs.domain.dto.SellProductDto;
import com.randaegs.domain.entities.Product;
import com.randaegs.repositories.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.net.URI;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> list(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        return productRepository.findActiveProducts(page, size);
    }

    public Product get(String id) {
        if (id.length() != 24) return null;

        ObjectId productId = new ObjectId(id);
        return productRepository.findById(productId);
    }

    public Response create(@Valid Product product) {
        productRepository.persist(product);
        return Response.created(URI.create("/products/" + product.id)).build();
    }

    public Response update(@Valid Product product) {
        if (product.id == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        productRepository.update(product);
        return Response.ok(URI.create("/products/" + product.id)).build();
    }

    public Response delete(String id) {
        ObjectId productId = new ObjectId(id);
        Product product = null;
        if ((product = productRepository.findById(productId)) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        product.deleted = true;
        productRepository.update(product);
        return Response.ok().build();
    }

    @Transactional
    public Response sell(SellProductDto dto) {

        Product product = get(dto.id());

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (product.productStock.actualStock - dto.amount() < product.productStock.minimumStock) {
            return Response.status(Response.Status.CONFLICT.getStatusCode(), "Amount surpasses minimum stock value").build();
        }

        product.productStock.actualStock -= dto.amount();
        productRepository.update(product);
        return Response.ok().build();
    }
}
