package ga.eatup.partner.mapper;

import java.util.List;
import ga.eatup.partner.domain.MenuVO;
import ga.eatup.partner.domain.StoreVO;


public interface PartnerMenuMapper {

	public List<MenuVO> getMenu(int sno);
	
	public int getSno(String pid);
	
	public int getupdateMaxQuantity(MenuVO menu);

	public int getSoldoutX(MenuVO menu);
	
	public int getSoldoutO(MenuVO menu);
	
	
}
