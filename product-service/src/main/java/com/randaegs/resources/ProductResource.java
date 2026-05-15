package com.randaegs.resources;

import com.randaegs.domain.entities.Product;
import com.randaegs.repositories.ProductRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.net.URI;
import java.util.List;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @GET
    public List<Product> list(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        return productRepository.findActiveProducts(page, size);
    }

    @GET
    @Path("/{id}")
    public Product get(String id) {
        ObjectId productId = new ObjectId(id);
        return productRepository.findById(productId);
    }

    @POST
    @Transactional
    public Response create(@Valid Product product){
        productRepository.persist(product);
        return Response.created(URI.create("/products/" + product.id)).build();
    }

    @PUT
    @Transactional
    public Response update(@Valid Product product) {
        if (product.id == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        productRepository.update(product);
        return Response.ok(URI.create("/products/" + product.id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
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

}
