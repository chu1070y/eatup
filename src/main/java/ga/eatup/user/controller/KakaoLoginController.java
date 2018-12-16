package ga.eatup.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.JsonNode;

import ga.eatup.user.domain.UserVO;
import ga.eatup.user.service.GoogleLogin;
import ga.eatup.user.service.KakaoLogin;
import ga.eatup.user.service.LoginService;
import ga.eatup.user.service.NaverLogin;
import lombok.Setter;
import lombok.extern.java.Log;

@RestController
@Log
public class KakaoLoginController {

	@Setter(onMethod_=@Autowired)
	private LoginService service;
	
	@Autowired
	PasswordEncoder encoder;

	
	@RequestMapping(value = "/kakaologin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakaoLogin(@RequestParam("code") String code, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

	  JsonNode token = KakaoLogin.getAccessToken(code);  
	  JsonNode profile = KakaoLogin.getKakaoUserInfo(token.path("access_token").toString());
	  
	  UserVO vo = KakaoLogin.changeData(profile);
	  String nickname = vo.getNickname();
	  String email = vo.getEmail();
	  String snsId = vo.getSns_id();
	  
	  //DB에서 끌고 온 데이터
	  List<UserVO> userList = service.getUserList();

	  String redirectPage = "";
	  String uid = "";
	  String upw = "";
	  
	  //DB에 저장된 sns_id 없을 때, 회원가입 페이지로 넘길 정보 (FlashMap 객체 생성)
	  FlashMap fm = RequestContextUtils.getOutputFlashMap(request);

	  //sns_id와 맵핑된 유저 데이터가 DB에 있는지 확인하는 작업. 
	  for(int i = 0; i < userList.size(); i++) {
		  if(userList.get(i).getSns_id().equals(snsId)) {
			  uid = userList.get(i).getUid();
			  // 소셜 로그인인 경우 defaultKey를 심어줌.
			  service.setDefaultkey(snsId);
			  //defaultKey에 넣어준 1값을 password 파라미터로 전달 
			  upw = service.getUser(uid).getDefaultkey();
			  redirectPage = "/user/login/customLoginTemp?username=" + uid + "&password=" + upw;
			  
			  // 파라미터 전달은 1로 준 이후에 DB 내 defaultKey는 암호화 업데이트
			  String defaultKey = encoder.encode(upw);
			  vo.setDefaultkey(defaultKey);
			  service.encodeDefaultkey(vo);
			  // defaultKey 암호화 후 곧바로 redirect
			  response.sendRedirect(redirectPage);
			  break;
			  
		  } else { 
		  
		  // sns_id와 맵핑된 유저 데이터가 DB에 없을 시 회원가입 페이지로 이동 
		  // flashmap에데이터 전달 
		  fm.put("nickname", nickname);
		  fm.put("email", email);
		  fm.put("snsId", snsId);
		  log.info("map: " + fm);

		  redirectPage = "/user/welcome";
		  //이 코드 때문에 몇시간을 날렸는지... FlashMap에 파라미터로 이동하는URL, request, response를 주어야 함.
          RequestContextUtils.saveOutputFlashMap(redirectPage, request, response);
		  }
	  }//end for
	  
	  log.info("redirect page: " + redirectPage);
	  // /user/welcome 즉 회원가입 페이지로 리다이렉트. UserController에서 FlashMap 받아서 다시 html로 뿌려주어야 함. 
	  response.sendRedirect(redirectPage);

	  //사실상 이 return은 의미없... html에서는 작동 안하는 듯... redirect 기능은 모두 response.sendRedirect로 시킴. 
	  return "redirect:" + redirectPage;
	}
	
	@RequestMapping(value = "/naverlogin", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String naverLogin(Model model, @RequestParam("code") String code, @RequestParam("state") String state,
			HttpSession session) {

		JsonNode token = NaverLogin.getAccessToken(code);
		log.info("=====" + code);
		log.info("=====" + state);
		log.info("=====" + token);

		JsonNode userInfo = NaverLogin.getNaverUserInfo(token.path("access_token").toString());

		UserVO vo = NaverLogin.changeData(userInfo);

//		vo.setSns_id("n" + vo.getSns_id());

		session.setAttribute("login", vo);
		model.addAttribute("login", vo);

		return "login/naverLogin";
	}
	
	@RequestMapping(value = "/googlelogin", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String googleLogin(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {

		JsonNode token = GoogleLogin.getAccessToken(code);
		log.info("=====" + code);
		log.info("=====" + token);

		JsonNode userInfo = GoogleLogin.getGoogleUserInfo(token.path("access_token").asText());

		UserVO vo = GoogleLogin.changeData(userInfo);

//		vo.setUser_snsId("g" + vo.getUser_snsId());

		session.setAttribute("login", vo);
		model.addAttribute("login", vo);

		return "login/googleLogin";
	}
	

}
