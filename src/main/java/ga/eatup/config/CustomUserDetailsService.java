package ga.eatup.config;

import ga.eatup.config.domain.CustomUser;
import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.mapper.PartnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class CustomUserDetailsService implements UserDetailsService {

	@Setter(onMethod_=@Autowired)
	private PartnerMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("loadUserByUsername..................................");
		log.info("PARM: " + username);
		
		PartnerVO vo = mapper.getPartner(username);	
		
		log.info("" + vo);
		
		//임시로 pw 인코딩 (프로젝트 땐 회원가입 단계에서 넣어야 함)
		vo.setPpw( encoder.encode(vo.getPpw()));
		
		CustomUser user = new CustomUser(vo);
		
		log.info("" + user);
		
		return user;
	}

}
