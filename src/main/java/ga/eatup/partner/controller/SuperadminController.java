package ga.eatup.partner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ga.eatup.partner.domain.EmailVO;
import ga.eatup.partner.domain.NoticeUploadVO;
import ga.eatup.partner.domain.NoticeVO;
import ga.eatup.partner.service.EmailServiceImpl;
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
	
	@Setter(onMethod_ = @Autowired)
	private EmailServiceImpl emailService;
	
	private static MenuVO menuVO;
	
	@GetMapping(value="/getUploadList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoticeUploadVO>> getUploadList(int nno){
		
		return new ResponseEntity<>(service.uploadRead(nno),HttpStatus.OK);
	}
	
	@PostMapping("/noticeadd")
	public String noticeAdd(NoticeVO vo,RedirectAttributes redirect) {
		log.info("notice add........");
		
		if(vo.getUploadList() != null) {
			vo.getUploadList().forEach(upload -> log.info(""+upload));
			vo.getUploadList().forEach(upload -> log.info(""+upload.getFiletype()));
		}
		
		int result = service.noticeAdd(vo);
		
		redirect.addFlashAttribute("addResult", result);
		
		return "redirect:/partner/superAdmin";
	}
	
	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody EmailVO vo) {
		log.info("sendEmail......................");
		
		log.info(""+vo);
		emailService.sendSimpleMessage(vo.getName(),vo.getEmail(),vo.getPhone(),vo.getMsg());
		
		return null;

	}
	
	@PostMapping("menuadd")
	public String menuAdd(MenuVO vo, RedirectAttributes redirect) {
		log.info("menu add......................");
		vo.setSno(menuVO.getSno());
		log.info("menu sno : " + vo.getSno());
		
		int result = service.menuAdd(vo);
		log.info("결과는? "+ result);
		
		redirect.addFlashAttribute("menuAddResult", result);
		
		return "redirect:/partner/superAdmin";
	}
	
	@GetMapping("/menuadd/{sname}/{mname}")
	@ResponseBody
	public ResponseEntity<MenuVO> searchSno(@PathVariable("sname") String sname, @PathVariable("mname") String mname){
		log.info("searchSno get....." + service.searchSno(sname,mname));
		
		if(!(service.searchSno(sname,mname)==null)) {
			menuVO = service.searchSno(sname,mname);
		}else {
			menuVO = new MenuVO();
		}
		
		log.info(""+menuVO.getCount());
		
		return new ResponseEntity<>(menuVO,HttpStatus.OK);
	}
	
	@PostMapping("menumodify")
	public String menuModify(MenuVO vo, RedirectAttributes redirect) {
		log.info("menu modify......................");
		vo.setSno(menuVO.getSno());
		vo.setMno(menuVO.getMno());
		log.info("menu sno : " + vo.getSno());
		log.info("menu mno : " + vo.getMno());
		log.info("mprice : " + vo.getMprice());
		
		int result = service.menuUpdate(vo);
		log.info("결과는? "+ result);
		
		redirect.addFlashAttribute("menuModifyResult", result);
		
		return "redirect:/partner/superAdmin";
	}
	
	@GetMapping("/menumodify/{sname}/{mname}")
	@ResponseBody
	public ResponseEntity<MenuVO> searchSnoMno(@PathVariable("sname") String sname, @PathVariable("mname") String mname){
		log.info("searchSnoMno get.....");
		
		if(!(service.searchSnoMno(sname, mname)==null)) {
			menuVO = service.searchSnoMno(sname, mname);
		}else {
			menuVO = new MenuVO();
		}
		
		return new ResponseEntity<>(menuVO,HttpStatus.OK);
	}
	
	@PostMapping("menuremove")
	public String menuRemove(MenuVO vo, RedirectAttributes redirect) {
		log.info("menu remove......................");
		
		int result = service.menuRemove(menuVO.getMno());
		log.info("결과는? "+ result);
		
		redirect.addFlashAttribute("menuRemoveResult", result);
		
		return "redirect:/partner/superAdmin";
	}

}
