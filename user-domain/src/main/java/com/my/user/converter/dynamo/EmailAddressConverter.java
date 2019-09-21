package com.my.user.converter.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.my.user.attributes.EMailAddress;

public class EmailAddressConverter implements DynamoDBTypeConverter<String, EMailAddress> {

    @Override
    public String convert(EMailAddress email) {
        return email.getValue();
    }

    @Override
    public EMailAddress unconvert(String s) {
        return EMailAddress.of(s);
    }
}
