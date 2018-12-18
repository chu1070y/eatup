package ga.eatup.partner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.MenuVO;

@Service
public interface PartnerMenuService {

	public List<MenuVO> getMenu(int sno);
	
	public int getSno(String pid);
}
