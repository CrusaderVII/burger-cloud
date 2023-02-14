package burgers.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import burgers.Burger;
import burgers.Order;
import burgers.Reference;
import burgers.User;
import burgers.data.BurgerRepository;
import burgers.data.OrderRepository;
import burgers.data.ReferenceRepository;
import burgers.data.UserRepository;
import burgers.security.UserLogin;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/order")
@SessionAttributes("order")
@Slf4j
public class OrderController {
	
	@Autowired
	BurgerRepository bRepo;
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	ReferenceRepository rRepo;
	
	@GetMapping
	public String home(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("avaliableBurgers", bRepo.findAll());
		
		return "orderChoosing";
	}
	
	@PostMapping
	 public String userLogging(@ModelAttribute("order") Order order, 
			 @ModelAttribute("avaliableBurgers") Iterable<Burger> burgers) {
		
		order.setAddres("Moscow");
		
		order.setPrice(calculatePrice(order));
		
		order.setOrdersDate(LocalDateTime.now());
		/*oRepo.save(order);
		for (Burger burger : order.getChart()) {
			Reference ref = new Reference();
			ref.setBurger(burger);
			ref.setOrder(order);
			rRepo.save(ref);
		}*/
		return "redirect:/lobby";
	 }
	
	public static double calculatePrice(Order order) {
		return order.getChart().stream().mapToDouble(i->i.getPrice()).sum();
	}

}
