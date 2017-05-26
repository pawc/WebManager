package pl.pawc.workflow.dao.employee;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pl.pawc.workflow.model.Employee;

public class EmployeeJDBCTemplate implements EmployeeDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public Employee getEmployee(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertEmployee(String firstName, String lastName, String birthDate, String employedSince, String department){
		String login = firstName.toLowerCase()+"."+lastName.toLowerCase();
		String SQL = "insert into employees(login, firstName, lastName, birthDate, employedSince, isStillEmployed, department, superior)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = jdbcTemplateObject.update(SQL, login, firstName, lastName, birthDate, employedSince, 1, department, "CEO");
		return rowsAffected;
	}

}