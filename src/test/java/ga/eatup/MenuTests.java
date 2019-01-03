package ga.eatup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import ga.eatup.partner.domain.MenuVO;
import ga.eatup.partner.mapper.PartnerMenuMapper;
import ga.eatup.partner.service.PartnerMenuService;
import ga.eatup.user.domain.CartDTO;
import ga.eatup.user.mapper.UserMenuMapper;
import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MenuTests {

	@Setter(onMethod_ = { @Autowired })
	private PartnerMenuService service;
	
	@Setter(onMethod_ = { @Autowired })
	private PartnerMenuMapper mapper;
	

	@Setter(onMethod_ = { @Autowired })
	private PartnerMenuMapper partnermapper;
	
	@Setter(onMethod_ = { @Autowired })
	private UserMenuMapper usermapper;
	
	@Test
	public void testMenuService() {
		//List<MenuVO> menu = service.getMenu(4);	
		//log.info("서비스테스트 : " + menu);
	}
	
	
	@Test
	public void testMenuMapper() {
		//List<MenuVO> menu = mapper.getMenu(1);
		//log.info("매퍼테스트: " + menu);
	}

	@Test
	public void testSnoService() {
		log.info("" + service.getSno("manofin"));
	}
	
	@Test
	public void testSnoMapper() {
		log.info("" + mapper.getSno("manofin"));
	}
	
	/*@Test
	public void testUpdate() {
		MenuVO vo = new MenuVO();
		vo.setMax_quantity(9);
		vo.setSno(1);
		vo.setMno(71);
		
		partnermapper.getupdateMaxQuantity(vo);
		
		
	}*/
	
	
	/*@Test
	public void soldoutteset() {
		
		MenuVO vo = new MenuVO();
		
		vo.setSno(2);
		vo.setMno(2);
		
	
		mapper.getSoldoutX(vo);
		
		log.info("" + mapper.getSoldoutX(vo));
		
		
	
				
	}
	*/
	@Test
	public void menutest() {
		CartDTO menu = new CartDTO();
		menu.setSno(1);
		menu.setMno(11);
		menu.setQuantity(2);
		
		log.info("" + usermapper.updatequantity(menu));
	}
}
