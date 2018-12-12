package ga.eatup.partner.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ga.eatup.user.domain.MenuVO;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/superadmin/*")
@Log
public class SuperadminController {
	
	
	@PostMapping("menuadd")
	public void menuAdd() {
		log.info("menu add......................");
		
		//DB와 연결
		
		
	}
	
/*	@GetMapping("/{sname}")
	@ResponseBody
	public ResponseEntity<String> searchSno(@PathVariable("sname") String sname){
		log.info("searchSno get.....");
		log.info(""+service.searchMenu(sname));
		
		return new ResponseEntity<>(service.searchMenu(keyword),HttpStatus.OK);
	}*/

}
