package com.lemon.validate.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created on 2020/12/10 17:13.
 *
 * @author lemon
 */
public class OptionalEnumValidator implements ConstraintValidator<OptionalEnum, Object> {

    private BaseEnum[] enums;

    @Override
    public void initialize(OptionalEnum constraintAnnotation) {
        enums = Arrays.stream(constraintAnnotation.values()).flatMap(cls -> Arrays.stream(cls.getEnumConstants())).toArray(BaseEnum[]::new);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 为null 不校验  与枚举实列 name或code作比较
        return Objects.isNull(value) || Arrays.stream(enums).anyMatch(e -> e.getCode().equals(value) || Objects.equals(e.name(),value));
    }
}
