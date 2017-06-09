package pl.pawc.webmanager.controller;

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

import pl.pawc.security.model.Password;
import pl.pawc.webmanager.dao.employee.EmployeeJdbcTemplate;
import pl.pawc.webmanager.dao.password.PasswordJdbcTemplate;

@Controller
public class ActionController {

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
	
	@RequestMapping("deleteAction")
	public ModelAndView deleteAction(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		PasswordJdbcTemplate passwordJdbcTemplate = (PasswordJdbcTemplate) context.getBean("passwordJdbcTemplate");
		
		Map<String, String[]> map = request.getParameterMap();
		Set<String> selectedLogins = map.keySet();

		employeeJdbcTemplate.deleteEmployees(selectedLogins);
		passwordJdbcTemplate.deletePassword(selectedLogins);
		
		return new ModelAndView("redirect:/result.html");
	}
	
}