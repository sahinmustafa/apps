package com.my.app.aws.adapter.sns;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.my.app.aws.adapter.ClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnsClientProvider extends ClientProvider {

    private final AWSCredentialsProvider credentialsProvider;

    @Autowired
    public SnsClientProvider(AWSCredentialsProvider credentialProvider) {
        this.credentialsProvider = credentialProvider;
    }

    @Bean
    public AmazonSNS amazonSNS(){
        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
                .withRegion(region)
                .build();
    }

}
