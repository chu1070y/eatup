package ga.eatup.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ga.eatup.user.domain.UserVO;
import ga.eatup.user.service.LoginService;
import ga.eatup.user.service.MenuService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/user/*")
@Log
public class UserController {
	
	@Setter(onMethod_=@Autowired)
	private MenuService menuService;
	
	@Setter(onMethod_=@Autowired)
	private LoginService loginService;
	
	
	@GetMapping("/speech")
	public void speech() {
		log.info("speech.......");
	}
	
	@GetMapping("/login/customLogin")
	public void customLogin() {
		log.info("customLogin");
	}
	
	@PostMapping("/login/customLogin")
	public void customLoginSuccess(UserVO vo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		
		String inputUid = vo.getUid();
		String inputPw = vo.getUpw();
		
		String Upw = loginService.getUser(inputUid).getUpw();
		
		if (inputPw != Upw) {
			response.sendRedirect("/user/login/customLogin");
		}
		
		
		
		/*
		if 같지 않으면 다시 돌리기. 
		같으면 home으로 redirect 시키기. 
		*/
		
		
		//session으로 계정 데이터 계속해서 물고 가야함.(아직 구현 X)
		
		
		//원래는 모든 페이지에서 로그인이 가능해야하고 로그인 후 그 페이지로 돌려줘야겠지만
		//일단은 홈에서 하는 경우만 생각하고 만듦. 
		response.sendRedirect("/user/home");

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
		
		model.addAttribute("menu", menuService.getMenu());
		
	}
	
	@GetMapping("/sample2")
	public void sample2(Model model) {
		log.info("sample2.....");
		
		model.addAttribute("menu", menuService.getMenu());
	}
	
	@GetMapping("/home")
	public void sample3(String location, Model model) {
	
		log.info("" + location);
		
		model.addAttribute("location", location);
		
	}
	
	@GetMapping("/store")
	public void index(Model model){
		
		model.addAttribute("menu", menuService.getMenu());
	
	}
	
	@GetMapping("/cart")
	public void cart(Model model){
		
		log.info("cartPage....");
		model.addAttribute("menu", menuService.getMenu());
	
	}
	
	@GetMapping("/pay")
	public void pay(Model model){
		
		log.info("payPage....");
		model.addAttribute("menu", menuService.getMenu());
	
	}
	
	@GetMapping("/firebase/test")
	public void testFirebase(HttpServletRequest request) {
		log.info("firebase....");
		
	}
	
}
