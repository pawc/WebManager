package pl.pawc.webmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import pl.pawc.webmanager.dao.employee.EmployeeJdbcTemplate;
import pl.pawc.webmanager.dao.password.PasswordJdbcTemplate;
import pl.pawc.webmanager.model.Employee;

@Controller
public class WebmanagerController{ 
	
	public List<Employee> query(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		List<Employee> result = employeeJdbcTemplate.getEmployees();
		return result;
	}
	
	public List<String> getLogins(){
		List<Employee> employees = query();
		List<String> result = new ArrayList<String>();
		for(Employee employee : employees){
			result.add(employee.getLogin());
		}
		return result;
	}
	
	@RequestMapping("form")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response){
		List<String> logins = getLogins();
		String info = request.getParameter("info");
		Object[] parameters = {logins, info};
		return new ModelAndView("form", "parameters", parameters);
    }
	
	@RequestMapping("formAction")
    public ModelAndView formAction(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		String employedSince = request.getParameter("employedSince");
		String department = request.getParameter("department");
		String superior = request.getParameter("superior");
		
		String login = firstName.toLowerCase()+"."+lastName.toLowerCase();
		String pass = request.getParameter("password");
		Password password = new Password(login, pass);
		
		PasswordJdbcTemplate passwordJdbcTemplate = (PasswordJdbcTemplate) context.getBean("passwordJdbcTemplate");

		try{
			employeeJdbcTemplate.insertEmployee(firstName, lastName, birthDate, employedSince, department, superior);
			passwordJdbcTemplate.insertPassword(password);
		}
		catch(DuplicateKeyException e){
			return new ModelAndView("redirect:/result.html");
		}
		
		return new ModelAndView("redirect:/form.html");
    }
	
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response){	
		String selectedUser = request.getParameter("login");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		Employee employee = employeeJdbcTemplate.getEmployee(selectedUser);
		List<String> logins = getLogins();
		Object[] parameters = new Object[2];
		parameters[0] = employee;
		parameters[1] = logins;
		return new ModelAndView("edit", "parameters", parameters);
	}
	
	@RequestMapping("editAction")
    public ModelAndView editAction(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		String employedSince = request.getParameter("employedSince");
		String stillEmployed = request.getParameter("stillEmployed");
		String department = request.getParameter("department");
		String superior = request.getParameter("superior");
		String login = firstName.toLowerCase()+"."+lastName.toLowerCase();
		int rowsAffected = employeeJdbcTemplate.editEmployee(firstName, lastName, birthDate, employedSince, checkboxToBoolean(stillEmployed), department, superior, login);
			
		return new ModelAndView("redirect:/result.html", "rowsAffected", rowsAffected);
    }
	
	public String checkboxToBoolean(String state){
		if("on".equals(state)) return "1";
		else return "0";
	}
	
	@RequestMapping("result")
	public ModelAndView result(HttpServletRequest request, HttpServletResponse response){
		List<Employee> result = query();
		return new ModelAndView("result", "employees", result);
	}
	
	@RequestMapping("user")
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
				
		String selectedUser = request.getParameter("login");
		Employee employee = employeeJdbcTemplate.getEmployee(selectedUser);
		return new ModelAndView("user", "employee", employee);
	}
	
	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("home", "info", "ok");
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		PasswordJdbcTemplate passwordJdbcTemplate = (PasswordJdbcTemplate) context.getBean("passwordJdbcTemplate");
		
		Map<String, String[]> map = request.getParameterMap();
		Set<String> selectedLogins = map.keySet();

		employeeJdbcTemplate.deleteEmployees(selectedLogins);
		passwordJdbcTemplate.deletePassword(selectedLogins);
		
		return new ModelAndView("redirect:/result.html");
	}
	
	@RequestMapping("account")
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response){	
		
		return new ModelAndView("account", "info", "");
	}
	
	@RequestMapping("logout")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response){	
		request.getSession().removeAttribute("login");
		return new ModelAndView("account", "info", "logged out");
	}
	
	@RequestMapping("signUp")
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
			return new ModelAndView("account", "info", "login already exists");
		}
		
		return new ModelAndView("account", "info", "new account registered: "+login);
	}
	
	@RequestMapping("signIn")
	public ModelAndView signIn(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		PasswordJdbcTemplate passwordJdbcTemplate = (PasswordJdbcTemplate) context.getBean("passwordJdbcTemplate");
		
		String login = request.getParameter("login");
		String pass = request.getParameter("password");

		String result = "invalid user or password";
	
		Password password;

		try{		
			password = passwordJdbcTemplate.getPassword(login);
		}
		catch(IndexOutOfBoundsException e){
			return new ModelAndView("account", "info", result);
		}

		String salt = password.getSalt();
		String hashedSaltedPass = password.getHashedSaltedPass();
		
		ISecurityService securityService = ServiceFactory.getSecurityService();
		
		String hashedSaltedPassForm = securityService.hashWithSalt(pass, salt);

		if(hashedSaltedPass.equals(hashedSaltedPassForm)){
			result = "logged in as "+login;
			request.getSession().setAttribute("login", login);
		}
		
		return new ModelAndView("redirect:/home.html");
	}
	
}
