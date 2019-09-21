package com.my.user.converter.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.my.user.attributes.Password;

public class PasswordConverter implements DynamoDBTypeConverter<String, Password> {

    @Override
    public String convert(Password password) {
        return password.getValue();
    }

    @Override
    public Password unconvert(String s) {
        return Password.of(s);
    }
}
