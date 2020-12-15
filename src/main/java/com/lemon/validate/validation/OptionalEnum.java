package com.lemon.validate.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created on 2020/12/10 11:50.
 *
 * @author lemon
 */

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {OptionalEnumValidator.class})
@Documented
public @interface OptionalEnum {
    String message() default "";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};

    Class<? extends BaseEnum>[] values() default {};
}