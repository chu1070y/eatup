package ga.eatup.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ga.eatup.user.domain.StoreVO;
import ga.eatup.user.service.StoreService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class StoreController {

	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@GetMapping("/storeList")
	@ResponseBody
	public ResponseEntity<List<StoreVO>> getStore(){

		return new ResponseEntity<>(storeService.getStore(), HttpStatus.OK);
	}
	
}
