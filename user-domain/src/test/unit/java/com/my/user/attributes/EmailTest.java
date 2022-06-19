package com.my.user.attributes;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailTest {
    @Test
    void throwValidationExceptionIfEMailNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> EMailAddress.of(null));
        assertEquals("e-mail cannot be null", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfEmailLengthMoreThanMaxLength() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> EMailAddress.of(RandomStringUtils.random(101)));
        assertEquals("e-mail cannot be more than 100 character", exception.getMessage());
    }

    @Test
    void throwValidationExceptionIfEmailNotValid() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> EMailAddress.of(RandomStringUtils.random(15)));
        assertEquals("Invalid e-mail", exception.getMessage());
    }

    @Test
    void createPasswordObjectIfEmailValid() {
        final String mail = RandomStringUtils.randomAlphanumeric(10);
        final String domain = RandomStringUtils.randomAlphanumeric(5);
        final String email = mail + "@" + domain + ".com";
        EMailAddress eMailAddress = EMailAddress.of(email);
        assertNotNull(eMailAddress);
        assertEquals(email, eMailAddress.getValue());
    }
}
