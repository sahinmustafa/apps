package com.my.user.attributes;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    void throwValidationExceptionIfPasswordNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> Password.of(null));
        assertEquals("Password cannot be null", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfPasswordLengthLessThanMinLength() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> Password.of(RandomStringUtils.random(2)));
        assertEquals("Password cannot be less than 3 character", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfPasswordLengthMoreThanMaxLength() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> Password.of(RandomStringUtils.random(501)));
        assertEquals("Password cannot be more than 500 character", exception.getMessage());
    }

    @Test
    void createPasswordObjectIfPasswordValid() {
        String passValue = RandomStringUtils.random(10);
        Password pass = Password.of(passValue);
        assertNotNull(pass);
        assertEquals(passValue, pass.getValue());
    }
}
