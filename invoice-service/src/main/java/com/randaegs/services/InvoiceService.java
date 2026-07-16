package com.randaegs.services;

import com.randaegs.dto.ProductSoldMessage;
import com.randaegs.entities.Invoice;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Transactional
    public Invoice create(ProductSoldMessage dto) {
        var invoice = new Invoice(dto);
        invoice.persist();

        log.info("Invoice {} saved.", invoice.id);
        return invoice;
    }
}
