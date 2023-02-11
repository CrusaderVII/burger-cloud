package burgers.web;

import org.apache.catalina.core.ApplicationContext;
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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/register")
@Slf4j
@SessionAttributes("user")
public class RegisterController {
	
	@Autowired
	UserRepository repo;
	
	@GetMapping
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping
	 public String userRegistration(@ModelAttribute("user") @Valid User user, Errors errors) {
		if (errors.hasErrors()) {
			log.info("Error: " + errors.getGlobalErrorCount());
			 return "register";
		}
		
		repo.save(user);
		log.info("New user created: " + user);
		return "redirect:/lobby";
	 }
	
}
