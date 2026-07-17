package com.randaegs.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

@ApplicationScoped
public class S3Service {
    @Inject
    S3Client s3Client;

    @ConfigProperty(name = "bucket.name")
    String bucketName;

    public void uploadInvoice(File invoice, String objectKey) {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .contentType("application/pdf")
                .build();

        s3Client.putObject(putRequest, RequestBody.fromFile(invoice));
    }
}
