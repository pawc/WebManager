package pl.pawc.security.services;

public interface ISecurityService {
	
	public String generateSalt();
	public String hashWithSalt(String password, String salt);

}