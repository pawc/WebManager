package pl.pawc.workflow.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.pawc.workflow.dao.employee.EmployeeJdbcTemplate;
import pl.pawc.workflow.model.Employee;

@Controller
public class WorkflowController{ 
	@RequestMapping("form")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		String employedSince = request.getParameter("employedSince");
		String department = request.getParameter("department");
		int rowsAffected = employeeJdbcTemplate.insertEmployee(firstName, lastName, birthDate, employedSince, department);
			
		return new ModelAndView("redirect:/result.html", "rowsAffected", "Rows affected: "+rowsAffected);
    }
	
	public List<Employee> query(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJdbcTemplate employeeJdbcTemplate = (EmployeeJdbcTemplate) context.getBean("employeeJdbcTemplate");
		List<Employee> result = employeeJdbcTemplate.getEmployees();
		return result;
	}
	
	@RequestMapping("result")
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response){
		List<Employee> result = query();
		return new ModelAndView("result", "employees", result);
	}
	
	@RequestMapping("user")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response){
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