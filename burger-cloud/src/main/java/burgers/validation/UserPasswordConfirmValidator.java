package burgers.validation;

import burgers.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserPasswordConfirmValidator implements ConstraintValidator<ValidUserPasswordConfirm, Object> {
    
    @Override
    public void initialize(ValidUserPasswordConfirm constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(Object user, ConstraintValidatorContext constraintValidatorContext) {
    	User check = (User) user;
    	if (check.getUserPasswordConfirm().isBlank()) return false;
    	return check.getUserPassword().equals(check.getUserPasswordConfirm());
    }

}
