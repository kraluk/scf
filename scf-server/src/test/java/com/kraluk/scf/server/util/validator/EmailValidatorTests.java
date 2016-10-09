package com.kraluk.scf.server.util.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailValidatorTests {

    @Test
    public void validate_correctAddress_validated() {
        boolean result = EmailValidator.validate("test@test.pl");

        assertTrue(result);
    }

    @Test
    public void validate_incorrectAddress_notValidated() {
        boolean result = EmailValidator.validate("test@test");

        assertFalse(result);
    }
}