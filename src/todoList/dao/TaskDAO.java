package todoList.dao;

import java.util.List;

import todoList.beans.Task;

public interface TaskDAO extends GenericDAO<Task, Long> {

	public Task getTaskById(Long taskId);
	
	public List<Task> getAllTasksByUser(Long userId);
}
