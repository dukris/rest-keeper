package by.bsuir.restkeeper.web.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ImageFileValidator.class})
public @interface ValidImage {

    /**
     * Get message.
     *
     * @return Message
     */
    String message() default "Invalid image file!";

    /**
     * Get groups.
     *
     * @return Groups
     */
    Class<?>[] groups() default {};

    /**
     * Get payload.
     *
     * @return Payload
     */
    Class<? extends Payload>[] payload() default {};

}
