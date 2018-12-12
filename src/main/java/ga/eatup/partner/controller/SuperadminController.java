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
	
	@PostMapping("menuadd")
	public void menuAdd(MenuVO vo) {
		log.info("menu add......................");
		
		log.info("결과는? "+service.menuAdd(vo));
		
	}
	
	@GetMapping("/{sname}")
	@ResponseBody
	public ResponseEntity<Integer> searchSno(@PathVariable("sname") String sname){
		log.info("searchSno get.....");
		log.info(""+service.searchSno(sname));
		
		return new ResponseEntity<>(service.searchSno(sname),HttpStatus.OK);
	}

}
