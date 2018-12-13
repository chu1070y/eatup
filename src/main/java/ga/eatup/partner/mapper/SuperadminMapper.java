package ga.eatup.partner.mapper;

import ga.eatup.user.domain.MenuVO;

public interface SuperadminMapper {
	
	public Integer searchSno(String sname);
	
	public int menuAdd(MenuVO vo);

}
