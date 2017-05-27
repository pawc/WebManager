package pl.pawc.workflow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.pawc.workflow.dao.employee.EmployeeJDBCTemplate;
import pl.pawc.workflow.model.Employee;

@Controller
public class WorkflowController{ 
	@RequestMapping("form")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJDBCTemplate employeeJdbcTemplate = (EmployeeJDBCTemplate) context.getBean("employeeJdbcTemplate");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		String employedSince = request.getParameter("employedSince");
		String department = request.getParameter("department");
		int rowsAffected = employeeJdbcTemplate.insertEmployee(firstName, lastName, birthDate, employedSince, department);
			
		return new ModelAndView("redirect:/form", "rowsAffected", "Rows affected: "+rowsAffected);
    }
	
	@RequestMapping("result")
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeJDBCTemplate employeeJdbcTemplate = (EmployeeJDBCTemplate) context.getBean("employeeJdbcTemplate");
		List<Employee> result = employeeJdbcTemplate.getEmployees();
		
		return new ModelAndView("result", "employees", result);
	}
}