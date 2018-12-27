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
import ga.eatup.user.service.OrderService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class KakaoPayController {

	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;

	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;


	@PostMapping(value = "/kakaoPay", consumes = "application/json")
	public ResponseEntity<String> kakaoPay(@RequestBody List<CartDTO> cartList) {
		int totalPrice = 0;

		for (CartDTO vo :cartList) {
			totalPrice += (vo.getQuantity() * vo.getMprice());
		};

		log.info("============================================================================");
		log.info("totalPrice: " + totalPrice);
		log.info("cartList: " + cartList);
		
		return new ResponseEntity<>(kakaopay.kakaoPayReady(totalPrice, cartList), HttpStatus.OK);

	}

	@GetMapping("/kakaopay/kakaoPaySuccess")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		log.info("kakaoPaySuccess get............................................");
		log.info("kakaoPaySuccess pg_token : " + pg_token);

		Map<String, Object> result = kakaopay.kakaoPayInfo(pg_token);
		
		log.info("result: "+result);
		
		//key = kakao
		KakaoPayApprovalVO kakaokey = (KakaoPayApprovalVO)result.get("kakao");		
		log.info(""+ kakaokey.getAid());
		
		//kakaopayapprovalVO 타입을 CartDTO로 넣기
		
		String kakaokeyTid = kakaokey.getTid();
		String kakaokeyPayment_method_type = kakaokey.getPayment_method_type();		
		String kakaokeyPartner_order_id = kakaokey.getPartner_order_id(); 
		int kakaokeyQuantity = kakaokey.getQuantity();
		
		OrderVO orderVO = new OrderVO();
		List<CartDTO> cartList = (List<CartDTO>)result.get("cartList");

		orderVO.setTid(kakaokeyTid);
		orderVO.setPayment_method_type(kakaokeyPayment_method_type);
		orderVO.setPartner_order_id( Integer.parseInt(kakaokeyPartner_order_id));
		orderVO.setQuantity(kakaokeyQuantity);
		orderVO.setSno(cartList.get(0).getSno());
		orderVO.setMno(cartList.get(0).getMno());
		orderVO.setUno(1);
		orderVO.setToken(pg_token);
		orderVO.setApproved_at(kakaokey.getApproved_at());
		
		
		
		cartList.forEach(vo->{
			log.info(""+vo);
		});
	
		orderService.insertOrder(orderVO, cartList);
		
		
		model.addAttribute("info", result);
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
