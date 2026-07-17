package com.randaegs.services;

import com.randaegs.entities.Invoice;
import jakarta.enterprise.context.ApplicationScoped;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class JasperService {

    private static final Logger log = LoggerFactory.getLogger(JasperService.class);

    private JasperReport getReport(String templateName) {
        String route = "/jasper-templates/" + templateName + ".jasper";
        try (InputStream reportStream = getClass().getResourceAsStream(route)) {
            if (reportStream == null) {
                throw new RuntimeException("No se encontró el archivo JRXML en: " + route);
            }

            return (JasperReport) JRLoader.loadObject(reportStream);
        } catch (IOException | JRException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public File generateInvoicePDF(Invoice invoice) throws JRException, IOException {
        String templateName = "invoice-template";
        var template = getReport(templateName);

        Map<String, Object> params = new HashMap<>();
        params.put("invoiceId", invoice.id.toString());
        params.put("productId", invoice.productId);
        params.put("productName", invoice.productName);
        params.put("productPrice", invoice.productPrice);
        params.put("productAmount", invoice.productAmount);

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
        var formattedDate = formatter.format(invoice.creationDate);
        params.put("creationDate", formattedDate);

        JasperPrint jasperPrint = JasperFillManager.fillReport(template, params, new JREmptyDataSource());

        File tempFile = File.createTempFile(templateName, ".pdf");
        tempFile.deleteOnExit();

        JasperExportManager.exportReportToPdfFile(jasperPrint, tempFile.getAbsolutePath());
        return tempFile;
    }

}
