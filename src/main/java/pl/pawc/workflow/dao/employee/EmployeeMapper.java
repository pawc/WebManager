package pl.pawc.workflow.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.pawc.workflow.model.Department;
import pl.pawc.workflow.model.Employee;

public class EmployeeMapper implements RowMapper<Employee>{

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setFirstName(rs.getString("firstName"));
		employee.setLastName(rs.getString("lastName"));
		employee.setBirthDate(rs.getDate("birthDate"));
		employee.setEmployedSince(rs.getDate("employedSince"));
		employee.setStillEmployed(rs.getBoolean("isStillEmployed"));
		employee.setDepartment(Department.valueOf(rs.getString("department")));
		return employee;
	}

}