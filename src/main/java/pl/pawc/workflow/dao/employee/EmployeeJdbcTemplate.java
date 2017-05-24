package pl.pawc.workflow.dao.employee;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pawc.workflow.model.Employee;

public class EmployeeJdbcTemplate implements EmployeeDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public EmployeeJdbcTemplate(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}	

	public Employee getEmployee(String login){
		String SQL = "select * from employees where login = ?";
		Employee employee = jdbcTemplateObject.queryForObject(SQL,
			new Object[]{login}, new EmployeeMapper());
		return employee;
	}

}
