package pl.pawc.webmanager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class WebmanagerInterceptor implements HandlerInterceptor{
	
	String[] restrictedRequests = {
			"/webmanager/result.html",
			"/webmanager/form.html",
			"/webmanager/formAction.html",
			"/webmanager/edit.html",
			"/webmanager/editAction.html",
			"/webmanager/user.html",
			"/webmanager/delete.html",
			"/webmanager/home.html"
			};
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		
		if(checkIfRestrictedRequest(requestURI)){
			if(request.getSession().getAttribute("login")!=null) return true;
			else{
				response.sendRedirect("account");
				return false;
			}
		}
		else return true;

	}
	
	public boolean checkIfRestrictedRequest(String request){
		for(String req : restrictedRequests){
			if(request.equals(req)) return true;
		}
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}