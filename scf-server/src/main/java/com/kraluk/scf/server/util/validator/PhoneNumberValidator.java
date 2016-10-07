package com.kraluk.scf.server.util.validator;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Phone Number Validation Utility.
 * <br/>
 * Specialized only in polish cell numbers, i.e. nine digits numbers
 *
 * @author lukasz
 */
@UtilityClass
public class PhoneNumberValidator {
    private static final String REGEX_PATTERN = "[0-9]{9}";
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);

    public static boolean validate(String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}