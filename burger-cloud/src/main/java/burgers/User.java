package burgers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import burgers.validation.ValidUserPasswordConfirm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ValidUserPasswordConfirm
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@ToString
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false)
	@Size(min=3, message="Your name is way toooo short:(")
	private String userName;
	
	@Column(name = "email", nullable = false)
	@Pattern(regexp="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
		        message="We think, your email is not correct:(")
	private String userMail;
	
	@Column(name = "password", nullable = false)
	@Size(min=5, message="Your password is way toooo short:(")
	private String userPassword;
	
	@Transient
	private String userPasswordConfirm;
		
	private boolean emailIsVerified;
	
	@Column (name = "verificationCode", length = 64)
	private String verificationCode;

}
