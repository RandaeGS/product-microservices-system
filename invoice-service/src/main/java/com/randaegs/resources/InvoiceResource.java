package com.randaegs.resources;

import com.randaegs.services.InvoiceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/invoices")
public class InvoiceResource {

    private static final Logger log = LoggerFactory.getLogger(InvoiceResource.class);
    @Inject
    InvoiceService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listInvoices(@QueryParam("page") @DefaultValue("0") Integer page, @QueryParam("size") @DefaultValue("10") Integer size) {
        return Response.ok(service.list(page, size)).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/pdf")
    public Response getInvoice(@PathParam("id") String id) {
        log.info("Getting invoice with id: {}", id);
        return service.getInvoice(id);
    }
}
