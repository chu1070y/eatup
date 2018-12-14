package ga.eatup.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.java.Log;


//이전과 다른 점은, Handler를 통해 특정 계정으로 들어왔을 때 특정 행위를 하도록 하는 것. 
@Log
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler 
	implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("login success");
		
		authentication.getAuthorities().forEach(auth -> {
			log.info("" + auth);
		});
		log.info("--------------------------");
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	//인증에 실패했을 경우
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("login fail");
		log.info("" + request);
		log.info("" + response);
		log.info("" + exception);
		
		if(exception != null) {
			
			log.info("========================================");
			
			StackTraceElement[] traces = exception.getStackTrace();
			
			for(StackTraceElement ele: traces) {
				log.warning(ele.toString());
			}
			
			log.info("========================================");
			
		}
		
		
		response.sendRedirect("/login?ex001");
		
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("Logout");
		response.sendRedirect("/login");
	}

}
