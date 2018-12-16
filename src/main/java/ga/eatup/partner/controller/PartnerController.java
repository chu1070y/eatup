package ga.eatup.partner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ga.eatup.partner.domain.NoticePageDTO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.partner.domain.OrderVO;
import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.mapper.OrderMapper;
import ga.eatup.partner.service.PartnerService;
import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.mapper.MenuMapper;
import ga.eatup.user.service.MenuService;
import ga.eatup.partner.service.SuperadminService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/partner/*")
@Log
public class PartnerController {
	
	@Setter(onMethod_=@Autowired)
	private PartnerService service;
	
	@Setter(onMethod_=@Autowired)
	private SuperadminService superadminService;
	
	//order
	@Setter(onMethod_= @Autowired)
	private OrderMapper ordermapper;
	
	//menu service
	@Setter(onMethod_=@Autowired)
	private MenuService menuservice;
	
	//menu mapper
	@Setter(onMethod_=@Autowired)
	private MenuMapper menumapper;
	
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
		
		/*model.addAttribute("result", ordermapper.getOrder(order));*/
		System.out.println("-=------------------------------>"+model);
		
		List<String> tidlist = new ArrayList<String>();
		List<OrderVO> list  = ordermapper.getOrder(order);
		
		model.addAttribute("result", list);
		
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
	public void menu(@ModelAttribute("sno") int sno, Model model) {
		log.info("menu......................page");
		
		List<MenuVO> list = menuservice.getMenu(sno);
		model.addAttribute("partner", list);
		
		log.info("메뉴 : " + list);
		
	}
	
	
	@GetMapping("/superAdmin")
	public void superAdmin(Model model, NoticePageDTO dto){
		log.info("superAdmin......................page");
		log.info("dto.." + dto);
		dto.setTotal(superadminService.noticeCount());
		
		model.addAttribute("noticeList", superadminService.noticeList(dto));
		model.addAttribute("dto", dto);
		
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
	public void noticeRead(Model model, NoticePageDTO dto) {
		log.info("notice read page...." + dto.getNno());
		
		model.addAttribute("notice",superadminService.noticeRead(dto.getNno()));
		model.addAttribute("dto", dto);
	}
	
	@GetMapping("/notice/modify")
	public void noticeModify(Model model, NoticePageDTO dto) {
		log.info("notice modify page....");
		model.addAttribute("notice",superadminService.noticeRead(dto.getNno()));
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/notice/modify")
	public String noticeModifyPost(RedirectAttributes redirect, NoticeVO vo, NoticePageDTO dto) {
		log.info("notice modify post....." + dto);
		log.info("notice modify post....." + vo);
		
		int result = superadminService.noticeModify(vo);
		
		redirect.addFlashAttribute("result", result);
		
		return dto.getLink("redirect:/partner/notice/read");
	}
	
	@PostMapping("/notice/remove")
	public String noticeRemovePost(RedirectAttributes redirect, NoticeVO vo) {
		log.info("notice remove post....." + vo);
		
		int result = superadminService.noticeRemove(vo);
		
		redirect.addFlashAttribute("result", result);
		
		return "redirect:/partner/superAdmin";
	}
	
}
