package pl.pawc.webmanager.dao.password;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.pawc.security.model.Password;

public class PasswordMapper implements RowMapper<Password>{
	
	
	public Password mapRow(ResultSet rs, int rowNum) throws SQLException{
		Password password = new Password();
		password.setLogin(rs.getString("login"));
		password.setSalt(rs.getString("salt"));
		password.setHashedSaltedPass(rs.getString("hashedSaltedPass"));
		
		return password;
	}

}