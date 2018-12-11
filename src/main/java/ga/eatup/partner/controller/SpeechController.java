package ga.eatup.partner.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.java.Log;

@Log
@Controller
public class SpeechController {

	
	@PostMapping(value="/sendVoice", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> sendVoice(@RequestBody String voice) {
		
		log.info(""+voice);
		
		return null;
	}
	

	
}
