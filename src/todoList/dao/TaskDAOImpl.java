package todoList.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import todoList.beans.Task;
import todoList.beans.User;
import todoList.util.ConnectionProvider;

public class TaskDAOImpl implements TaskDAO {
	
	private static final String CREATE_STATEMENT = "INSERT INTO task(title,description,user_id) VALUES(:title,:description,:user_id)";
	private static final String DELETE_STATEMENT = "DELETE FROM task WHERE task_id=:task_id";
	private static final String UPDATE_STATEMENT = "UPDATE task SET title=:title,description=:description,user_id=:user_id WHERE task_id=:task_id";
	
	private NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());

	@Override
	public Task create(Task task) {
		
		Task resultTask = new Task(task);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("title", task.getTitle());
		parameterSource.addValue("description", task.getDescription());
		parameterSource.addValue("user_id", task.getUser().getId());
		
		int update = template.update(CREATE_STATEMENT, parameterSource, keyHolder);
		if(update > 0)
			resultTask.setId(keyHolder.getKey().longValue());
		
		return resultTask;
	}

	@Override
	public Task read(Long primeKey) {
		return null;
	}

	@Override
	public boolean update(Task updateObject) {
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("title", updateObject.getTitle());
		parameterSource.addValue("description", updateObject.getDescription());
		parameterSource.addValue("user_id", updateObject.getUser().getId());
		parameterSource.addValue("task_id", updateObject.getId());
		
		return template.update(UPDATE_STATEMENT, parameterSource) != 0;
	}

	@Override
	public boolean delete(Long primeKey) {
		
		SqlParameterSource parmeterSource = new MapSqlParameterSource("task_id", primeKey);
		return template.update(DELETE_STATEMENT, parmeterSource) != 0;
	}

	@Override
	public List<Task> getAll() {
		return null;
	}

	@Override
	public Task getTaskById(Long task_id) {
		
		final String statement = "SELECT * FROM task LEFT JOIN user ON task.user_id=user.user_id WHERE task.task_id=:task_id";
		
		Task resultTask = null;
		SqlParameterSource parameterSource = new MapSqlParameterSource("task_id", task_id);
		List<Task> taskList = template.query(statement, parameterSource, new TaskRowMapper());
		
		if(taskList.size() > 0 && taskList.get(0) != null)
			resultTask = taskList.get(0);
		
		return resultTask;
	}
	
	@Override
	public List<Task> getAllTasksByUser(Long user_id) {
		
		final String statement = "SELECT * FROM task LEFT JOIN user ON task.user_id=user.user_id WHERE task.user_id=:user_id";
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("user_id", user_id);
		List<Task> taskList = template.query(statement, parameterSource, new TaskRowMapper());
		
		return taskList;
	}
	
	private class TaskRowMapper implements RowMapper<Task>{

		@Override
		public Task mapRow(ResultSet resultSet, int arg1) throws SQLException {
			
			Task task = new Task();
			task.setId(resultSet.getLong("task_id"));
			task.setTitle(resultSet.getString("title"));
			task.setDescription(resultSet.getString("description"));
			
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			
			task.setUser(user);
			return task;
		}
	}
}
