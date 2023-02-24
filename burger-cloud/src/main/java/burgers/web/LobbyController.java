package burgers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import burgers.User;
import burgers.data.OrderRepository;
import burgers.data.UserRepository;
import burgers.security.EmailCodeVerification;
import burgers.security.UserLogin;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/lobby")
@SessionAttributes({"user", "emailVerification"})
@Slf4j
public class LobbyController {
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	UserRepository uRepo;
	
	@GetMapping
	public ModelAndView lobby(Model model) {
		EmailCodeVerification ver = new EmailCodeVerification((User) model.getAttribute("user"));
		
		model.addAttribute("emailVerification", ver);
		
		return new ModelAndView("lobby.html");
	}
	
	@GetMapping("/history")
	public ModelAndView lobbyHstory(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("usersOrders", oRepo.getAllById(user.getId()));
		return new ModelAndView("lobbyOrders.html");
	}
	
	@PostMapping
	public ModelAndView lobbyEmailVerification(@Valid @ModelAttribute("emailVerification") EmailCodeVerification ver, 
			Errors errors, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		if(errors.hasErrors()) {
			modelAndView.setViewName("lobby.html");
			return modelAndView;
		}
		
		uRepo.emailConfirm(ver.getUserName());;
		modelAndView.setViewName("lobby.html");
		return modelAndView;
	}

}
