package burgers.web.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import freemarker.ext.beans.EnumerationModel;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/order")
@SessionAttributes({"order", "user", "orderedBurgers"})
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
	public ModelAndView order(@ModelAttribute("order") Order order, Model model, HttpSession session) {
		session.setAttribute("insertFlagAddress", false);
		session.setAttribute("insertFlagCreditCard", false);
		return new ModelAndView("order/orderChoosing.html");
	}
	
	@GetMapping("/cart")	
	public ModelAndView orderCurrent(@ModelAttribute("order") Order order, HttpSession session, 
			Errors errors, Model model) {
		model.addAttribute("insertFlagAddress", (boolean)session.getAttribute("insertFlagAddress"));
		model.addAttribute("insertFlagCreditCard", (boolean)session.getAttribute("insertFlagCreditCard"));
		List<Burger> orderedBurgers = new ArrayList<>();
		order.getCart().stream()
			.forEach(i -> orderedBurgers.add(bRepo.getBurgerByCodeName(i)));
		
		Map<Burger, Integer> orderedBurgersMap = sortBurgers(orderedBurgers);
		
		model.addAttribute("orderedBurgers", orderedBurgersMap);
		log.info(order.getAddress());
		return new ModelAndView("order/orderCart.html");
	}
	
	@GetMapping("/cart/delete_burger")	
	public ModelAndView orderCurrentDelete(@ModelAttribute("order") Order order, Model model,
			@RequestParam(name="codeName") String codeName, HttpSession session) {
		
		List<String> codeNames = new ArrayList<>(); 
		
		List<Burger> orderedBurgers = new ArrayList<>();
		order.getCart().stream()
			.forEach(i -> orderedBurgers.add(bRepo.getBurgerByCodeName(i)));
		
		orderedBurgers.remove(bRepo.getBurgerByCodeName(codeName));
		
		orderedBurgers.stream().forEach(i -> codeNames.add(i.getCodeName()));
		Map<Burger, Integer> orderedBurgersMap = sortBurgers(orderedBurgers);
		
		order.setCartFull(codeNames);
		order.setPrice(calculatePrice(order));
		model.addAttribute("orderedBurgers", orderedBurgersMap);
		model.addAttribute("order", order);
		return new ModelAndView("redirect:/order/cart");
	}
	
	@GetMapping("/cart/insert_data")	
	public ModelAndView insertUserData(@ModelAttribute("order") Order order, @ModelAttribute("user") User user,
			Model model, HttpSession session) {
		if(user.getAddress()!=null) {
			order.setAddress(user.getAddress());
			
			session.setAttribute("insertFlagAddress", true);
		}
		if(user.getCreditCard()!=null) {
			order.setCcNumber(user.getCreditCard().getCcNumber());
			order.setCcExperaion(user.getCreditCard().getCcExperation());
			order.setCcCVV(user.getCreditCard().getCcCVV());
			
			session.setAttribute("insertFlagCreditCard", true);
		}
		
		
		return new ModelAndView("redirect:/order/cart");
	}
	
	@ModelAttribute
	public void addBurgersToModel(Model model) {
		Iterable<Burger> burgers = bRepo.findAll();
		model.addAttribute("burgers", burgers);
	}
	
	@PostMapping("/cart")
	 public ModelAndView orderCurrentPost(@Valid @ModelAttribute("order") Order order, Errors errors, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		if (errors.hasErrors()) {
			modelAndView.setViewName("order/orderCart.html");
			return modelAndView;
		}
		order.setPrice(calculatePrice(order));
		
		order.setOrdersDate(LocalDateTime.now());
		log.info(order+"");
		
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
		} else {
			log.info(order+"");
			order.setUser((User) model.getAttribute("user"));
			//log.info(""+order);
			oRepo.save(order);
			for (String codeName : order.getCart()) {
				Reference ref = new Reference();
				ref.setBurger(bRepo.getBurgerByCodeName(codeName));
				ref.setOrder(order);
				rRepo.save(ref);
			}
		
		}
		modelAndView.setViewName("redirect:/lobby");
		
		return modelAndView;
	 }
	
	@PostMapping()
	 public ModelAndView OrderPost(@ModelAttribute("order") Order order, Model model) {		
		order.setPrice(calculatePrice(order));
		
		return new ModelAndView("redirect:/order/cart");
	 }
	
	//Methods
	public double calculatePrice(Order order) {
		return order.getCart().stream().mapToDouble(i-> bRepo.getBurgerByCodeName(i).getPrice()).sum();
	}
	
	public Map<Burger, Integer> sortBurgers(List<Burger> orderedBurgers) {
		Map<Burger, Integer> res = new HashMap<>(); 
		
		orderedBurgers.stream().forEach(i -> res.put(i, res.containsKey(i)? 
				res.get(i)+1 : 1));
		
		return res;
	}
	

}
