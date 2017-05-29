package pl.pawc.workflow.dao.employee;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pl.pawc.workflow.model.Department;
import pl.pawc.workflow.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class EmployeeMapperTest extends TestCase{

	public EmployeeMapperTest(String testName){
        super(testName);
    }

    public static Test suite(){
        return new TestSuite(EmployeeMapperTest.class);
    }

    public void testEmployeeMapper(){
    	   	
     	try{
     		ResultSet rs = Mockito.mock(ResultSet.class);
			when(rs.getInt("id")).thenReturn(35);
			when(rs.getString("login")).thenReturn("johndoe");
			when(rs.getString("firstName")).thenReturn("John");
			when(rs.getString("lastName")).thenReturn("Doe");
			when(rs.getDate("birthDate")).thenReturn(new Date(200));
			when(rs.getDate("employedSince")).thenReturn(new Date(3000));
			when(rs.getBoolean("stillEmployed")).thenReturn(true);
			when(rs.getString("department")).thenReturn("FINANCIAL");
			when(rs.getString("superior")).thenReturn("tomwhite");
			
			EmployeeMapper employeeMapper = new EmployeeMapper();
			Employee employee = employeeMapper.mapRow(rs, 8);
			
			assertEquals(35, employee.getId());
			assertEquals("johndoe", employee.getLogin());
			assertEquals("John", employee.getFirstName());
			assertEquals("Doe", employee.getLastName());
			assertEquals(new Date(200), employee.getBirthDate());
			assertEquals(new Date(3000), employee.getEmployedSince());
			assertTrue(employee.isStillEmployed());
			assertEquals(Department.FINANCIAL, employee.getDepartment());				
			assertEquals("tomwhite", employee.getSuperior());
		} 
    	catch(SQLException e){
			e.printStackTrace();
		} 	
    	
    }
}
