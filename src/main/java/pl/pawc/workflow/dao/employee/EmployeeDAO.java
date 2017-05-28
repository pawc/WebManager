package pl.pawc.workflow.dao.employee;

import java.util.List;
import java.util.Set;

import pl.pawc.workflow.model.Employee;

public interface EmployeeDAO{
	
	public Employee getEmployee(String login);
	public List<Employee> getEmployees();
	public int insertEmployee(String firstName, String lastName, String birthDate, String employedSince, String department);
	public int deleteEmployees(Set<String> employees);
}
