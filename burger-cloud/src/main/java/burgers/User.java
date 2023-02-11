package burgers;

import burgers.validation.ValidUserPasswordConfirm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@ValidUserPasswordConfirm
@Data
public class User {
	
	@NotNull
	@Size(min=3, message="Your name is way toooo short:(")
	private String userName;
	
	@NotNull
	@Pattern(regexp="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
		        message="We think, your email is not correct:(")
	private String userMail;
	
	@NotNull
	@Size(min=5, message="Your password is way toooo short:(")
	private String userPassword;
	private String userPasswordConfirm;
	
	
}
