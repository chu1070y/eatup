package ga.eatup;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ga.eatup.user.mapper.MenuMapper;
import lombok.Cleanup;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class EatupApplicationTests {

	@Setter(onMethod_=@Autowired)
	private DataSource ds;
	
	@Setter(onMethod_=@Autowired)
	private MenuMapper menuMapper;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	@SneakyThrows(Exception.class)
	public void testConnection(){
		
		@Cleanup Connection con = ds.getConnection();
		log.info(""+con);
		
	}
	
	@Test
	public void test1() {
		System.out.println(menuMapper.getMenu());
	}

}
