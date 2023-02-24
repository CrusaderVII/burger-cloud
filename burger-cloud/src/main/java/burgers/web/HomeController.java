package burgers.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import burgers.Order;
import burgers.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@SessionAttributes({"order", "user"})
@Slf4j
public class HomeController {
	
	
	@GetMapping("/")
	public ModelAndView home(Model model, HttpSession session) {
		model.addAttribute("order", new Order());
		model.addAttribute("user", (User) session.getAttribute("user"));
		return new ModelAndView("home.html");
	}
	
}
