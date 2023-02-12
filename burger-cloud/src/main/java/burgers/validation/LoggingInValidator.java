package burgers.validation;

import org.springframework.beans.factory.annotation.Autowired;

import burgers.data.UserRepository;
import burgers.security.UserLogin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoggingInValidator implements ConstraintValidator<ValidLoggingIn, Object> {
	
	@Autowired
	private UserRepository repo;
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserLogin check = (UserLogin) value;
		if (repo.userIsExistingByName(check.getUserLoginName())==0) return false;
		
		if (!check.getUserLoginPassword()
				.equals(repo.getUserPassword(repo.getUserByUserName(check.getUserLoginName())))) {
			return false;
		}
		return true;
	}

}
