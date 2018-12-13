package ga.eatup.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "/kakaologin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakaoLogin(@RequestParam("code") String code , HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

	  JsonNode token = KakaoLogin.getAccessToken(code);
	  log.info("=====" + code);
	  log.info("=====" + token);
	  
	  JsonNode profile = KakaoLogin.getKakaoUserInfo(token.path("access_token").toString());
	  System.out.println("profile: " + profile);
	  
	  UserVO vo = KakaoLogin.changeData(profile);
	  List<UserVO> userList = service.getUserList();
	  
	  log.info("==========================================");
	  log.info("" + vo);
	  log.info("" + vo.getNickname());
	  log.info("" + userList);
	  log.info("" + userList.size());
	  log.info("" + userList.get(8).getNickname());

	  log.info("==========================================");
	  
	  for(int i = 0; i < userList.size(); i++) {
		  if(userList.get(i).getNickname() == vo.getNickname()) {
			  response.sendRedirect("/user/home");
		  }
	  }
	  
	  response.sendRedirect("/user/welcome");
	  
	  
//	  service.registerUser(vo);
	  
////	  이건 왜 하는거지?
//	  session.setAttribute("login", vo);
//	  
//	  System.out.println("session: " + session);
//	  System.out.println(vo.toString());
//		  
//	  response.sendRedirect("/user/home");
	  return "redirect:/user/home";
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
