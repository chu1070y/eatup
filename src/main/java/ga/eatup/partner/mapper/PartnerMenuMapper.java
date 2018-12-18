package ga.eatup.partner.mapper;

import java.util.List;
import ga.eatup.partner.domain.MenuVO;


public interface PartnerMenuMapper {

	public List<MenuVO> getMenu(int sno);
	
	public int getSno(String pid);
	
}
