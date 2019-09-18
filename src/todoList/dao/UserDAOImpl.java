package todoList.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import todoList.beans.User;
import todoList.util.ConnectionProvider;

public class UserDAOImpl implements UserDAO {
	
	private static final String CREATE_STATEMENT = "INSERT INTO user(email,password) VALUES(:email,:password)";
	
	private NamedParameterJdbcTemplate template;
	
	public UserDAOImpl() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}

	@Override
	public User create(User user) {
		User resultUser = new User(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		int update = template.update(CREATE_STATEMENT, parameterSource, keyHolder);
		if(update > 0) {
			resultUser.setId(keyHolder.getKey().longValue());
			setPrivileges(resultUser);
		}
		return resultUser;
	}

	private void setPrivileges(User user) {
		final String statement = "INSERT INTO user_role(email) VALUES(:email)";
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		template.update(statement, parameterSource);
	}

	@Override
	public User read(Long primeKey) {
		return null;
	}

	@Override
	public boolean update(User updateObject) {
		return false;
	}

	@Override
	public boolean delete(Long primeKey) {
		return false;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		User resultUser = null;
		final String statement = "SELECT * FROM user WHERE email=:email";
		SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
		List<User> users = template.query(statement, parameterSource, new UserRowMapper());
		if(users.size() > 0 && users.get(0) != null)
			resultUser = users.get(0);
		return resultUser;
	}
	
	private class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			return user;
		}
		
	}

}
