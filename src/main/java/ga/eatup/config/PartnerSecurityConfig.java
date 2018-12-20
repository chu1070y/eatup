package ga.eatup.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
@Configuration
@EnableWebSecurity
@Order(value= 1)
public class PartnerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config.......");
		
		//경로 및 권한 생성하기
		http
			.antMatcher("/partner/**")
			.authorizeRequests()
				.antMatchers("/partner/upload/**").permitAll()
				.antMatchers("/partner/partnercreate").permitAll()
				.antMatchers("/partner/welcome").permitAll()
				.antMatchers("/partner/login/**").permitAll()
				.antMatchers("/partner/index").authenticated()
				.antMatchers("/partner/information").authenticated()
				.antMatchers("/partner/menu").authenticated()
				.antMatchers("/partner/notice/*").authenticated()
				.antMatchers("/partner/oneByone").authenticated()
				.antMatchers("/partner/sales").authenticated()
				.antMatchers("/partner/notice").authenticated()
				.antMatchers("/partner/salesList").authenticated()
				.antMatchers("/partner/weeklyList").authenticated()
				.antMatchers("/partner/monthlyList").authenticated()
				.antMatchers("/partner/superAdmin").hasRole("ADMIN")
				.antMatchers("/partner/notice/modify").hasRole("ADMIN")
				.antMatchers("/partner/notice/read").hasRole("ADMIN")
				.and()
			.formLogin()
				.loginPage("/partner/login/customLogin")
				.successHandler(psuccessHandler());
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new CustomUserDetailsService();
	}
	
	@Bean
	public AuthenticationSuccessHandler psuccessHandler() {
		return new PartnerLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler pfailHandler() {
		return new PartnerLoginSuccessHandler();
	}	
	
	
		
}
