package com.my.infrastructure.amazon.dynamo;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import lombok.RequiredArgsConstructor;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@RequiredArgsConstructor
@EnableDynamoDBRepositories(basePackages = "com.my")
@PropertySource("classpath:aws.properties")
public class DynamoDbClientProvider {

    private final AWSCredentialsProvider credentialsProvider;

    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(credentialsProvider);
        amazonDynamoDB.setEndpoint(endpoint);
        //amazonDynamoDB.setRegion(Region.getRegion(Regions.DEFAULT_REGION));
        return amazonDynamoDB;
        /*return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.DEFAULT_REGION)
                .build();*/
    }
}
