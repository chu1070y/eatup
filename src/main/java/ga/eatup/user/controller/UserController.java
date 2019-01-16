package ga.eatup.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.FaqPageDTO;
import ga.eatup.user.domain.FaqUploadVO;
import ga.eatup.user.domain.FaqVO;
import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.domain.OrderVO;
import ga.eatup.user.domain.UserVO;
import ga.eatup.user.service.FaqBoardService;
import ga.eatup.user.service.LoginService;
import ga.eatup.user.service.MenuService;
import ga.eatup.user.service.OrderService;
import ga.eatup.user.service.StoreService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/user/*")
@Log
public class UserController {

	@Setter(onMethod_ = @Autowired)
	private MenuService service;

	@Setter(onMethod_ = @Autowired)
	private LoginService loginService;

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;

	@Setter(onMethod_ = @Autowired)
	private FaqBoardService faqService;

	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;

	@Autowired
	PasswordEncoder encoder;


	@GetMapping("/login/customLogout")
	public void logout() {
	}

	@PostMapping("/login/customLogin")
	public void customLogin(UserVO vo, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {


		String inputUid = vo.getUid();
		String inputPw = vo.getUpw();

		UserVO dbVo = loginService.getUser(inputUid);

		String uId = dbVo.getUid();
		String uPw = dbVo.getUpw();


		if (!inputPw.equals(uPw)) {
			response.sendRedirect("/user/pay");
		}

		else {
			response.sendRedirect("/user/home");
		}
	}

	@GetMapping("/login/customLogin")
	public void login() {

	}

	@GetMapping("/login/customLoginTemp")
	public void temptemp(@RequestParam String username, @RequestParam String password, Model model) {

		model.addAttribute("username", username);
		model.addAttribute("password", password);
	}

	@PostMapping("/usercreate")
	public String usercreate(UserVO vo, RedirectAttributes redirect) {

		String enPw = encoder.encode(vo.getUpw());
		vo.setUpw(enPw);

		loginService.registerUser(vo);

		redirect.addFlashAttribute("welcome", loginService.registerAuth(vo));

		return "redirect:/user/login/customLogin";
	}

	@RequestMapping(value = "/welcome", method = { RequestMethod.POST, RequestMethod.GET })
	public void welcome(HttpServletRequest req, Model model) {

		// KakaoLoginController에서 FlashMap으로 전송한 데이터 받기 위해서는 request를 파라미터로 한
		// inputFlashMap 객체를 생성해야 함.
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(req);

		// 받은 정보를 UserVO 인스턴스 vo에 담아서 welcome.html(회원가입 페이지)로 전달.
		UserVO vo = new UserVO();
		vo.setSns_id("0");
		if (inputFlashMap != null) {
			vo.setNickname((String) inputFlashMap.get("nickname"));
			vo.setEmail((String) inputFlashMap.get("email"));
			vo.setSns_id((String) inputFlashMap.get("snsId"));
			log.info("UserVO: " + vo);
		}
		model.addAttribute("vo", vo);
	}

	@GetMapping("/map")
	public void map() {
		log.info("map.....");
	}


	@GetMapping("/home")
	public void home(Authentication authentication, String lat, String lng, String search, Model model) {
		log.info("lat: " + lat + " , lng: " + lng);
		// model.addAttribute("location", lat );
		
		String auth = "";
		
		if(authentication != null) {
			List list = new ArrayList<>(authentication.getAuthorities());
			auth = "" + list.get(0);
		}

		String uid = (auth.equals("ROLE_USER")) ? authentication.getName() : "nomember";
		int uno = orderService.getUno(uid);


		model.addAttribute("quick", orderService.getQuickMenu(uno));

		if (lat == null) {
			model.addAttribute("store", storeService.getStore());
		} else {
			model.addAttribute("store", storeService.getStoreNear(Double.parseDouble(lat), Double.parseDouble(lng)));
			model.addAttribute("search", search);
		}

	}

	// 매장 page
	@GetMapping("/store")
	public void store(@ModelAttribute("sno") int sno, Model model) {

		model.addAttribute("menu", service.getMenu(sno));
		model.addAttribute("storeimg", storeService.getStoreImg(sno));

	}

	// 장바구니, 주문확인 page
	@GetMapping({"/cart","/pay"})
	public void cart(@CookieValue("cart") String cart, @ModelAttribute("sno") int sno, Model model) {

		// 쿠기값 가져오기
		Gson gson = new Gson();

		List<CartDTO> menu = gson.fromJson(cart, new TypeToken<List<CartDTO>>() {
		}.getType());

		menu.forEach(dto -> {

		});
		List<MenuVO> menuList = service.getCart(sno);

		CartDTO.classify(menu, menuList);
		menu.forEach(dto -> {

		});
		model.addAttribute("cart", menu);

	}


	// 주문내역 page
	@GetMapping("/history")
	public void orderHistory(Authentication authentication, String tid, Model model) {
		
		// 비회원 체크
		String uid = (authentication == null) ? "nomember":authentication.getName();
		int uno = orderService.getUno(uid);

		Map<String, Object> map = new HashMap<>();
		map.put("uno", uno);

		if (uno == 1) {
			map.put("tid", tid);
		}

		
		model.addAttribute("history", orderService.getOrderHistory(map));
		
	}
	
	// 비회원 주문내역
	@PostMapping(value="/{tid}")
	public ResponseEntity<List<OrderVO>> historyPOST(@PathVariable("tid") String tid){

		int uno = 1;
		
		Map<String, Object> map =new HashMap<>();

		map.put("uno", uno);
		map.put("tid", tid);
		
		return new ResponseEntity<List<OrderVO>> (orderService.getOrderHistory(map), HttpStatus.OK);
	
	};
	 


	@GetMapping("/firebase/test")
	public void testFirebase(HttpServletRequest request) {

	}

	@GetMapping("/firebase/test2")
	public void testFirebase2(HttpServletRequest request) {

	}

	@GetMapping("/search")
	public void search() {

	}

	@GetMapping("/oneByone")
	public void oneByone() {
	}

	@Transactional
	@GetMapping("/faq")
	public void notice(Model model, FaqPageDTO dto) {

		dto.setTotal(faqService.faqCount());

		model.addAttribute("faqList", faqService.faqList(dto));
		model.addAttribute("dto", dto);
	}

	@GetMapping("/faq/register")
	public void faqRegister() {

	}

	@GetMapping(value="/getUploadList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<FaqUploadVO>> getUploadList(int fno){
		
		return new ResponseEntity<>(faqService.uploadRead(fno),HttpStatus.OK);
	}
	
	@PostMapping("/faqadd")
	public String noticeAdd(FaqVO vo, RedirectAttributes redirect) {
		
		if(vo.getUploadList() != null) {
			vo.getUploadList().forEach(upload -> log.info(""+upload));
			vo.getUploadList().forEach(upload -> log.info(""+upload.getFiletype()));
		}

		int result = faqService.faqAdd(vo);

		redirect.addFlashAttribute("addResult", result);

		return "redirect:/user/faq";
	}

	@GetMapping("/faq/read")
	public void faqRead(Model model, FaqPageDTO dto) {

		model.addAttribute("faq", faqService.faqRead(dto.getFno()));
		model.addAttribute("dto", dto);
	}

	@GetMapping("/faq/modify")
	public void faqModify(Model model, FaqPageDTO dto) {

		model.addAttribute("faq", faqService.faqRead(dto.getFno()));
		model.addAttribute("dto", dto);
	}

	@PostMapping("/faq/modify")
	public String faqModifyPost(RedirectAttributes redirect, FaqVO vo, FaqPageDTO dto) {

		int result = faqService.faqModify(vo);

		redirect.addFlashAttribute("result", result);

		return dto.getLink("redirect:/user/faq/read");
	}

	@PostMapping("/faq/remove")
	public String faqRemovePost(RedirectAttributes redirect, FaqVO vo) {

		int result = faqService.faqRemove(vo);

		redirect.addFlashAttribute("result", result);

		return "redirect:/user/faq";
	}

}
