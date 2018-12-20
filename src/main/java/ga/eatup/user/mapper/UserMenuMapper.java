package ga.eatup.user.mapper;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.MenuVO;

public interface UserMenuMapper {

	public List<MenuVO> getMenu(@RequestParam("sno") int sno);
	
	public List<MenuVO> getCart(@RequestParam("sno") int sno);
}
