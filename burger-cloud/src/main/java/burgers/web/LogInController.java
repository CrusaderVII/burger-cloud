package burgers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.User;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
@SessionAttributes("user")
public class LogInController {
	
	@GetMapping
	 public String orderForm(Model model) {
		model.addAttribute("user", new User());
		return "logIn";
	 }
	
	@PostMapping
	 public String userRegistration(User user) {
		log.info("User : " + user);
		 return "redirect:/lobby";
	 }
}
