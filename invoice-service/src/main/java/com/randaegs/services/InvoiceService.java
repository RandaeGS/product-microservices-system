package com.randaegs.services;

import com.randaegs.dto.ProductSoldMessage;
import com.randaegs.entities.Invoice;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.io.InputStream;
import java.util.List;

@ApplicationScoped
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
    @Inject
    S3Service s3Service;

    public List<Invoice> list(Integer page, Integer size) {
        return Invoice.findAll().page(page, size).list();
    }

    @Transactional
    public Invoice create(ProductSoldMessage dto) {
        var invoice = new Invoice(dto);
        invoice.persist();

        log.info("Invoice {} saved.", invoice.id);
        return invoice;
    }

    public Response getInvoice(String id) {
        try {
            InputStream invoice = s3Service.getInvoice(id);
            return Response.ok(invoice).build();

        } catch (NoSuchKeyException _) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Object key not found!")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }
}
