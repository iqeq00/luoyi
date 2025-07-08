package com.luoyi.example.jd.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Validation 校验工具
 *
 * @author yaojinchi
 */
public class ValidationUtils {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();



    private ValidationUtils() {
    }

    /**
     * 校验对象的指定字段
     */
    public static <T> ValidationResult validateFields(T object, List<String> fields) {

        checkValidatorInitialized();
        if (object == null || fields == null || fields.isEmpty()) {
            return ValidationResult.success();
        }
        Map<String, List<String>> results = new LinkedHashMap<>();
        for (String field : fields) {
            Set<ConstraintViolation<T>> violations = validator.validateProperty(object, field);
            if (!violations.isEmpty()) {
                results.put(field, violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
            }
        }
        return results.isEmpty() ? ValidationResult.success() : ValidationResult.failure(results);
    }

    /**
     * 校验整个对象
     */
    public static <T> ValidationResult validateObject(T object) {

        checkValidatorInitialized();
        if (object == null) {
            return ValidationResult.success();
        }
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (violations.isEmpty()) {
            return ValidationResult.success();
        }
        Map<String, List<String>> results = violations.stream().collect(Collectors.groupingBy(
                violation -> violation.getPropertyPath().toString(),
                Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())
        ));
        return ValidationResult.failure(results);
    }

    private static void checkValidatorInitialized() {

        if (validator == null) {
            throw new IllegalStateException("Validator 未初始化，请先调用 ValidationUtils.init()");
        }
    }

}