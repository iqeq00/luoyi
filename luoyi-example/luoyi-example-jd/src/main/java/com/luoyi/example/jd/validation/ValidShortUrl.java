package com.luoyi.example.jd.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 短链 Validation，自定义：扩展 Validation
 *
 * @author yaojinchi
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ShortUrlValidator.class})
@Documented
public @interface ValidShortUrl {

    String message() default "短链格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean allowOtherProtocols() default false;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ValidShortUrl[] value();
    }

}