package pl.lostworld.lostworldbackend.validator.continent;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueContinentFieldValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueContinentField {
    String column();
    String message() default "{uniqueField.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
