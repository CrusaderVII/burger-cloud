package burgers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.User;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/lobby")
@SessionAttributes("user")
@Slf4j
public class LobbyController {
	
	@GetMapping
	public String home() {
		return "lobby";
	}

}
