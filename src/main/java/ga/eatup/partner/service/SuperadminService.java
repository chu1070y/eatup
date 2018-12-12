package ga.eatup.partner.service;

import org.springframework.stereotype.Service;

import ga.eatup.user.domain.MenuVO;

@Service
public interface SuperadminService {
	
	public Integer searchSno(String sname);
	
	public int menuAdd(MenuVO vo);

}
