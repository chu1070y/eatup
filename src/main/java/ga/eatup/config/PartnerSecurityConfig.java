package ga.eatup.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.java.Log;

@Log
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
				.antMatchers("/partner/salesList").hasRole("ADMIN")
				.antMatchers("/partner/weeklyList").hasRole("ADMIN")
				.antMatchers("/partner/monthlyList").hasRole("ADMIN")
				.antMatchers("/partner/superAdmin").hasRole("ADMIN")
				.antMatchers("/partner/notice/modify").hasRole("ADMIN")
				.antMatchers("/partner/notice/read").hasRole("ADMIN")
				.and()
			.formLogin()
				.loginPage("/partner/login/customLogin");
		
		

	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new CustomUserDetailsService();
	}
		
}
