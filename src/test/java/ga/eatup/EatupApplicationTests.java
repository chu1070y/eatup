package ga.eatup;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ga.eatup.partner.mapper.PartnerMapper;
import ga.eatup.partner.mapper.SuperadminMapper;
import ga.eatup.partner.service.EmailServiceImpl;
import ga.eatup.user.domain.UserVO;
import ga.eatup.user.mapper.UserMenuMapper;
import ga.eatup.user.mapper.StoreMapper;
import ga.eatup.user.mapper.UserMapper;
import lombok.Cleanup;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class EatupApplicationTests {

	@Autowired
	PasswordEncoder encoder;
	
	@Setter(onMethod_=@Autowired)
	private DataSource ds;
	
	@Setter(onMethod_=@Autowired)
	private UserMenuMapper menuMapper;
	
	@Setter(onMethod_=@Autowired)
	private PartnerMapper partnerMapper;
	
	@Setter(onMethod_=@Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_=@Autowired)
	private SuperadminMapper superadminMapper;
	
	
	@Setter(onMethod_=@Autowired)
	private EmailServiceImpl emailService;
	
	@Setter(onMethod_=@Autowired)
	private StoreMapper storeMapper;
	
	@Test
	public void getStore() {
		
		log.info("store 정보를 가져옵니다.");
		log.info("" + storeMapper.getStore());
	}
	
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void getUserTest() {
		
		UserVO vo = userMapper.getUser("manofin");
		log.info("" + vo);

	}
	
	@Test
	public void encodeTest() {
		UserVO vo = new UserVO();
		vo.setUpw("12345678");
		String enPw = encoder.encode(vo.getUpw());
		userMapper.update(enPw);
	}
	
	@Test
	@SneakyThrows(Exception.class)
	public void testConnection(){
		
		@Cleanup Connection con = ds.getConnection();
		log.info(""+con);
		
	}
	
/*	@Test
	public void test1() {
		System.out.println(menuMapper.getMenu());
	}*/
	
	@Test
	public void test2() {
		log.info("" + partnerMapper.getPartner("manofin"));
	}
	

//	@Test
//	public void sendEmail() {
//		
//	}
	
	@Test
	public void storeRemove() {
		superadminMapper.storeImageRemove("salem");
	}
	

}
