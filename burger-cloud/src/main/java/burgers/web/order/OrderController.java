package burgers.web.order;

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
import jakarta.servlet.http.Cookie;
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
	public String order(@ModelAttribute("order") Order order) {
		return "order/orderChoosing.html";
	}
	
	@GetMapping("/cart")	
	public String orderCurrent(@ModelAttribute("order") Order order, Model model) {
		List<Burger> orderedBurgers = new ArrayList<>();
		order.getCart().stream()
			.forEach(i -> orderedBurgers.add(bRepo.getBurgerByCodeName(i)));
		
		model.addAttribute("orderedBurgers", orderedBurgers);
		
		return "order/orderCart.html";
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Burger> burgers = bRepo.findAll();
		model.addAttribute("burgers", burgers);
	}
	
	@PostMapping("/cart")
	 public String orderCurrentPost(@ModelAttribute("order") Order order, Model model) {
		
		order.setAddres("Moscow");
		
		order.setPrice(calculatePrice(order));
		
		order.setOrdersDate(LocalDateTime.now());
		log.info(""+order);
		/*oRepo.save(order);
		for (Burger burger : order.getChart()) {
			Reference ref = new Reference();
			ref.setBurger(burger);
			ref.setOrder(order);
			rRepo.save(ref);
		}*/
		return "redirect:/lobby";
	 }
	
	@PostMapping()
	 public String OrderPost(@ModelAttribute("order") Order order, Model model) {
		
//		order.setAddres("Moscow");
//		
//		
//		
//		order.setOrdersDate(LocalDateTime.now());
		
		order.setPrice(calculatePrice(order));
		log.info(""+order);
		return "redirect:/order/cart";
	 }
	
	public double calculatePrice(Order order) {
		return order.getCart().stream().mapToDouble(i-> bRepo.getBurgerByCodeName(i).getPrice()).sum();
	}
	

}
