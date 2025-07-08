package com.luoyi.example.jd.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * ShortUrl Validator
 *
 * @author yaojinchi
 */
public class ShortUrlValidator implements ConstraintValidator<ValidShortUrl, String> {

    private static final Pattern URL_PATTERN = Pattern.compile("^(((ht|f)tps?):\\/\\/).*$");

    private boolean allowOtherProtocols;

    @Override
    public void initialize(ValidShortUrl constraintAnnotation) {
        this.allowOtherProtocols = constraintAnnotation.allowOtherProtocols();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        String url = value.trim();

        if (allowOtherProtocols) {
            return true;
        }

        return URL_PATTERN.matcher(url).matches();
    }

}