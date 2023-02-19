package burgers.web;

import java.io.IOException;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.Order;
import burgers.User;
import burgers.data.BurgerRepository;
import burgers.sevice.UserServices;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("order")
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("order", new Order());
		
		return "home";
	}
	
}
