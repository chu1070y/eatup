package ga.eatup.partner.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	@PostMapping("/usercreate")
	public void usercreate() {
		log.info("유저 계정생성 완료....");
	}
	
	@GetMapping("/index")
	public void index(Model model, OrderVO order) {
		log.info("index......................page");
		
		model.addAttribute("result", ordermapper.getOrder(order));
		System.out.println("-=------------------------------>"+model);
		
		List<String> tidlist = new ArrayList<String>();
		List<OrderVO> list  = ordermapper.getOrder(order);
		
		System.out.println("사이쥬ㅜ를 알려드리조,,," + list.size());
		
		
		for(int i=0; i<list.size(); i++) {
			
			//System.out.println(list.get(i).getTid());
			if(!tidlist.contains(list.get(i).getTid())) {
			tidlist.add(list.get(i).getTid());
			}
		}
		
		
		System.out.println(tidlist);
		model.addAttribute("tidlist", tidlist);
		
		
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
	
	@GetMapping("/notice/register")
	public void noticeRegister() {
		log.info("notice register page....");
	}
	
	@GetMapping("/notice/read")
	public void noticeRead() {
		log.info("notice read page....");
	}
	
	@GetMapping("/notice/modify")
	public void noticeModify() {
		log.info("notice modify page....");
	}
	
}
