package burgers.web.account;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import burgers.Order;
import burgers.User;
import burgers.data.UserRepository;
import burgers.security.UserLogin;
import burgers.sevice.UserServices;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
@SessionAttributes({"userLogin", "user", "order"})
public class LogInController {
	
	@Autowired
	private UserRepository uRepo; 
	
	@GetMapping
	 public ModelAndView orderForm(Model model, HttpServletRequest request, 
			 HttpSession session) throws UnsupportedEncodingException, MessagingException {
		model.addAttribute("user", null);
		session.setAttribute("user", null);
		model.addAttribute("order", new Order());
		model.addAttribute("userLogin", new UserLogin());
		return new ModelAndView("logIn.html");
	 }
	
	
	
	@PostMapping
	 public ModelAndView userLogging(@Valid @ModelAttribute("userLogin") UserLogin userLogin, Errors errors, 
			 Model model) {
		ModelAndView modelAndView = new ModelAndView();
		if (errors.hasErrors()) {
			modelAndView.setViewName("logIn.html");
			return modelAndView;
		}
		
	    
		
		model.addAttribute("user", uRepo.getUserByUserName(userLogin.getUserLoginName()));
		modelAndView.setViewName("redirect:/lobby");
		return modelAndView;
	 }
}
