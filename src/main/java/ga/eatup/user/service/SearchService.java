package ga.eatup.user.service;

import java.util.List;

import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.domain.StoreVO;

public interface SearchService {

	public List<MenuVO> searchMenu(String keyword);
	
	public List<StoreVO> searchStore(String keyword);
	
	public List<String> getName();
	
}
