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
	public Integer searchSno(String sname) {
		// TODO Auto-generated method stub
		return mapper.searchSno(sname);
	}

	@Override
	public int menuAdd(MenuVO vo) {
		// TODO Auto-generated method stub
		return mapper.menuAdd(vo);
	}

	
	
}
