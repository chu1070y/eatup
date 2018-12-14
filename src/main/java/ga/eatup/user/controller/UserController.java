package ga.eatup.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//        
//        //이건 카카오 구현할 때 쓸 것. 아직 하지 않음. 카카오에서 정보 가져와서 특정 칸에 넣어주는 건 해야함. 흑. 
//        if (Uid == null) {
//        	response.sendRedirect("/user/welcome/");
//        }
        
        /*
        if 같지 않으면 다시 돌리기. 
        같으면 home으로 redirect 시키기. 
        */
                
        //session으로 계정 데이터 계속해서 물고 가야함.(아직 구현 X)
              
        //원래는 모든 페이지에서 로그인이 가능해야하고 로그인 후 그 페이지로 돌려줘야겠지만
        //일단은 홈에서 하는 경우만 생각하고 만듦. 
//        response.sendRedirect("/user/home");		
	}
	
	
	@GetMapping("/login/customLogin")
	public void login() {
		
		
	}
	
	@GetMapping("/welcome")
	public void welcome() {
		
	}
	
	@GetMapping("/map")
	public void map() {
		log.info("map.....");
	}
	
	@GetMapping("/gorany")
	public void gorany(@ModelAttribute("sno") int sno, Model model) {
		
		model.addAttribute("menu", service.getMenu(sno));
		
	}
	
	@GetMapping("/sample2")
	public void sample2(@ModelAttribute("sno") int sno,Model model) {
		log.info("sample2.....");
		
		model.addAttribute("menu", service.getMenu(sno));
	}
	
	@GetMapping("/home")
	public void home(String location, Model model) {
	
		log.info("" + location);
		
		model.addAttribute("location", location);
		
	}
	
	@GetMapping("/store")
	public void store(@ModelAttribute("sno") int sno,Model model){
		
		model.addAttribute("menu", service.getMenu(sno));
	
	}
	
	@GetMapping("/cart")
	public void cart(@ModelAttribute("sno") int sno,Model model){
		
		log.info("cartPage....");
		model.addAttribute("menu", service.getMenu(sno));
	
	}
	
	@GetMapping("/pay")
	public void pay(@ModelAttribute("sno") int sno, Model model){
		
		log.info("payPage....");
		model.addAttribute("menu", service.getMenu(sno));
	
	}
	
	@GetMapping("/firebase/test")
	public void testFirebase(HttpServletRequest request) {
		log.info("firebase....");
		
	}
	
}
