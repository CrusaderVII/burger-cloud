package burgers.web;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import burgers.sevice.UserServices;
import jakarta.mail.MessagingException;

@Controller
public class RegisterSuccessfulController {

	@Autowired
	private UserServices serv;
	
	@GetMapping("/register_success")
	public String home() throws UnsupportedEncodingException, MessagingException {
		
		return "registerSuccessful";
	}
	
}
