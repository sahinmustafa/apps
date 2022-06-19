package com.my.app.aws.adapter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:aws.properties")
public class ClientProvider {

    @Value("${amazon.aws.region}")
    protected String region;
}
