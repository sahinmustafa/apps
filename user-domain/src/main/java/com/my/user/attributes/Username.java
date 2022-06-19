package com.my.user.attributes;

import com.my.infrastructure.attributes.ValueObject;

import static com.my.infrastructure.validation.StringValidation.*;


public final class Username extends ValueObject<String> {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;
    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9]+(?:[_-]?[a-zA-Z0-9])*$";


    private Username(String value) {
        super(value);
    }

    public static Username of(String value) {
        isNull(value, "Username cannot be null");
        lessThan(value, MIN_LENGTH, "Username cannot be less than " + MIN_LENGTH + " character");
        greaterThan(value, MAX_LENGTH, "Username cannot be more than " + MAX_LENGTH + " character");
        checkArgument(value.matches(USER_NAME_REGEX), "Username not valid! " +
                "Username cannot start with special character or cannot end. You can only use a-z A-Z 0-9 _-");

        return new Username(value);
    }
}
