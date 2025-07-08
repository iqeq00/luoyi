package com.luoyi.example.jd.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 校验结果封装类
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult {
    private final boolean valid;
    private final Map<String, List<String>> errors;

    public List<String> getFieldErrors(String field) {
        return errors.getOrDefault(field, Collections.emptyList());
    }

    public boolean hasFieldErrors(String field) {
        return errors.containsKey(field);
    }

    public int getErrorCount() {
        return errors.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public String toString() {
        return toErrorMessage();
    }

    public String toErrorMessage() {
        if (errors.isEmpty()) {
            return "校验通过";
        }
        StringBuilder message = new StringBuilder();
        errors.forEach((field, fieldErrors) -> {
            fieldErrors.forEach(error -> {
                if (message.length() > 0) {
                    message.append("; ");
                }
                message.append(field).append(": ").append(error);
            });
        });
        return message.toString();
    }

    public static ValidationResult success() {
        return new ValidationResult(true, Collections.emptyMap());
    }

    public static ValidationResult failure(Map<String, List<String>> errors) {
        return new ValidationResult(false, errors != null ? Collections.unmodifiableMap(errors) : Collections.emptyMap());
    }

}