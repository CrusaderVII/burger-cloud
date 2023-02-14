package burgers.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import burgers.User;
import burgers.data.BurgerRepository;
import burgers.sevice.UserServices;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home() throws MessagingException, MalformedTemplateNameException, 
		ParseException, IOException {
		
		return "home";
	}
	
}
