package ga.eatup.user.service;

import java.net.URI;
import java.net.URISyntaxException;

import ga.eatup.user.domain.kakaopay.KakaoPayApprovalVO;
import ga.eatup.user.domain.kakaopay.KakaoPayReadyVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {

	private static final String HOST = "https://kapi.kakao.com";
	
	private KakaoPayReadyVO kakaoPayReadyVO;
	private KakaoPayApprovalVO kakaoPayApprovalVO;
	
	public String kakaoPayReady() {

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "044adc3f0bc14dac22901c54c3e37d0b");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "1001");
		params.add("partner_user_id", "gorany");
		params.add("item_name", "갤럭시S9");
		params.add("quantity", "1");
		params.add("total_amount", "2100");
		params.add("tax_free_amount", "100");
		params.add("approval_url", "http://localhost:8080/user/kakaopay/kakaoPaySuccess");
		params.add("cancel_url", "http://localhost:8080/user/kakaopay/kakaoPayCancel");
		params.add("fail_url", "http://localhost:8080/user/kakaopay/kakaoPaySuccessFail");
		
		// 서버로 요청할 Body
//		kakaoPayReadyVO.setCid("TC0ONETIME");
//		kakaoPayReadyVO.setPartner_order_id("1001");
//		kakaoPayReadyVO.setPartner_user_id("gorany");
//		kakaoPayReadyVO.setItem_name("갤럭시S9");
//		kakaoPayReadyVO.setQuantity(1);
//		kakaoPayReadyVO.setTotal_amount(2100);
//		kakaoPayReadyVO.setTax_free_amount(100);
//		kakaoPayReadyVO.setApproval_url("http://localhost:8080/kakaopay/kakaoPaySuccess");
//		kakaoPayReadyVO.setCancel_url("http://localhost:8080/kakaoPayCancel");
//		kakaoPayReadyVO.setFail_url("http://localhost:8080/kakaoPaySuccessFail");

 		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
			
			log.info("" + kakaoPayReadyVO);
			
			return kakaoPayReadyVO.getNext_redirect_pc_url();

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/pay";
		
	}
	
	public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {

		log.info("KakaoPayInfoVO............................................");
		log.info("-----------------------------");
		
		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "044adc3f0bc14dac22901c54c3e37d0b");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyVO.getTid());
		params.add("partner_order_id", "1001");
		params.add("partner_user_id", "gorany");
		params.add("pg_token", pg_token);
		params.add("total_amount", "2100");
		
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
			log.info("" + kakaoPayApprovalVO);
			
			
//			String test = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, String.class);
//			
//			log.info("=================================++++++++++++++++++++++++++++++++++++2");
//		
//			log.info(""+test);
			
			
			return kakaoPayApprovalVO;
		
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
