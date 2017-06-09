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
	
	@RequestMapping("account")
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response){	
		
		return new ModelAndView("account", "info", "");
	}
}