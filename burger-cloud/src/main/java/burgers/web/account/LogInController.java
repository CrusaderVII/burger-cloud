package burgers.web.account;

import java.io.UnsupportedEncodingException;

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
import burgers.sevice.UserServices;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
@SessionAttributes({"userLogin", "user"})
public class LogInController {
	
	@Autowired
	private UserRepository uRepo; 
	
	@Autowired
	private UserServices serv;
	
	@GetMapping
	 public String orderForm(Model model) throws UnsupportedEncodingException, MessagingException {
		model.addAttribute("userLogin", new UserLogin());
		return "logIn";
	 }
	
	
	
	@PostMapping
	 public String userLogging(@Valid @ModelAttribute("userLogin") UserLogin userLogin, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "logIn";
		}
		
		model.addAttribute("user", uRepo.getUserByUserName(userLogin.getUserLoginName()));
		return "redirect:/lobby";
	 }
}
