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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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


@RestController
@RequestMapping("/order")
@SessionAttributes({"order", "user"})
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
	public ModelAndView order(@ModelAttribute("order") Order order) {
		return new ModelAndView("order/orderChoosing.html");
	}
	
	@GetMapping("/cart")	
	public ModelAndView orderCurrent(@ModelAttribute("order") Order order, Model model) {
		List<Burger> orderedBurgers = new ArrayList<>();
		order.getCart().stream()
			.forEach(i -> orderedBurgers.add(bRepo.getBurgerByCodeName(i)));
		
		model.addAttribute("orderedBurgers", orderedBurgers);
		
		return new ModelAndView("order/orderCart.html");
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Burger> burgers = bRepo.findAll();
		model.addAttribute("burgers", burgers);
	}
	
	@PostMapping("/cart")
	 public ModelAndView orderCurrentPost(@ModelAttribute("order") Order order, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		
		order.setAddres("Moscow");
		
		order.setPrice(calculatePrice(order));
		
		order.setOrdersDate(LocalDateTime.now());
		
		if (model.getAttribute("user")==null) {
			order.setUser(null);
			oRepo.save(order);
			for (String codeName : order.getCart()) {
				Reference ref = new Reference();
				ref.setBurger(bRepo.getBurgerByCodeName(codeName));
				ref.setOrder(order);
				rRepo.save(ref);
			}
			modelAndView.setViewName("redirect:/");
			
			return modelAndView;
		}else {
			order.setUser((User) model.getAttribute("user"));
		}
		log.info(""+order);
		oRepo.save(order);
		for (String codeName : order.getCart()) {
			Reference ref = new Reference();
			ref.setBurger(bRepo.getBurgerByCodeName(codeName));
			ref.setOrder(order);
			rRepo.save(ref);
		}
		modelAndView.setViewName("redirect:/lobby");
		
		return modelAndView;
	 }
	
	@PostMapping()
	 public ModelAndView OrderPost(@ModelAttribute("order") Order order, Model model) {		
		order.setPrice(calculatePrice(order));
		log.info(""+order);
		return new ModelAndView("redirect:/order/cart");
	 }
	
	public double calculatePrice(Order order) {
		return order.getCart().stream().mapToDouble(i-> bRepo.getBurgerByCodeName(i).getPrice()).sum();
	}
	

}
