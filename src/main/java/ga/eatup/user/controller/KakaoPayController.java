package ga.eatup.user.controller;

import java.util.ArrayList;
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

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.OrderNumDTO;
import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.domain.kakaopay.KakaoPayApprovalVO;
import ga.eatup.user.service.KakaoPay;
import ga.eatup.user.service.MenuService;
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
	
	@Setter(onMethod_ = @Autowired)
	private MenuService menuService;

	@PostMapping(value = "/kakaoPay", consumes = "application/json")
	public ResponseEntity<String> kakaoPay(@RequestBody List<CartDTO> cartList) {
		int totalPrice = 0;

		for (CartDTO vo : cartList) {
			totalPrice += (vo.getQuantity() * vo.getMprice());

			menuService.updatequantity(vo);
		};


		return new ResponseEntity<>(kakaopay.kakaoPayReady(totalPrice, cartList), HttpStatus.OK);

	}
	@GetMapping("/kakaopay/kakaoPaySuccess")
	public void kakaoPaySuccess(Authentication authentication, @RequestParam("pg_token") String pg_token, Model model) {

		Map<String, Object> result = kakaopay.kakaoPayInfo(pg_token);
		
		String auth = "";
		
		if(authentication != null) {
			List list = new ArrayList<>(authentication.getAuthorities());
			auth = "" + list.get(0);
		}

		String uid = (auth.equals("ROLE_USER")) ? authentication.getName() : "nomember";
		
		int uno = orderService.getUno(uid);
		

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
		orderVO.setPartner_order_id(Integer.parseInt(kakaokeyPartner_order_id));
		orderVO.setQuantity(kakaokeyQuantity);
		orderVO.setSno(cartList.get(0).getSno());
		orderVO.setMno(cartList.get(0).getMno());
		orderVO.setUno(orderService.getUno(uid));
		orderVO.setToken(pg_token);

		cartList.forEach(vo -> {
		});

		orderService.insertOrder(orderVO, cartList);

		model.addAttribute("info", result);

		
		// 가게별 주문번호 구하기 - 날짜가 바뀌면 최신화 하기
		Calendar cal = Calendar.getInstance();
		int date = cal.get(cal.DATE);
			
		Map<Integer, Integer[]> temp = new HashMap<>();

		if (OrderNumDTO.getOrder_num().get(cartList.get(0).getSno()) == null) {
			OrderNumDTO.putOrder_num(cartList.get(0).getSno(), new Integer[] { 101, date });
			
		} else {
			
			if (OrderNumDTO.getOrder_num().get(cartList.get(0).getSno())[1] != date) {
				OrderNumDTO.putOrder_num(cartList.get(0).getSno(), new Integer[] { 101, date });
			} else {
				OrderNumDTO.putOrder_num(cartList.get(0).getSno(), new Integer[] {OrderNumDTO.getOrder_num().get(cartList.get(0).getSno())[0] + 1, date });
				
			}
		}

		model.addAttribute("order_num", OrderNumDTO.getOrder_num().get(cartList.get(0).getSno())[0]);

	}

	@GetMapping("/kakaopay/kakaoPayFail")
	public void kakaoPayFail() {

	}

	@GetMapping("/kakaopay/kakaoPayCancel")
	public void kakaoPayCancel() {

	}
	
	@PostMapping("/tokenAjax")
	@ResponseBody
	public ResponseEntity<Integer> searchMenu(@PathVariable("token") String token){
		
		return new ResponseEntity<>( null ,HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST}, value = "/tokenAjax", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> tokenAjax(@RequestBody OrderVO vo) {

		return orderService.tokenUpdate(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
