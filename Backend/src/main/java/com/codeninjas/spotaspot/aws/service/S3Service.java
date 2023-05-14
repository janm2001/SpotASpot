package com.codeninjas.spotaspot.aws.service;

import com.codeninjas.spotaspot.events.controller.EventController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    Logger logger = LoggerFactory.getLogger(S3Service.class);

    public void putObject(String bucketName, String key, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
    }

    public byte[] getObject(String bucketName, String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> res = s3Client.getObject(getObjectRequest);
        try {
            return res.readAllBytes();
        } catch (IOException e) {
            logger.error(MessageFormat.format("Bucket: %s, failed getObject with key: %s", bucketName, key));
            throw new RuntimeException(e);
        }
    }

}
