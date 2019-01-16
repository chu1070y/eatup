package ga.eatup.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String user() {
		
		return "redirect:/user/home";
	}
	
	@GetMapping("/partner")
	public String partner() {
		
		return "redirect:/partner/index";
	}
}
