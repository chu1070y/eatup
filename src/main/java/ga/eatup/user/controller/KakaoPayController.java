package ga.eatup.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.service.KakaoPay;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class KakaoPayController {
	
	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;
	
	
//	@PostMapping("/kakaoPay")
//	public String kakaoPay() {
//		log.info("kakaoPay post............................................");
//		
//		return "redirect:" + kakaopay.kakaoPayReady();
		
		
		@PostMapping(value="/kakaoPay", consumes="application/json")
		public ResponseEntity<String> kakaoPay(@RequestBody List<CartDTO> cartList){
//			Long totalPrice = 0L;
//			
//			for(CartDTO dto : cartList) {
//				totalPrice += (dto.getQuantity() * dto.getMprice());
//			};
//			totalPrice += 3000;
			// new ResponseEntity<>(kakaopay.kakaoPayReady(cartList), HttpStatus.OK);
		
			return	new ResponseEntity<>(kakaopay.kakaoPayReady(cartList), HttpStatus.OK);

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
