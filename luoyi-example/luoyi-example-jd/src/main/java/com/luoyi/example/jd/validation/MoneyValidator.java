package com.luoyi.example.jd.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Money Validator
 *
 * @author yaojinchi
 */
public class MoneyValidator implements ConstraintValidator<ValidMoney, String> {

    private static final Pattern MONEY_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    private boolean allowNegative;
    private int maxFractionDigits;

    @Override
    public void initialize(ValidMoney constraintAnnotation) {
        this.allowNegative = constraintAnnotation.allowNegative();
        this.maxFractionDigits = constraintAnnotation.maxFractionDigits();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        if (!MONEY_PATTERN.matcher(value).matches()) {
            return false;
        }

        if (!allowNegative && value.startsWith("-")) {
            return false;
        }

        if (value.contains(".")) {
            String fractionPart = value.substring(value.indexOf(".") + 1);
            if (fractionPart.length() > maxFractionDigits) {
                return false;
            }
        }

        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}