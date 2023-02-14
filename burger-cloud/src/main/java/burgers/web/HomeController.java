package burgers.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import burgers.User;
import burgers.sevice.UserServices;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import jakarta.mail.MessagingException;

@Controller
public class HomeController {

	@Autowired
	private UserServices serv;
	
	@GetMapping("/")
	public String home() throws MessagingException, MalformedTemplateNameException, 
		ParseException, IOException {
		
		return "home";
	}
	
}
