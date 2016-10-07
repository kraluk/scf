package com.kraluk.scf.server.util.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailValidatorTest {

    @Test
    public void shouldValidEmailTest() {
        boolean result = EmailValidator.validate("test@test.pl");

        assertTrue(result);
    }

    @Test
    public void shouldNotValidEmailTest() {
        boolean result = EmailValidator.validate("test@test");

        assertFalse(result);
    }
}