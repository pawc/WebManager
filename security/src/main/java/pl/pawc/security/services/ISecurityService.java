package pl.pawc.security.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface ISecurityService {
	
	public String generateSalt();
	public String hashWithSalt(String password, String salt);

}
