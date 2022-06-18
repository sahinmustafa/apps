package com.my.user.attributes;

import com.my.infrastructure.attributes.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public final class Username extends ValueObject<String> {

    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9]+(?:[_-]?[a-zA-Z0-9])*$";


    private Username(String value) {
        super(value);
    }

    public static Username of(String value) {
        checkNotNull(value, "Username cannot be null");
        checkArgument(value.length() >= 3, "Username cannot be less than 3 character");
        checkArgument(value.length() <= 20, "Username cannot be more than 20 character");
        checkArgument(value.matches(USER_NAME_REGEX), "Username not valid! " +
                "Username cannot star with special character or cannot end. You can only use a-z A-Z 0-9 _-");

        return new Username(value);
    }
}
