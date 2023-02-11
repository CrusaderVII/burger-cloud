package burgers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lobby")
public class LobbyController {
	
	@GetMapping
	public String home() {
		return "lobby";
	}

}
