package pl.pawc.webmanager.dao.employee;

import java.util.List;
import java.util.Set;

import pl.pawc.webmanager.model.Employee;

public interface EmployeeDAO{
	
	public Employee getEmployee(String login);
	public List<Employee> getEmployees();
	public int insertEmployee(String firstName, String lastName, String birthDate, String employedSince, String department, String superior);
	public int editEmployee(String firstName, String lastName, String birthDate, String employedSince, String stillEmployed, String department, String superior, String login);
	public int deleteEmployees(Set<String> employees);
	
}