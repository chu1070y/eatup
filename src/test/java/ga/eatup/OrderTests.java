package ga.eatup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ga.eatup.partner.domain.OrderVO;
import ga.eatup.partner.mapper.OrderMapper;
import lombok.Setter;
import lombok.extern.java.Log;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class OrderTests {
	
	@Setter(onMethod_= {@Autowired})
	private OrderMapper mapper;
	

	/*
	@Test
	public void ordermapper() {
		
		System.out.println(mapper.getOrder());
	}*/
	
	@Test
	public void ordermapper2() {
		
		OrderVO vo = new OrderVO();
		
		vo.setSno(1);
		vo.setTid("tid55");
		
		
		log.info(""+mapper.getOrder(vo));
		
	}

}
