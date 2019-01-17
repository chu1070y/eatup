package ga.eatup.partner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ga.eatup.partner.domain.MenuVO;
import ga.eatup.partner.domain.NoticePageDTO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.partner.domain.OrderVO;
import ga.eatup.partner.domain.StoreVO;
import ga.eatup.partner.mapper.PartnerMenuMapper;
import ga.eatup.partner.mapper.PartnerOrderMapper;
import ga.eatup.partner.service.OpenService;
import ga.eatup.partner.service.PartnerMenuService;
import ga.eatup.partner.service.PartnerService;
import ga.eatup.partner.service.SuperadminService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/partner/*")
@Log
public class PartnerController {

	@Setter(onMethod_ = @Autowired)
	private PartnerService service;

	@Setter(onMethod_ = @Autowired)
	private SuperadminService superadminService;

	@Setter(onMethod_ = @Autowired)
	private OpenService openService;

	// order
	@Setter(onMethod_ = @Autowired)
	private PartnerOrderMapper ordermapper;

	// menu service
	@Setter(onMethod_ = @Autowired)
	private PartnerMenuService menuservice;

	// menu mapper
	@Setter(onMethod_ = @Autowired)
	private PartnerMenuMapper menumapper;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/welcome")
	public void welcome() {
		log.info("회원가입....");
	}

	@GetMapping("/login/customLogout")
	public void logout() {

	}

	@PostMapping("/login/customLogout")
	public void logoutPOST() {

	}

	@GetMapping("/index")
	public void index(Model model, OrderVO order, StoreVO store, Authentication authentication) {
		log.info("index......................page");

		String pid = authentication.getName();
		
		int sno = menuservice.getSno(pid);
		
		store.setSno(sno);

		model.addAttribute("sno", sno);

		
		System.out.println("-=------------------------------>" + model);

		List<String> tidlist = new ArrayList<String>();
		List<OrderVO> list = ordermapper.getOrder(order);

		model.addAttribute("result", list);

		System.out.println("리스트 사이즈: ," + list.size());

		for (int i = 0; i < list.size(); i++) {

			
			if (!tidlist.contains(list.get(i).getTid())) {
				tidlist.add(list.get(i).getTid());
			}
		}

		
		model.addAttribute("tidlist", tidlist);

		ordermapper.getOpen(store);

		store.setSno(sno);
		List<StoreVO> open = service.getOpen(store);
		
		model.addAttribute("open", open.get(0).getOpen());
		
	}

	@GetMapping("/menu")
	public void menu(Authentication authentication, Model model, MenuVO menu) {
		log.info("menu......................page");

		// 로그인 정보 끌어오는 것. pid로 sno를 뽑아서 sno에 해당하는 menu를 전달
		String pid = authentication.getName();
		
		int sno = menuservice.getSno(pid);
		

		List<MenuVO> list = menuservice.getMenu(sno);
		model.addAttribute("menu", list);

		

	}

	@PostMapping(value = "/menu", consumes = "application/json")
	public ResponseEntity<Integer> menu2(Authentication authentication, Model model, @RequestBody MenuVO menu) {
		log.info("menu post......................page");

		// 로그인 정보 끌어오는 것. pid로 sno를 뽑아서 sno에 해당하는 menu를 전달
		String pid = authentication.getName();
		
		int sno = menuservice.getSno(pid);
		

		List<MenuVO> list = menuservice.getMenu(sno);
		model.addAttribute("menu", list);

		
		
	
		
		
		
		if (menu.getSoldout().equals("X")) {
			
			
			menuservice.getSoldoutX(menu);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);

		} else if (menu.getSoldout().equals("O")) {
			 menuservice.getSoldoutO(menu);
			
			return new ResponseEntity<Integer>(1, HttpStatus.OK);

		}
		
		return new ResponseEntity<Integer>(1, HttpStatus.OK);

	}

	@Transactional
	@GetMapping("/superAdmin")
	public void superAdmin(Model model, NoticePageDTO dto) {
		log.info("superAdmin......................page");
		log.info("dto.." + dto);
		dto.setTotal(superadminService.noticeCount());

		model.addAttribute("noticeList", superadminService.noticeList(dto));
		model.addAttribute("dto", dto);

	}

	@Transactional
	@GetMapping("/notice")
	public void notice(Model model, NoticePageDTO dto) {
		log.info("notice......................page");
		log.info("superAdmin......................page");
		log.info("dto.." + dto);
		dto.setTotal(superadminService.noticeCount());

		model.addAttribute("noticeList", superadminService.noticeList(dto));
		model.addAttribute("dto", dto);
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

		model.addAttribute("notice", superadminService.noticeRead(dto.getNno()));
		model.addAttribute("dto", dto);
	}

	@GetMapping("/notice/modify")
	public void noticeModify(Model model, NoticePageDTO dto) {
		log.info("notice modify page....");
		model.addAttribute("notice", superadminService.noticeRead(dto.getNno()));
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

	@GetMapping("/notice/partnerRead")
	public void partnerRead(Model model, NoticePageDTO dto) {
		log.info("notice partner read page...." + dto.getNno());

		model.addAttribute("notice", superadminService.noticeRead(dto.getNno()));
		model.addAttribute("dto", dto);
	}

	
	@PostMapping(value = "/openx", consumes = "application/json")
	public ResponseEntity<String> updateX(Authentication authentication,@RequestBody StoreVO vo) {
		
		String pid = authentication.getName();
		
		int sno = menuservice.getSno(pid);
		
		vo.setSno(sno);
		
		

		return openService.updateX(sno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@PostMapping(value = "/openo", consumes = "application/json")
	public ResponseEntity<String> updateO(Authentication authentication,@RequestBody StoreVO vo) {
		
		String pid = authentication.getName();
		
		int sno = menuservice.getSno(pid);
		
		vo.setSno(sno);
		
		

		return openService.updateO(sno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// order complete
	@GetMapping("/orderComplete/{tid}")
	@ResponseBody
	public ResponseEntity<Integer> orderComplete(@PathVariable("tid") String tid) {
		log.info("orderComplete get.....");

		return new ResponseEntity<>(service.orderComplete(tid), HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/maxquantity", consumes = "application/json")
	@ResponseBody
	public int updatequantity(@RequestBody MenuVO vo, Authentication authentication) {

		// 로그인 정보 끌어오는 것. pid로 sno를 뽑아서 sno에 해당하는 menu를 전달
		String pid = authentication.getName();
		
		int sno = menuservice.getSno(pid);
		

		vo.setSno(sno);

		

		return menuservice.getupdateMaxQuantity(vo);
	}

	

}
