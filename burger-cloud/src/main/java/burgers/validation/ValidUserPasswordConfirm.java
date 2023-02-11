package burgers.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = UserPasswordConfirmValidator.class)
@Documented
public @interface ValidUserPasswordConfirm {
	
    String message() default "Passwords must match:(";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
