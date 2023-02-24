package burgers.security;

import burgers.User;
import burgers.validation.ValidEmailCode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ValidEmailCode
public class EmailCodeVerification {

	private String userName;
	
	private boolean isVerified;
	
	@NotBlank(message="Type something:(")
	private String emailCode;
	
	public EmailCodeVerification(User user) {
		this.userName = user.getUserName();
		this.isVerified = user.isEmailIsVerified();
	}
}
