package pl.pawc.security.model;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

import pl.pawc.security.factory.ServiceFactory;
import pl.pawc.security.services.ISecurityService;
import pl.pawc.security.services.Security;

import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class Password{
	
	private String login;
    private String hashedSaltedPass;
    private String salt;
 
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
