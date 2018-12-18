package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.eatup.partner.domain.MenuVO;
import ga.eatup.partner.mapper.PartnerMenuMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
public class PartnerMenuServiceImpl implements PartnerMenuService {

	@Setter(onMethod_=@Autowired)
	private PartnerMenuMapper menuMapper;
	
	@Override
	public List<MenuVO> getMenu(int sno) {
		
		log.info("메뉴 정보를 가져옵니다.");
		return menuMapper.getMenu(sno);
	}

	@Override
	public int getSno(String pid) {
		log.info("sno를 가져옵니다.");
		return menuMapper.getSno(pid);
	}

}
