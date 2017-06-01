package pl.pawc.webmanager.dao.password;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pl.pawc.security.factory.ServiceFactory;
import pl.pawc.security.model.Password;
import pl.pawc.security.services.ISecurityService;
import pl.pawc.webmanager.dao.password.PasswordMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class PasswordMapperTest extends TestCase{

	public PasswordMapperTest(String testName){
        super(testName);
    }

    public static Test suite(){
        return new TestSuite(PasswordMapperTest.class);
    }

    public void testPasswordMapper(){
    	   	
     	try{
     		ISecurityService securityService = ServiceFactory.getSecurityService();
     		String login = "sample login";
     		String passwordText = "sample password";
     		String salt = securityService.generateSalt();
     		String hashedSaltedPass = securityService.hashWithSalt(passwordText, salt);
     		
     		ResultSet rs = Mockito.mock(ResultSet.class);
			when(rs.getString("login")).thenReturn(login);
			when(rs.getString("salt")).thenReturn(salt);
			when(rs.getString("hashedSaltedPass")).thenReturn(hashedSaltedPass);
			
			PasswordMapper passwordMapper = new PasswordMapper();
			Password password = passwordMapper.mapRow(rs, 8);
			
			assertEquals(login, password.getLogin());
			assertEquals(salt, password.getSalt());
			assertEquals(hashedSaltedPass, password.getHashedSaltedPass());
		} 
    	catch(SQLException e){
			e.printStackTrace();
		} 	
    	
    }
    
}