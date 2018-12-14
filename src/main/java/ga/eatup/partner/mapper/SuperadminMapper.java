package ga.eatup.partner.mapper;

import ga.eatup.user.domain.MenuVO;

public interface SuperadminMapper {
	
	public MenuVO searchSno(String sname, String mname);
	
	public int menuAdd(MenuVO vo);
	
	public MenuVO searchSnoMno(String sname, String mname);
	
	public int menuUpdate(MenuVO vo);
	
	public int menuRemove(int mno);

}
