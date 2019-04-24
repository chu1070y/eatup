package ga.eatup.user.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.domain.kakaopay.KakaoPayApprovalVO;
import ga.eatup.user.domain.kakaopay.KakaoPayReadyVO;
import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {

	private List<CartDTO> finalcartList;
	
	private static final String HOST = "https://kapi.kakao.com";
	
	private KakaoPayReadyVO kakaoPayReadyVO;
	private KakaoPayApprovalVO kakaoPayApprovalVO;
	
	
	public String kakaoPayReady(int totalPrice, List<CartDTO> cartList) {
		
		finalcartList = cartList;

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "key");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", finalcartList.get(0).getSno()+"");
		params.add("partner_user_id", finalcartList.get(0).getSno()+"");
		params.add("item_name", finalcartList.get(0).getSname()+"");
		params.add("quantity", finalcartList.get(0).getQuantity()+"");
		params.add("total_amount", totalPrice +"");
		params.add("tax_free_amount","0");
		params.add("approval_url", "https://eatup.ga/user/kakaopay/kakaoPaySuccess");
		params.add("cancel_url", "https://eatup.ga/user/kakaopay/kakaoPayCancel");
		params.add("fail_url", "https://eatup.ga/user/kakaopay/kakaoPaySuccessFail");
		

 		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
				
			return kakaoPayReadyVO.getNext_redirect_pc_url();

		} catch (RestClientException e) {
			e.printStackTrace();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "/pay";
		
	}
	
	public Map<String, Object> kakaoPayInfo(String pg_token) {
	
		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "21e1a296e2c3aabd8c183e424ead22b5");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyVO.getTid());
		params.add("partner_order_id", finalcartList.get(0).getSno()+"");
		params.add("partner_user_id", finalcartList.get(0).getSno()+"");
		params.add("pg_token", pg_token);
		
		
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		

		Map<String, Object> map = new HashMap<>();
		
		
		try {
			kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
			
			map.put("kakao", kakaoPayApprovalVO);
			map.put("cartList", finalcartList);
			
			return map;
		
		} catch (RestClientException e) {
			e.printStackTrace();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
