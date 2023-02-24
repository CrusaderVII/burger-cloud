package burgers.validation;

import org.springframework.beans.factory.annotation.Autowired;

import burgers.data.UserRepository;
import burgers.security.EmailCodeVerification;
import burgers.security.UserLogin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailCodeValidator implements ConstraintValidator<ValidEmailCode, Object> {
	
	@Autowired
	private UserRepository repo;
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		EmailCodeVerification check = (EmailCodeVerification) value;
		
		if (!repo.getUserByUserName(check.getUserName())
				.getVerificationCode()
				.equals(check.getEmailCode())) {
			return false;
		}
		
		return true;
	}

}
