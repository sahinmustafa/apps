package com.my.user.converter.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.my.user.attributes.Username;

public class UsernameConverter implements DynamoDBTypeConverter<String, Username> {

    @Override
    public String convert(Username username) {
        return username.getValue();
    }

    @Override
    public Username unconvert(String s) {
        return Username.of(s);
    }
}
