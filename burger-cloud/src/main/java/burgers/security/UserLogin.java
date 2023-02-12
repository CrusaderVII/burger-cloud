package burgers.security;

import burgers.validation.ValidLoggingIn;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ValidLoggingIn
public class UserLogin {
	
	@NotBlank(message="Type something:(")
	private String userLoginName;
	
	@NotBlank(message="Type something:(")
	private String userLoginPassword;
	
}
