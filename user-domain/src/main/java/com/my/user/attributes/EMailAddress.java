package com.my.user.attributes;

import com.my.infrastructure.attributes.ValueObject;

import static com.my.infrastructure.validation.StringValidation.*;

public final class EMailAddress extends ValueObject<String> {

    private static final int MAX_EMAIL_LENGTH = 100;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    private EMailAddress(String value) {
        super(value);
    }

    public static EMailAddress of(String value){
        isNull(value,"e-mail cannot be null");
        greaterThan(value, MAX_EMAIL_LENGTH, "e-mail cannot be more than " + MAX_EMAIL_LENGTH + " character");
        checkArgument(value.matches(EMAIL_REGEX), "Invalid e-mail");
        return new EMailAddress(value);
    }

}
