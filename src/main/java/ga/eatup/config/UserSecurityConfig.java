package ga.eatup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@Order(value= 100)
public class UserSecurityConfig  extends WebSecurityConfigurerAdapter {


	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("common user .... security config.......");
		
		http
			.authorizeRequests()
				.antMatchers("/upload/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/googlelogin").permitAll()
				.antMatchers("/naverlogin").permitAll()
				.and()
			.formLogin()
				.loginPage("/user/login/customLogin")
				.successHandler(successHandler())
				.failureHandler(failHandler())
				.and()
			.logout()
				.logoutUrl("/user/login/customLogout");

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failHandler() {
		return new CustomLoginSuccessHandler();
	}	
}
