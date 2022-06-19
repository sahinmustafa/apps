package com.my.user.attributes;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsernameTest {

    @Test
    void throwValidationExceptionIfUsernameNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> Username.of(null));
        assertEquals("Username cannot be null", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfUsernameLengthLessThanMinLength() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> Username.of(RandomStringUtils.random(2)));
        assertEquals("Username cannot be less than 3 character", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfUsernameLengthMoreThanMaxLength() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> Username.of(RandomStringUtils.random(21)));
        assertEquals("Username cannot be more than 20 character", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfUsernameNotValid() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> Username.of(RandomStringUtils.random(15)));
        assertEquals("Username not valid! Username cannot start with special character or cannot end. You can only use a-z A-Z 0-9 _-"
                , exception.getMessage());
    }

    @Test
    void createPasswordObjectIfEmailValid() {
        final String usernameStr = RandomStringUtils.randomAlphanumeric(10);
        Username username = Username.of(usernameStr);
        assertNotNull(username);
        assertEquals(usernameStr, username.getValue());
    }

}
