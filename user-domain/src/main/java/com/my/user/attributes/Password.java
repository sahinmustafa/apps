package com.my.user.attributes;

import com.my.infrastructure.attributes.ValueObject;

import static com.my.infrastructure.validation.StringValidation.*;


public final class Password extends ValueObject<String> {

    private static final int MAX_LENGTH = 500;
    private static final int MIN_LENGTH = 3;

    private Password(String value) {
        super(value);
    }

    public static Password of(String value){
        isNull(value, "Password cannot be null");
        lessThan(value, MIN_LENGTH, "Password cannot be less than " + MIN_LENGTH + " character");
        greaterThan(value,  MAX_LENGTH, "Password cannot be more than " + MAX_LENGTH + " character");
        return new Password(value);
    }
}
