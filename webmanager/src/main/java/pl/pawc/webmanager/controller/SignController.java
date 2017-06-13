package pl.pawc.webmanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.pawc.security.factory.ServiceFactory;
import pl.pawc.security.model.Password;
import pl.pawc.security.services.ISecurityService;
import pl.pawc.webmanager.dao.password.PasswordJdbcTemplate;

@Controller
public class SignController {

	@RequestMapping("signUpAction")
	public ModelAndView signUp(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		PasswordJdbcTemplate passwordJdbcTemplate = (PasswordJdbcTemplate) context.getBean("passwordJdbcTemplate");
		
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		
		Password password = new Password(login, pass);
		
		try{
			passwordJdbcTemplate.insertPassword(password);
		}
		catch(DuplicateKeyException e){
			return new ModelAndView("signUp", "info", "login already exists");
		}
		
		return new ModelAndView("signIn", "info", "new account registered: "+login);
	}
	
	@RequestMapping("signInAction")
	public ModelAndView signIn(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		PasswordJdbcTemplate passwordJdbcTemplate = (PasswordJdbcTemplate) context.getBean("passwordJdbcTemplate");
		
		String login = request.getParameter("login");
		String pass = request.getParameter("password");

		Password password;

		try{		
			password = passwordJdbcTemplate.getPassword(login);
		}
		catch(IndexOutOfBoundsException e){
			return new ModelAndView("signIn", "info", "invalid user or password");
		}

		String salt = password.getSalt();
		String hashedSaltedPass = password.getHashedSaltedPass();
		
		ISecurityService securityService = ServiceFactory.getSecurityService();
		
		String hashedSaltedPassForm = securityService.hashWithSalt(pass, salt);

		if(hashedSaltedPass.equals(hashedSaltedPassForm)){
			request.getSession().setAttribute("login", login);
			return new ModelAndView("redirect:/home.html");
		}
		else{
			return new ModelAndView("signIn", "info", "invalid user or password");
		}			
	}	
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){	
		request.getSession().removeAttribute("login");
		return new ModelAndView("signIn", "info", "logged out");
	}
	
}