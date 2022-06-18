package com.my.user.attributes;

import com.my.infrastructure.attributes.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public final class EMailAddress extends ValueObject<String> {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    private EMailAddress(String value) {
        super(value);
    }

    public static EMailAddress of(String value){
        checkNotNull(value,"e-mail cannot be null");
        checkArgument(value.length() <= 100, "e-mail cannot be more than 20 character");
        checkArgument(value.matches(EMAIL_REGEX), "Invalid e-mail");
        return new EMailAddress(value);
    }

}
