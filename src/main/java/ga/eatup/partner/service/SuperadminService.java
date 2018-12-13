package ga.eatup.partner.service;

import org.springframework.stereotype.Service;

import ga.eatup.user.domain.MenuVO;

@Service
public interface SuperadminService {
	
	public MenuVO searchSno(String sname, String mname);
	
	public int menuAdd(MenuVO vo);
	
	public MenuVO searchSnoMno(String sname, String mname);
	
	public int menuUpdate(MenuVO vo);
	
	public int menuRemove(int mno);

}
