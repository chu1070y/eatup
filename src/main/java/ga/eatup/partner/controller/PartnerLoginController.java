package ga.eatup.partner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.domain.StoreVO;
import ga.eatup.partner.service.PartnerService;
import ga.eatup.user.service.LoginService;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/partner/*")
public class PartnerLoginController {
	
	@Setter(onMethod_ = @Autowired)
	PartnerService service;
	
	@Setter(onMethod_ = @Autowired)
	LoginService loginService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Transactional
	@GetMapping("/welcome/{pid}")
	@ResponseBody
	public ResponseEntity<Integer> checkId(@PathVariable("pid") String pid){
		log.info("checkId get.....");
		
		int userCheck = loginService.checkId(pid);
		int partnerCheck = service.checkId(pid);
		
		int result = 0;
		if(userCheck == 1 || partnerCheck == 1) {
			result = 1;
		}
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	@PostMapping("/partnercreate")
	public String partnercreate(PartnerVO partnerVO, StoreVO storeVO, RedirectAttributes redirect) {
		log.info("유저 계정생성 완료....");
		log.info("유저 회원가입 정보: " + partnerVO);
		log.info("유저 회원가입 정보: " + storeVO);
		
		String enPw = encoder.encode(partnerVO.getPpw());
		partnerVO.setPpw(enPw);		

		redirect.addFlashAttribute("welcome", service.insertPartner(partnerVO, storeVO));
		
		return "redirect:/partner/login/customLogin";
	}

}
