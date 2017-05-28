package pl.pawc.workflow.dao.employee;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pl.pawc.workflow.model.Employee;

public class EmployeeJdbcTemplate implements EmployeeDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<Employee> getEmployees(){
		String SQL = "select * from employees;";
		List<Employee> result = jdbcTemplateObject.query(SQL, new EmployeeMapper());
		return result;
	}

	public int insertEmployee(String firstName, String lastName, String birthDate, String employedSince, String department){
		String login = firstName.toLowerCase()+"."+lastName.toLowerCase();
		String SQL = "insert into employees(login, firstName, lastName, birthDate, employedSince, isStillEmployed, department, superior)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = jdbcTemplateObject.update(SQL, login, firstName, lastName, birthDate, employedSince, 1, department, "CEO");
		return rowsAffected;
	}

	public int deleteEmployees(Set<String> employees){
		String SQL = "delete from employees where login=?";
		int rowsAffected = 0;
		for(String login : employees){
			rowsAffected += jdbcTemplateObject.update(SQL, login);
		}
		return rowsAffected;
	}

	public Employee getEmployee(String login) {
		String SQL = "select * from employees where login=?";
		Employee employee = jdbcTemplateObject.query(SQL, new Object[]{login}, new EmployeeMapper()).get(0);
		return employee;
	}

}