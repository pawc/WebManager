package pl.pawc.workflow.dao.employee;

import java.util.List;

import pl.pawc.workflow.model.Employee;

public interface EmployeeDAO{
	
	public List<Employee> getEmployees();
	public int insertEmployee(String firstName, String lastName, String birthDate, String employedSince, String department);

}
