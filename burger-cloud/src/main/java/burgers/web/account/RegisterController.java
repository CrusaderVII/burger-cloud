package burgers.web.account;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.User;
import burgers.data.UserRepository;
import burgers.sevice.UserServices;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/register")
@Slf4j
@SessionAttributes("user")
public class RegisterController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
    UserServices service;
	
	@GetMapping
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping
	 public String userRegistration(@ModelAttribute("user") @Valid User user, Errors errors) 
			 throws MessagingException, MalformedTemplateNameException, ParseException, IOException{
		if (errors.hasErrors()) {
			log.info("Error: " + errors.getGlobalErrorCount());
			 return "register";
		}
		service.register(user);
		log.info("New user created: " + user);
		return "redirect:/register_success";
	 }
	
	
}
