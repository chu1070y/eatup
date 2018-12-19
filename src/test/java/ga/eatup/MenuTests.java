package ga.eatup;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ga.eatup.partner.domain.MenuVO;
import ga.eatup.partner.mapper.PartnerMenuMapper;
import ga.eatup.partner.service.PartnerMenuService;
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
	
	@Test
	public void testMenuService() {
		List<MenuVO> menu = service.getMenu(4);	
		log.info("서비스테스트 : " + menu);
	}
	
	
	@Test
	public void testMenuMapper() {
		List<MenuVO> menu = mapper.getMenu(1);
		log.info("매퍼테스트: " + menu);
	}

	@Test
	public void testSnoService() {
		log.info("" + service.getSno("manofin"));
	}
	
	@Test
	public void testSnoMapper() {
		log.info("" + mapper.getSno("manofin"));
	}
}
