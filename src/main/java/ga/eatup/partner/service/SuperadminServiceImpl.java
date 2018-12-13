package ga.eatup.partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.partner.mapper.SuperadminMapper;
import ga.eatup.user.domain.MenuVO;
import lombok.Setter;
import lombok.extern.java.Log;

@Service
public class SuperadminServiceImpl implements SuperadminService{
	
	@Setter(onMethod_ = @Autowired)
	private SuperadminMapper mapper;
	
	@Override
	public int menuAdd(MenuVO vo) {
		// TODO Auto-generated method stub
		return mapper.menuAdd(vo);
	}

	@Override
	public MenuVO searchSno(String sname, String mname) {
		// TODO Auto-generated method stub
		return mapper.searchSno(sname, mname);
	}

	@Override
	public MenuVO searchSnoMno(String sname, String mname) {
		// TODO Auto-generated method stub
		return mapper.searchSnoMno(sname, mname);
	}

	@Override
	public int menuUpdate(MenuVO vo) {
		// TODO Auto-generated method stub
		return mapper.menuUpdate(vo);
	}

	@Override
	public int menuRemove(int mno) {
		// TODO Auto-generated method stub
		return mapper.menuRemove(mno);
	}

	
	
}
