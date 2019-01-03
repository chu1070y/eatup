package ga.eatup.partner.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ga.eatup.partner.domain.SalesVO;
import ga.eatup.partner.service.SalesService;
import lombok.Setter;
import lombok.extern.java.Log;


@RequestMapping("/partner/*")
@Controller
@Log
public class SalesController {

	@Setter(onMethod_=@Autowired)
	private SalesService service;
	
	@GetMapping(value = "sales")
	public String Sales(Locale locale, Model model) {
		log.info("sales page....");
		return "/partner/sales";
	}

	
	// ---------------------- chart ------------------------------- //
	@RequestMapping(value = "salesList", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String dailyList(Authentication authentication, Model model) {
		
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		Gson gson = new Gson();
		List<SalesVO> list = service.getDailySales(sno);
		
		return gson.toJson(list);
	}
	
	@RequestMapping(value = "weeklyList", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String weeklyList(Authentication authentication, Model model) {
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		Gson gson = new Gson();
		List<SalesVO> list = service.getWeeklySales(sno);
		
		return gson.toJson(list);
	}
	
	@RequestMapping(value = "monthlyList", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String monthlyList(Authentication authentication, Model model) {
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		Gson gson = new Gson();
		List<SalesVO> list = service.getMonthlySales(sno);
		
		return gson.toJson(list);
	}
	
	// ---------------------- data table ------------------------------- //
	@GetMapping(value = "dailydata/menu/{year}/{month}", produces="application/json")
	public ResponseEntity<List<SalesVO>> getdailydata(Authentication authentication, @PathVariable("month") int month, @PathVariable("year") int year){
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		return new ResponseEntity<>(service.getDailytableData(year, month, sno),HttpStatus.OK);
	}
	
	@GetMapping(value = "dailydata/date/{year}/{month}", produces="application/json")
	public ResponseEntity<List<SalesVO>> getdailydata_date(Authentication authentication, @PathVariable("month") int month, @PathVariable("year") int year){
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		return new ResponseEntity<>(service.getDailytableData_date(year, month, sno),HttpStatus.OK);
	}
	
	@GetMapping(value = "weeklydata/menu/{year}/{month}", produces="application/json")
	public ResponseEntity<List<SalesVO>> getweeklydata(Authentication authentication, @PathVariable("month") int month, @PathVariable("year") int year){
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		return new ResponseEntity<>(service.getWeeklytableData(year, month, sno),HttpStatus.OK);
	}
	
	@GetMapping(value = "weeklydata/date/{year}/{month}", produces="application/json")
	public ResponseEntity<List<SalesVO>> getweeklydata_date(Authentication authentication, @PathVariable("month") int month, @PathVariable("year") int year){
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		return new ResponseEntity<>(service.getWeeklytableData_date(year, month, sno),HttpStatus.OK);
	}
	
	@RequestMapping(value = "monthlydata/menu", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String monthlydata(Authentication authentication, Model model) {
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		
		Gson gson = new Gson();
		List<SalesVO> list = service.getMonthlytableData(sno);
		
		return gson.toJson(list);
	}
	
	@RequestMapping(value = "monthlydata/date", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String monthlydata_date(Authentication authentication, Model model) {
		String pid = authentication.getName();
		log.info("pid: " + pid);
		int sno = service.getSno(pid);
		log.info("sno: " + sno);
		 
		Gson gson = new Gson();
		List<SalesVO> list = service.getMonthlytableData_date(sno);
		
		return gson.toJson(list);
	}

	
}	