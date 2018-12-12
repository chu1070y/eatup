package ga.eatup.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.domain.StoreVO;
import ga.eatup.user.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@Transactional
@RequestMapping("/user/*")
public class SearchController {

	@Setter(onMethod_=@Autowired)
	private SearchService service;
	
	@PostMapping("/autocomplete")
	@ResponseBody
	public ResponseEntity<String[]> autoComplete(){
		log.info("autocomplete");
		
		List<String> list = service.getName();

		return new ResponseEntity<>(list.toArray(new String[list.size()]),HttpStatus.OK);
	}
	
	@GetMapping("/searchMenu/{keyword}")
	@ResponseBody
	public ResponseEntity<List<MenuVO>> searchMenu(@PathVariable("keyword") String keyword){
		log.info("searchMenu post.....");
		log.info(""+service.searchMenu(keyword));
		
		return new ResponseEntity<>(service.searchMenu(keyword),HttpStatus.OK);
	}
	
	@GetMapping("/searchStore/{keyword}")
	@ResponseBody
	public ResponseEntity<List<StoreVO>> searchStore(@PathVariable("keyword") String keyword){
		log.info("searchStore post.....");

		log.info(""+service.searchStore(keyword));
		
		return new ResponseEntity<>(service.searchStore(keyword),HttpStatus.OK);
	}
	
}
