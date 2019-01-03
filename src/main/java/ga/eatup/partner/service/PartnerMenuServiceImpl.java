package ga.eatup.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	
	//menu service
		@Setter(onMethod_=@Autowired)
		private PartnerMenuService menuservice;
	
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

	@Override
	public int getupdateMaxQuantity(MenuVO menu) {
		
		return menuMapper.getupdateMaxQuantity(menu);
	}

	@Override
	public int getSoldoutX(MenuVO menu) {
		
		return menuMapper.getSoldoutX(menu);
	}

	
	@Override
	public int getSoldoutO(MenuVO menu) {
		
		return menuMapper.getSoldoutO(menu);
	}


}
