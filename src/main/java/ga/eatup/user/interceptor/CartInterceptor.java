package ga.eatup.user.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CartInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Cookie cookies[] = request.getCookies();
		
		boolean check = false;
		boolean echeck = false;
		
		Cookie cartCookie = null;
		
		String mno = request.getParameter("mno");
		String quantity = request.getParameter("quantity");

		String arr[];
		
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("cart")) {
				check = true;
				cartCookie = cookie;
				
				arr = cartCookie.getValue().split("_");
				for(String e:arr) {
					if(e.equals(String.valueOf(mno))) {
						echeck = true;
						break;
					}
				}
				
/*				if(check == false) {
					
					Cookie cookie = new Cookie("cart", mno);
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					
				}else if(echeck == true){
					return;
					
				}
				else {
				
					cartCookie.setValue(cartCookie.getValue() +"_" + mno);
					response.addCookie(cartCookie);
					
				}
				
				
				super.postHandle(request, response, handler, modelAndView);*/
			}
				
				
			}
		}
	}

	

