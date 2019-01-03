package ga.eatup.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.mapper.UserMenuMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class MenuServiceImpl implements MenuService {

	@Setter(onMethod_=@Autowired)
	private UserMenuMapper mapper;

	@Override
	public List<MenuVO> getMenu(@RequestParam("sno") int sno) {

		log.info("getmenu service implement...........");
		return mapper.getMenu(sno);
	}

	@Override
	public List<MenuVO> getCart(int sno) {
		
		log.info("getCart service implement...........");
		return mapper.getCart(sno);
	}

	@Override
	public int updatequantity(CartDTO cart) {
		
		return mapper.updatequantity(cart);
		
	}



	
	
}
