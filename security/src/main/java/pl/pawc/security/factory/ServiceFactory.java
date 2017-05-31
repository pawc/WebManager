package pl.pawc.security.factory;

import pl.pawc.security.services.ISecurityService;
import pl.pawc.security.services.Security;

public class ServiceFactory{
	
	public static ISecurityService getSecurityService(){
		return new Security();
	}

}	