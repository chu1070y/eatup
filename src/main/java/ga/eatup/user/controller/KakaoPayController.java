package ga.eatup.user.controller;

import ga.eatup.user.service.KakaoPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class KakaoPayController {
	
	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;
	
	@PostMapping("/kakaoPay")
	public String kakaoPay() {
		log.info("kakaoPay post............................................");
		
		return "redirect:" + kakaopay.kakaoPayReady();

	}
	
	@GetMapping("/kakaopay/kakaoPaySuccess")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		log.info("kakaoPaySuccess get............................................");
		log.info("kakaoPaySuccess pg_token : " + pg_token);
		
		model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
	}
	
	@GetMapping("/kakaopay/kakaoPayFail")
	public void kakaoPayFail() {
		log.info("kakaoPayFail get............................................");
		
	}
	
	@GetMapping("/kakaopay/kakaoPayCancel")
	public void kakaoPayCancel() {
		log.info("kakaoPayCancel get............................................");
		
	}
	

}
