package burgers.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.Order;
import burgers.User;
import burgers.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/lobby")
@SessionAttributes("user")
@Slf4j
public class LobbyController {
	
	@Autowired
	OrderRepository oRepo;
	
	@GetMapping
	public String lobby(Model model) {
		return "lobby";
	}
	
	@GetMapping("/history")
	public String lobbyHstory(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("usersOrders", oRepo.getAllById(user.getId()));
		return "lobbyOrders";
	}

}
