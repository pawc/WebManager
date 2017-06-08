package pl.pawc.webmanager.dao.password;

import java.util.Set;

import pl.pawc.security.model.Password;

public interface PasswordDAO {

	public Password getPassword(String login);
	public int insertPassword(Password password);
	public int updatePassword(String login);
	public int deletePassword(Set<String> logins);
	
}