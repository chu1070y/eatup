package ga.eatup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import ga.eatup.config.domain.CustomUser;
import ga.eatup.partner.domain.PartnerVO;
import ga.eatup.partner.service.PartnerService;
import ga.eatup.user.domain.UserVO;
import ga.eatup.user.service.LoginService;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class CustomUserDetailsService implements UserDetailsService {

	@Setter(onMethod_=@Autowired)
	private PartnerService partnerService;
	
	@Setter(onMethod_=@Autowired)
	private LoginService userService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("loadUserByUsername..................................");
		log.info("PARM: " + username);
						
		PartnerVO partnerVO = partnerService.getPartner(username);	
		UserVO userVO = userService.getUser(username);
		
		//Partner로 로그인하면 userVO가 null, User로 로그인하면 partnerVO가 null
		log.info("" + partnerVO);
		log.info("" + userVO);
				
		CustomUser member;
		
		//partner 로그인인 경우
		if (partnerVO != null) {
			member = new CustomUser(partnerVO);
		//customer(user) 로그인인 경우
		}else {
			//customer(user) 로그인에서 소셜 로그인인 경우
			if (userVO.getDefaultkey() != null) {
				userVO.setUpw(userVO.getDefaultkey());
				member = new CustomUser(userVO);
				userService.nullDefaultkey(username);
			}
			//customer(user) 로그인에서 일반 로그인인 경우
			else {
				member = new CustomUser(userVO);
			}
		}
		log.info("" + member);
		
		return member;
	}

}
