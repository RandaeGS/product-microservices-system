package com.randaegs.messaging;

import com.randaegs.dto.SellProductDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class InvoiceMessaging {

    private static final Logger log = LoggerFactory.getLogger(InvoiceMessaging.class);

    @Incoming("product-in")
    public void hello(SellProductDto dto) {
        log.info("Product {} and amount {}", dto.id(), dto.amount());
    }


}
