package ga.eatup.user.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ga.eatup.partner.domain.StoreVO;
import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.domain.OrderNumDTO;
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

		for (CartDTO vo : cartList) {
			totalPrice += (vo.getQuantity() * vo.getMprice());
			
		};

		log.info("============================================================================");
		log.info("totalPrice: " + totalPrice);
		log.info("cartList: " + cartList);

		return new ResponseEntity<>(kakaopay.kakaoPayReady(totalPrice, cartList), HttpStatus.OK);

	}
	@GetMapping("/kakaopay/kakaoPaySuccess")
	public void kakaoPaySuccess(Authentication authentication, @RequestParam("pg_token") String pg_token, Model model) {
		log.info("kakaoPaySuccess get............................................");
		log.info("kakaoPaySuccess pg_token : " + pg_token);

		Map<String, Object> result = kakaopay.kakaoPayInfo(pg_token);
		log.info("auth: ----------------------------------------------------" + authentication);
		log.info("result: "+result);
		
		String uid = (authentication == null) ? "nomember":authentication.getName();
		log.info("uid: " + uid);
		int uno = orderService.getUno(uid);
		log.info("uno:" + uno);
		
		
		//key = kakao
		KakaoPayApprovalVO kakaokey = (KakaoPayApprovalVO)result.get("kakao");		
		
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
		orderVO.setUno(orderService.getUno(uid));
		orderVO.setToken(pg_token);
		orderVO.setApproved_at(kakaokey.getApproved_at());

		cartList.forEach(vo -> {
			log.info("" + vo);
		});

		orderService.insertOrder(orderVO, cartList);

		model.addAttribute("info", result);

		// 가게별 주문번호 구하기 - 날짜가 바뀌면 최신화 하기
		
		Calendar cal = Calendar.getInstance();
		int date = cal.get(cal.DATE);
		
		log.info("" + date);
		
		Map<Integer, Integer[]> temp = new HashMap<>();

		if (OrderNumDTO.getOrder_num().get(cartList.get(0).getSno()) == null) {
			log.info("come come3");
			OrderNumDTO.putOrder_num(cartList.get(0).getSno(), new Integer[] { 101, date });
			
		} else {
			
			if (OrderNumDTO.getOrder_num().get(cartList.get(0).getSno())[1] != date) {
				log.info("come come2");
				OrderNumDTO.putOrder_num(cartList.get(0).getSno(), new Integer[] { 101, date });
			} else {
				log.info("come come");
				OrderNumDTO.putOrder_num(cartList.get(0).getSno(), new Integer[] {OrderNumDTO.getOrder_num().get(cartList.get(0).getSno())[0] + 1, date });
				
			}
		}

		model.addAttribute("order_num", OrderNumDTO.getOrder_num().get(cartList.get(0).getSno())[0]);

	}

	@GetMapping("/kakaopay/kakaoPayFail")
	public void kakaoPayFail() {
		log.info("kakaoPayFail get............................................");

	}

	@GetMapping("/kakaopay/kakaoPayCancel")
	public void kakaoPayCancel() {
		log.info("kakaoPayCancel get............................................");

	}
	
	@PostMapping("/tokenAjax")
	@ResponseBody
	public ResponseEntity<Integer> searchMenu(@PathVariable("token") String token){
		log.info("tokenAjax get.....");
		
		return new ResponseEntity<>( null ,HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST}, value = "/tokenAjax", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> tokenAjax(@RequestBody OrderVO vo) {
		log.info("token ajax post...... in kakaoPayController");
		
		log.info("tid:" + vo.getTid());
		log.info("token:" + vo.getToken());

		return orderService.tokenUpdate(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
