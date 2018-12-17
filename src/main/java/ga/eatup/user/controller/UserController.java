package ga.eatup.user.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

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
	private MenuService service;
	
	@Setter(onMethod_=@Autowired)
	private LoginService loginService;
	
	@GetMapping("/speech")
	public void speech() {
		log.info("speech.......");
	}
	
	@GetMapping("/login/customLoginTemp")
	public void temptemp(@RequestParam String username, @RequestParam String password, Model model) {
		log.info("===========임시 로그인페이지 ===============");
		log.info(username);
		log.info(password);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
	}
	
	@PostMapping("/login/customLogin")
	public void customLogin(UserVO vo, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws IOException {

		log.info("vo입니당: " + vo );

        String inputUid = vo.getUid();
        String inputPw = vo.getUpw();
        
        UserVO dbVo = loginService.getUser(inputUid);
 
        String uId = dbVo.getUid();
        String uPw = dbVo.getUpw();
        
        log.info("" + (inputPw =="12345678"));
        log.info("" + (uPw =="12345678"));
        log.info("같은지 체크: " + inputPw.equals(uPw));

        if (!inputPw.equals(uPw)) {
            response.sendRedirect("/user/pay");
        } 
        
        else {
            response.sendRedirect("/user/home");		
        }

	}
	
	
	@GetMapping("/login/customLogin")
	public void login() {
		
		
	}
	
	@GetMapping("/welcome")
	public void welcome(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
		
		Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
		String nickname = null;
		if(redirectMap != null) {
			modelMap.put("userVO", (String)redirectMap.get("nickname"));
		}
		log.info("--------------------------" +  modelMap);
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
