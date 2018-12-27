package ga.eatup.user.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.MenuVO;

public interface MenuService {

	public List<MenuVO> getMenu(@RequestParam("sno") int sno);
	
	public List<MenuVO> getCart(@RequestParam("sno") int sno);
	
	
}
