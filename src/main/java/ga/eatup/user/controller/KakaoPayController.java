package ga.eatup.user.controller;

import java.util.List;
import java.util.Map;

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
import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.domain.kakaopay.KakaoPayApprovalVO;
import ga.eatup.user.service.KakaoPay;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class KakaoPayController {
	
	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;

	
		
		@PostMapping(value="/kakaoPay", consumes="application/json")
		public ResponseEntity<String> kakaoPay(@RequestBody List<CartDTO> cartList){
			int totalPrice = 0;
			
			for(CartDTO dto : cartList) {
				totalPrice += (dto.getQuantity() * dto.getMprice());
			};
		
			log.info("============================================================================");
			log.info("totalPrice: "+ totalPrice);
			log.info("cartList: " + cartList);
			return new ResponseEntity<>(kakaopay.kakaoPayReady(totalPrice, cartList), HttpStatus.OK);

	}
		
	
	@GetMapping("/kakaopay/kakaoPaySuccess")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		
		Map<String, Object> map = kakaopay.kakaoPayInfo(pg_token);
		List<CartDTO> list = (List<CartDTO>) map.get("orderList");
		OrderVO order = new OrderVO();
		
		KakaoPayApprovalVO vo = (KakaoPayApprovalVO) map.get("kakao");
		/*order.setTotal(Long.valueOf(vo.getAmount().getTotal()));*/
		
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
