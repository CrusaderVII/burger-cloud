package burgers.web.account;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import burgers.sevice.UserServices;
import jakarta.mail.MessagingException;

@RestController
public class RegisterSuccessfulController {

	@Autowired
	private UserServices serv;
	
	@GetMapping("/register_success")
	public ModelAndView registerSuccessful(){
		
		return new ModelAndView("registerSuccessful.html");
	}
	
}
