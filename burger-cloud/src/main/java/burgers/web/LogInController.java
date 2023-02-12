package burgers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.User;
import burgers.data.UserRepository;
import burgers.security.UserLogin;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
@SessionAttributes("userLogin")
public class LogInController {
	
	@Autowired
	private UserRepository repo; 
	
	@GetMapping
	 public String orderForm(Model model) {
		model.addAttribute("userLogin", new UserLogin());
		return "logIn";
	 }
	
	@PostMapping
	 public String userLogging(@Valid @ModelAttribute("userLogin") UserLogin userLogin, Errors errors) {
		if (errors.hasErrors()) {
			return "logIn";
		}
		
		
		
		return "redirect:/lobby";
	 }
}
