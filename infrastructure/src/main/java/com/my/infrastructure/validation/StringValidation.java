package com.my.infrastructure.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ValidationException;

public class StringValidation {

    public static void isNull(String value) {
        isNull(value, "Value cannot be null");
    }

    public static void isNull(String value, String message){
        if (value == null) {
            throw new ValidationException(message);
        }
    }

    public static void isEmpty(String value){
        isEmpty(value, "Value cannot be empty!");
    }

    public static void isEmpty(String value, String message){
        if (StringUtils.isEmpty(value)) {
            throw new ValidationException(message);
        }
    }

    public static void isBlank(String value) {
        isBlank(value, "Value cannot be blank!");
    }

    public static void isBlank(String value, String message){
        if (StringUtils.isBlank(value)) {
            throw new ValidationException(message);
        }
    }

    public static void greaterThan(String value, int length) {
        greaterThan(value, length, "Value cannot more than " + length + " character!");
    }

    public static void greaterThan(String value, int length, String message) {
        isNull(value);
        checkArgument(value.length() <= length, message);
    }

    public static void lessThan(String value, int length) {
        lessThan(value, length, "Value cannot less than " + length + " character!");
    }

    public static void lessThan(String value, int length, String message) {
        isNull(value);
        checkArgument(value.length() >= length, message);
    }

    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new ValidationException(message);
        }
    }
}
