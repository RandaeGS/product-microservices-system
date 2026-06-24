package com.randaegs.messaging;

import com.randaegs.dto.SellProductDto;
import com.randaegs.services.InvoiceService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class InvoiceMessaging {

    private static final Logger log = LoggerFactory.getLogger(InvoiceMessaging.class);

    @Inject
    InvoiceService invoiceService;

    @Incoming("create")
    public void createInvoice(SellProductDto dto) {
        invoiceService.create(dto);
    }
}
