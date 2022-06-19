package com.my.app.aws.adapter.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SnsPublisher {

    private final AmazonSNS client;

    @Autowired
    public SnsPublisher(AmazonSNS client) {
        this.client = client;
    }

    public void publish(PublishRequest request){

        com.amazonaws.services.sns.model.PublishRequest awsRequest =
                createAwsRequest(request);

        PublishResult publish = client.publish(awsRequest);

    }

    private com.amazonaws.services.sns.model.PublishRequest createAwsRequest(PublishRequest request) {
        return new com.amazonaws.services.sns.model.PublishRequest()
                .withTopicArn(request.getTopicArn())
                .withMessage(convertToJson(request));
    }


    private String convertToJson(Object message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper
                    .writeValueAsString(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
