package com.luoyi.example.jd.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 金额 Validation，自定义：扩展 Validation
 *
 * @author yaojinchi
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MoneyValidator.class})
@Documented
public @interface ValidMoney {

    String message() default "金额格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean allowNegative() default false;

    int maxFractionDigits() default 2;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ValidMoney[] value();
    }

}