package com.my.infrastructure.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AmazonCredentialsProvider {

    private final AmazonCredentialsProperties properties;

    @Bean
    public AWSCredentials awsCredentials(){
        return new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
    }

    @Bean
    public AWSStaticCredentialsProvider credentialsProvider(){
        return new AWSStaticCredentialsProvider(awsCredentials());
    }
}
