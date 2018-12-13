package ga.eatup.partner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ga.eatup.partner.service.SuperadminService;
import ga.eatup.user.domain.MenuVO;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/superadmin/*")
@Log
public class SuperadminController {
	
	@Setter(onMethod_ = @Autowired)
	private SuperadminService service;
	
	private static int sno = 0;
	
	@PostMapping("menuadd")
	public String menuAdd(MenuVO vo, RedirectAttributes redirect) {
		log.info("menu add......................");
		vo.setSno(sno);
		vo.getSno();
		log.info("menu sno : " + vo.getSno());
		log.info("결과는? "+ service.menuAdd(vo));
		
		return "redirect:/partner/superAdmin";
	}
	
	@GetMapping("/{sname}")
	@ResponseBody
	public ResponseEntity<Integer> searchSno(@PathVariable("sname") String sname){
		log.info("searchSno get.....");
		
		sno = service.searchSno(sname);
		
		log.info(""+sno);
		
		return new ResponseEntity<>(sno,HttpStatus.OK);
	}

}
