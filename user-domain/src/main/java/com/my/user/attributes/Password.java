package com.my.user.attributes;

import com.my.infrastructure.attributes.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public final class Password extends ValueObject<String> {

    private Password(String value) {
        super(value);
    }

    public static Password of(String value){
        checkNotNull(value, "Password cannot be null");
        checkArgument(value.length() >= 3, "Password cannot be less than 3 character");
        checkArgument(value.length() <= 500, "Password cannot be more than 50 character");
        return new Password(value);
    }
}
