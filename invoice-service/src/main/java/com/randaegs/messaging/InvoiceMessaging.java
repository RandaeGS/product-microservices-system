package com.randaegs.messaging;

import com.randaegs.dto.ProductSoldMessage;
import com.randaegs.entities.Invoice;
import com.randaegs.services.InvoiceService;
import com.randaegs.services.JasperService;
import com.randaegs.services.S3Service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

@ApplicationScoped
public class InvoiceMessaging {

    private static final Logger log = LoggerFactory.getLogger(InvoiceMessaging.class);

    @Inject
    InvoiceService invoiceService;

    @Inject
    JasperService jasperService;

    @Inject
    S3Service s3Service;

    @Incoming("create")
    public void createInvoice(ProductSoldMessage dto) {
        log.info("Message recieved for processing");

        try {
            Invoice invoice = invoiceService.create(dto);

            log.info("Generating PDF invoice file");
            File pdf = jasperService.generateInvoicePDF(invoice);

            String objectKey = "invoices/" + invoice.id + ".pdf";
            log.info("Uploading PDF invoice to S3 with key: {}", objectKey);
            s3Service.uploadInvoice(pdf, objectKey);

            log.info("Processing pipeline completed!");
        } catch (JRException | IOException e) {
            log.error("Error while processing message\n {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
