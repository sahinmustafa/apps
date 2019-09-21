package com.my.user.attributes;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public final class Password {

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password of(String value){
        checkNotNull(value, "Password cannot be null");
        checkArgument(value.length() >= 3, "Password cannot be less than 3 character");
        checkArgument(value.length() <= 500, "Password cannot be more than 50 character");
        return new Password(value);
    }

    public String getValue() {
        return value;
    }
}
