package ga.eatup.partner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ga.eatup.partner.domain.OrderVO;
import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.mapper.OrderMapper;
import ga.eatup.partner.service.PartnerService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/partner/*")
@Log
public class PartnerController {

	@Setter(onMethod_=@Autowired)
	private PartnerService service;
	
	//order
	@Setter(onMethod_= @Autowired)
	private OrderMapper ordermapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/welcome")
	public void welcome() {
		log.info("회원가입....");
	}
	
	@PostMapping("/partnercreate")
	public void partnercreate(PartnerVO vo) {
		log.info("파트너 계정생성 완료....");
		log.info("파트너 회원가입 정보: " + vo);
		
		String enPw = encoder.encode(vo.getPpw());
		vo.setPpw(enPw);		
		
		service.registerPartner(vo);
		service.registerAuth(vo);
	}
	
	@GetMapping("/index")
	public void index(Model model, OrderVO order) {
		log.info("index......................page");
		
		model.addAttribute("result", ordermapper.getOrder(order));
		System.out.println("-=------------------------------>"+model);
	}
	
	
	@GetMapping("/menu")
	public void menu() {
		log.info("menu......................page");
	}
	
	@GetMapping("/superAdmin")
	public void superAdmin() {
		log.info("superAdmin......................page");
	}
	
	@GetMapping("/notice")
	public void notice() {
		log.info("notice......................page");
	}

	
	@GetMapping("/oneByone")
	public void oneByone() {
		log.info("oneByone......................page");
	}
	
	@GetMapping("/information")
	public void information() {
		log.info("information......................page");
	}
	
	@GetMapping("/login/customLogin")
	public void customLogin() {
		log.info("custom login.........");
	}
	
}
