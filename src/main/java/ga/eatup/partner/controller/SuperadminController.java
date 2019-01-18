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
import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.domain.StoreVO;
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
	
	@GetMapping("/findAuth/{pid}")
	@ResponseBody
	public ResponseEntity<Integer> findAuth(@PathVariable("pid") String pid){
		log.info("findAuth get.....");
		
		return new ResponseEntity<>(service.findAuth(pid),HttpStatus.OK);
	}
	
	@PostMapping("/storeremove")
	public String storeRemove(PartnerVO partnerVO, RedirectAttributes redirect) {
		log.info("store Remove........");
		
		log.info("" + partnerVO);

		int result = service.storeRemove(partnerVO.getPid());
		
		redirect.addFlashAttribute("storeRemoveResult", result);
		
		return "redirect:/partner/admin";
	}
	
	@PostMapping("/storemodify")
	public String storeModify(PartnerVO partnerVO, StoreVO storeVO ,RedirectAttributes redirect) {
		log.info("store Modify........");
		
		log.info("" + partnerVO);
		log.info("" + storeVO);
		
		if(storeVO.getImageList() != null) {
			storeVO.getImageList().forEach(image -> log.info("" + image));
		}
		
		int result = service.storeModify(storeVO, partnerVO.getPid());
		
		redirect.addFlashAttribute("storeModifyResult", result);
		
		return "redirect:/partner/admin";
	}
	
	@PostMapping("/storeadd")
	public String storeAdd(PartnerVO partnerVO, StoreVO storeVO ,RedirectAttributes redirect) {
		log.info("store add........");
		
		log.info("" + partnerVO);
		log.info("" + storeVO);
		
		if(storeVO.getImageList() != null) {
			storeVO.getImageList().forEach(image -> log.info("" + image));
		}
		
		int result = service.storeAdd(storeVO, partnerVO.getPid());
		
		redirect.addFlashAttribute("storeAddResult", result);
		
		return "redirect:/partner/admin";
	}
	
	@GetMapping("/pid/{pid}")
	@ResponseBody
	public ResponseEntity<Integer> checkPid(@PathVariable("pid") String pid){
		log.info("checkPid get.....");
		
		return new ResponseEntity<>(service.checkPid(pid),HttpStatus.OK);
	}
	
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
		
		return "redirect:/partner/admin";
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
		
		return "redirect:/partner/admin";
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
		
		return "redirect:/partner/admin";
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
		
		return "redirect:/partner/admin";
	}

}
