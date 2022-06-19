package com.my.app.aws.adapter.sns;

public enum EventType {

    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");

    private String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
