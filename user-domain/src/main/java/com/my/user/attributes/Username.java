package com.my.user.attributes;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public final class Username implements Serializable {

    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9]+(?:[_-]?[a-zA-Z0-9])*$";

    private final String value;

    private Username(String value) {
        this.value = value;
    }

    public static Username of(String value) {
        checkNotNull(value, "Username cannot be null");
        checkArgument(value.length() >= 3, "Username cannot be less than 3 character");
        checkArgument(value.length() <= 20, "Username cannot be more than 20 character");
        checkArgument(value.matches(USER_NAME_REGEX), "Username not valid! " +
                "Username cannot star with special character or cannot end. You can only use a-z A-Z 0-9 _-");

        return new Username(value);
    }

    public String getValue() {
        return value;
    }
}
