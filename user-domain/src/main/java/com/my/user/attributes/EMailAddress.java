package com.my.user.attributes;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public final class EMailAddress {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    private final String value;

    private EMailAddress(String value) {
        this.value = value;
    }

    public static EMailAddress of(String value){
        checkNotNull(value,"e-mail cannot be null");
        checkArgument(value.length() <= 100, "e-mail cannot be more than 20 character");
        checkArgument(value.matches(EMAIL_REGEX), "Invalid e-mail");
        return new EMailAddress(value);
    }

    public String getValue() {
        return value;
    }
}
