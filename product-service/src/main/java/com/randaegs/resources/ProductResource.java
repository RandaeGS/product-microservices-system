package com.randaegs.resources;

import com.randaegs.domain.dto.SellProductDto;
import com.randaegs.domain.entities.Product;
import com.randaegs.services.ProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    public List<Product> list(@QueryParam("page") @DefaultValue("0") Integer page, @QueryParam("size") @DefaultValue("10") Integer size) {
        return service.list(page, size);
    }

    @GET
    @Path("/{id}")
    public Product get(String id) {
        return service.get(id);
    }

    @POST
    @Transactional
    public Response create(@Valid Product product) {
        return service.create(product);
    }

    @PUT
    @Transactional
    public Response update(@Valid Product product) {
        return service.update(product);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(String id) {
        return service.delete(id);
    }

    @POST
    @Path("/sell")
    public Response sell(@Valid SellProductDto dto) {
        return service.sell(dto);
    }
}
