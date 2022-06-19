package com.my.app.aws.adapter;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:aws.properties")
public class AwsCredentialProvider {

    @Value("${amazon.aws.accessKey}")
    private String accessKey;

    @Value("${amazon.aws.secretKey}")
    private String secretKey;

    @Bean
    public AWSCredentials awsCredentials(){
        return new BasicAWSCredentials(getAccessKey(), getSecretKey());
    }

    @Bean
    public AWSCredentialsProvider credentialsProvider(){
        return new AWSStaticCredentialsProvider(awsCredentials());
    }

    private String getAccessKey(){
        if (StringUtils.isBlank(accessKey)) {
            this.accessKey = System.getProperty("amazon.aws.accessKey");
        }
        return accessKey;
    }

    private String getSecretKey(){
        if (StringUtils.isBlank(secretKey)) {
            this.secretKey = System.getProperty("amazon.aws.secretKey");
        }
        return secretKey;
    }

}
