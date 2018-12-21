package ga.eatup.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.domain.UserVO;
import ga.eatup.user.service.LoginService;
import ga.eatup.user.service.MenuService;
import ga.eatup.user.service.StoreService;
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
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Autowired
	PasswordEncoder encoder;
	
	
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
	
	
	@PostMapping("/usercreate")
	public void usercreate(UserVO vo) {
		log.info("유저 계정생성 완료....");
		log.info("유저 회원가입 정보: " + vo);
		
		String enPw = encoder.encode(vo.getUpw());
		vo.setUpw(enPw);		
		
		loginService.registerUser(vo);
		loginService.registerAuth(vo);
	}
	
	@RequestMapping(value = "/welcome",
			method = {RequestMethod.POST, RequestMethod.GET})
	public void welcome(HttpServletRequest req, Model model) {
				
		//KakaoLoginController에서 FlashMap으로 전송한 데이터 받기 위해서는 request를 파라미터로 한 inputFlashMap 객체를 생성해야 함. 
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(req);
		log.info("inputFlashMap: " + inputFlashMap);
		
		
		//받은 정보를 UserVO 인스턴스 vo에 담아서 welcome.html(회원가입 페이지)로 전달. 
		UserVO vo = new UserVO();
		vo.setSns_id("0");
		if (inputFlashMap != null) {
			vo.setNickname((String) inputFlashMap.get("nickname"));
			vo.setEmail((String) inputFlashMap.get("email"));
			vo.setSns_id((String) inputFlashMap.get("snsId"));
			log.info("UserVO: " + vo);
		}
		model.addAttribute("vo", vo);
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
	
//		log.info("" + location);
		
		model.addAttribute("location", location);
		
		model.addAttribute("store",storeService.getStore());
		
	}
	
	@GetMapping("/store")
	public void store(@ModelAttribute("sno") int sno,Model model){
		
		model.addAttribute("menu", service.getMenu(sno));
	
	}
	
	@GetMapping("/cart")
	public void cart(@CookieValue("cart")String cart, @ModelAttribute("sno") int sno,Model model){
		
		log.info("cartPage....");
		log.info("cart:"+cart);
		Gson gson=new Gson();
		List<CartDTO> menu = gson.fromJson(cart, new TypeToken<List<CartDTO>>(){}.getType());
		
		menu.forEach(dto->{
			log.info(""+dto);
		});
		model.addAttribute("cart", menu);
		model.addAttribute("menu", service.getCart(sno));
	
	}
	
	@GetMapping("/pay")
	public void pay(@CookieValue("order")String order, @ModelAttribute("sno") int sno, Model model){
		
		log.info("payPage....");
		log.info("order:"+order);
		Gson gson=new Gson();
		List<MenuVO> menu=gson.fromJson(order, new TypeToken<List<MenuVO>>(){}.getType());
		
		menu.forEach(vo->{
			log.info(""+vo);
		});
		
		model.addAttribute("menu", service.getCart(sno));
	
	}
	
	@GetMapping("/firebase/test")
	public void testFirebase(HttpServletRequest request) {
		log.info("firebase....");
		
	}
	
	@GetMapping("/firebase/test2")
	public void testFirebase2(HttpServletRequest request) {
		log.info("firebase....test2");
		
	}
	
	@GetMapping("/search")
	public void search() {
		log.info("search page......");
		
	}
	
}
