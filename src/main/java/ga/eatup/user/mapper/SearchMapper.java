package ga.eatup.user.mapper;

import java.util.List;

import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.domain.StoreVO;

public interface SearchMapper {
	
	public List<MenuVO> searchMenu(String keyword);

	public List<StoreVO> searchStore(String keyword);
	
	public List<String> getMenuName();
	
	public List<String> getStoreName();
}
