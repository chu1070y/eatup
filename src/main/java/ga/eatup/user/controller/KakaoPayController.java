package ga.eatup.user.controller;

import java.util.ArrayList;
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


	@PostMapping(value = "/kakaoPay", consumes = "application/json")
	public ResponseEntity<String> kakaoPay(@RequestBody List<OrderVO> orderList) {
		int totalPrice = 0;
		List list = new ArrayList();

/*		for (OrderVO vo :orderList) {
			totalPrice += (vo.getQuantity() * vo.getMprice());
		}
		;*/

		log.info("============================================================================");
		log.info("totalPrice: " + totalPrice);
		log.info("orderList: " + orderList);
		
		
		
		return new ResponseEntity<>(kakaopay.kakaoPayReady(totalPrice, orderList), HttpStatus.OK);

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
		
		//kakaopayapprovalvo 타입을 ordervo로 넣기
		
		String kakaokeyTid = kakaokey.getTid();
		String kakaokeyPayment_method_type = kakaokey.getPayment_method_type();		
		String kakaokeyPartner_order_id = kakaokey.getPartner_order_id(); // orderVO는 int로 돼있음,,,, 일단은 kakaoApprovalVO에 있는 String으로 해놓을궤,,
		
		int kakaokeyQuantity = kakaokey.getQuantity();
		
		
		
		
		
		
		//key=orderList
		List<CartDTO> orderlistkey = (List<CartDTO>)result.get("orderList");
		for(int i=0; i<orderlistkey.size(); i++) {
		
		log.info(""+ orderlistkey.get(i).getMname());
		
		}
		
	
		
		
		
		
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
