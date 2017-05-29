package pl.pawc.workflow.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.pawc.workflow.dao.employee.EmployeeJdbcTemplate;
import pl.pawc.workflow.model.Employee;

@Controller
public class WorkflowController{ 
	
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
		return new ModelAndView("form", "logins", logins);
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
		int rowsAffected = employeeJdbcTemplate.insertEmployee(firstName, lastName, birthDate, employedSince, department, superior);
			
		return new ModelAndView("redirect:/result.html", "rowsAffected", "Rows affected: "+rowsAffected);
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
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response){
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
	
	@RequestMapping("delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		
		Map<String, String[]> map = request.getParameterMap();
		Set<String> selectedLogins = map.keySet();

		int rowsAffected = employeeJdbcTemplate.deleteEmployees(selectedLogins);
		
		return new ModelAndView("redirect:/result.html", "rowsAffected", rowsAffected);
	}
}