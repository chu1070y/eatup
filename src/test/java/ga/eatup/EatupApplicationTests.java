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
import ga.eatup.user.mapper.MenuMapper;
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
	private MenuMapper menuMapper;
	
	@Setter(onMethod_=@Autowired)
	private PartnerMapper partnerMapper;
	
	@Setter(onMethod_=@Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_=@Autowired)
	private SuperadminMapper superadminMapper;
	
	@Setter(onMethod_=@Autowired)
	private EmailServiceImpl emailService;
	
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
		UserVO vo = userMapper.getUser("user_kakao");
		String enPw = encoder.encode(vo.getUpw());
		userMapper.update(enPw);
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
	
	@Test
	public void test2() {
		log.info("" + partnerMapper.getPartner("manofin"));
	}
	
//	@Test
//	public void sendEmail() {
//		emailService.sendSimpleMessage("고라니컴퍼니입니다.","죄송합니다. 이전에 보낸 당첨메일이 잘못 전송되었습니다.");
//		
//	}
	

}
