package pl.pawc.webmanager.dao.password;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pl.pawc.security.model.Password;

public class PasswordJdbcTemplate implements PasswordDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public Password getPassword(String login) {
		String SQL = "select * from passwords where login=?";
		Password password = jdbcTemplateObject.query(SQL, new PasswordMapper(), login).get(0);
		return password;
	}

	public int insertPassword(Password password) {
		String SQL = "insert into passwords (login, salt, hashedSaltedPass) values (?, ?, ?);";
		int rowsUpdated = jdbcTemplateObject.update(SQL, password.getLogin(), password.getSalt(), password.getHashedSaltedPass());
		return rowsUpdated;
	}

	public int updatePassword(String login) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deletePassword(String login) {
		// TODO Auto-generated method stub
		return 0;
	}

}
