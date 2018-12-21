package ga.eatup.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ga.eatup.user.service.LoginService;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/user/*")
public class LoginController {
	
	@Setter(onMethod_ = @Autowired)
	LoginService service;
	
	@GetMapping("/welcome/{uid}")
	@ResponseBody
	public ResponseEntity<Integer> checkId(@PathVariable("uid") String uid){
		log.info("checkId get.....");
		
		return new ResponseEntity<>(service.checkId(uid),HttpStatus.OK);
	}

}
