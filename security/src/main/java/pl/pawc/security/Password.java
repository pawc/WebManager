package pl.pawc.security;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

import pl.pawc.security.services.Utils;

import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class Password{
	
	private String login;
    private String hashedSaltedPass;
    private String salt;
 
    public Password(String login, String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        this.login =  login;
        this.salt = Utils.generateSalt();
        this.hashedSaltedPass = Utils.hashWithSalt(pass, salt);
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