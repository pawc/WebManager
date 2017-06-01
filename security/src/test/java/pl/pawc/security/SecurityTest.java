package pl.pawc.security;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pl.pawc.security.factory.ServiceFactory;
import pl.pawc.security.services.ISecurityService;
import pl.pawc.security.services.Security;

public class SecurityTest extends TestCase{

    public SecurityTest(String testName){
        super( testName );
    }

    public static Test suite(){
        return new TestSuite(SecurityTest.class);
    }
    
    public void testSecurityFactory(){
    	ISecurityService securityService = ServiceFactory.getSecurityService();
    	assertTrue(securityService instanceof Security);
    }

    public void testLength(){
    	
    	ISecurityService securityService = ServiceFactory.getSecurityService();
    	String password = "sample password";
    	
    	String salt = securityService.generateSalt();
    	assertEquals(44, salt.length());
    	
    	String hashedPassWithSalt = securityService.hashWithSalt(password, salt);
    	assertEquals(24, hashedPassWithSalt.length());
    	  	    	  	
    }
    
    public void testReproductivity(){
    	
    	ISecurityService securityService = ServiceFactory.getSecurityService();
    	String password1 = "sample password";
    	String password2 = "sample password";
    	
    	String salt = securityService.generateSalt();
    	
    	String result1 = securityService.hashWithSalt(password1, salt);
    	String result2 = securityService.hashWithSalt(password2, salt);
    	
    	if(result1 != null) assertTrue(result1.equals(result2));

    }
    
    public void testFailDifferentPass(){
    	ISecurityService securityService = ServiceFactory.getSecurityService();
    	String password1 = "sample password";
    	String password2 = "other password";
    	
    	String salt = securityService.generateSalt();
    	
    	String result1 = securityService.hashWithSalt(password1, salt);
    	String result2 = securityService.hashWithSalt(password2, salt);
    	
    	if(result1 != null) assertFalse(result1.equals(result2));
    }
    
    public void testFailDifferentSalt(){
    	ISecurityService securityService = ServiceFactory.getSecurityService();
    	String password = "sample password";
    	
    	String salt1 = securityService.generateSalt();
    	String salt2 = securityService.generateSalt();
    	
    	String result1 = securityService.hashWithSalt(password, salt1);
    	String result2 = securityService.hashWithSalt(password, salt2);
    	
    	if(result1 != null) assertFalse(result1.equals(result2));
    }
    
}