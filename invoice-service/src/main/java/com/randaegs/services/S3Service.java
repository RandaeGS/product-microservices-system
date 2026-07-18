package com.randaegs.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

@ApplicationScoped
public class S3Service {
    @Inject
    S3Client s3Client;

    @ConfigProperty(name = "bucket.name")
    String bucketName;

    public void uploadInvoice(File invoice, String id) {
        String objectKey = "invoices/" + id + ".pdf";

        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .contentType("application/pdf")
                .build();

        s3Client.putObject(putRequest, RequestBody.fromFile(invoice));
    }

    public ResponseInputStream<GetObjectResponse> getInvoice(String id) {
        String objectKey = "invoices/" + id + ".pdf";

        GetObjectRequest request = GetObjectRequest
                .builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        return s3Client.getObject(request);
    }
}
