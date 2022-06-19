package com.my.app.aws.adapter.sns;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PublishRequest implements Serializable {

    private final String topicArn;

    protected PublishRequest(String topicArn) {
        this.topicArn = topicArn;
    }


    protected String getTopicArn(){
        return topicArn;
    }
}
