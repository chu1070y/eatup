package ga.eatup.user.controller;

import javax.servlet.http.HttpServletRequest;

import ga.eatup.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/user/*")
@Log
public class UserController {
	
	@Setter(onMethod_=@Autowired)
	private MenuService service;
	
	@GetMapping("/speech")
	public void speech() {
		log.info("speech.......");
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("login....");
	}
	
	@GetMapping("/map")
	public void map() {
		log.info("map.....");
	}
	
	@GetMapping("/gorany")
	public void sample1(Model model) {
		
		model.addAttribute("menu", service.getMenu());
		
	}
	
	@GetMapping("/sample2")
	public void sample2(Model model) {
		log.info("sample2.....");
		
		model.addAttribute("menu", service.getMenu());
	}
	
	@GetMapping("/home")
	public void sample3(String location, Model model) {
	
		log.info("" + location);
		
		model.addAttribute("location", location);
		
	}
	
	@GetMapping("/store")
	public void index(Model model){
		
		model.addAttribute("menu", service.getMenu());
	
	}
	
	@GetMapping("/cart")
	public void cart(Model model){
		
		log.info("cartPage....");
		model.addAttribute("menu", service.getMenu());
	
	}
	
	@GetMapping("/pay")
	public void pay(Model model){
		
		log.info("payPage....");
		model.addAttribute("menu", service.getMenu());
	
	}
	
	@GetMapping("/firebase/test")
	public void testFirebase(HttpServletRequest request) {
		log.info("firebase....");
		
	}
	
}
