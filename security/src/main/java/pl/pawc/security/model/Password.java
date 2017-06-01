package pl.pawc.security.model;

import pl.pawc.security.factory.ServiceFactory;
import pl.pawc.security.services.ISecurityService;

public class Password{
	
	private String login;
    private String hashedSaltedPass;
    private String salt;
    
    public Password(){}
 
    public Password(String login, String pass){
        this.login =  login;
        ISecurityService securityService = ServiceFactory.getSecurityService();
        this.salt = securityService.generateSalt();
        this.hashedSaltedPass = securityService.hashWithSalt(pass, salt);
    }
    
    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHashedSaltedPass() {
		return hashedSaltedPass;
	}

	public void setHashedSaltedPass(String hashedSaltedPass) {
		this.hashedSaltedPass = hashedSaltedPass;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}