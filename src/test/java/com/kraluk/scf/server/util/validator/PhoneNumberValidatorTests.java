package com.kraluk.scf.server.util.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PhoneNumberValidatorTests {

    @Test
    public void testValidateWithCorrectNumber() {
        boolean result = PhoneNumberValidator.validate("500600700");

        assertTrue(result);
    }

    @Test
    public void testValidateWithIncorrectNumber() {
        boolean result = PhoneNumberValidator.validate("1234567");

        assertFalse(result);
    }
}