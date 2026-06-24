package com.randaegs.messaging;

import com.randaegs.domain.dto.SellProductDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class InvoiceMessaging {

    @Channel("create")
    Emitter<SellProductDto> emitter;

    public void createInvoice(SellProductDto dto) {
        emitter.send(dto);
    }
}
