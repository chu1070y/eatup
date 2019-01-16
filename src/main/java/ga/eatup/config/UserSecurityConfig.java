package ga.eatup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.java.Log;

@Log
@Configuration
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
		
		http.authorizeRequests().antMatchers("/imgs/**").permitAll().and()
			// 해당코드 주석처리해야 https로 자동변환되는 것을 막을 수 있다.
			.requiresChannel().anyRequest().requiresSecure().and()
			.antMatcher("/user/**")
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
				.logoutUrl("/user/login/customLogout")
				.logoutSuccessHandler(logoutsuccess());
		
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
	
	public LogoutSuccessHandler logoutsuccess() {
		return new CustomLoginSuccessHandler();
	}
	
	
}
