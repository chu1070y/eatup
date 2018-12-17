/*package ga.eatup;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ga.eatup.user.domain.MenuVO;
import ga.eatup.user.mapper.MenuMapper;
import ga.eatup.user.service.MenuService;
import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MenuTests {

	@Setter(onMethod_ = { @Autowired })
	private MenuService service;
	
	@Setter(onMethod_ = { @Autowired })
	private MenuMapper mapper;
	
	@Test
	public void testMenuService() {

		List<MenuVO> menu = service.getMenu(2);
		
		log.info("서비스테스트 : " + menu);


	}

	@Test
	public void testMenuMapper() {

		List<MenuVO> menu = mapper.getMenu(1);
	
		log.info("매퍼테스트: " + menu);

	}

}
*/