package todoList.service;

import java.util.List;

import todoList.Exceptions.IncorrectRequestException;
import todoList.beans.Task;
import todoList.beans.User;
import todoList.dao.DAOFactory;
import todoList.dao.TaskDAO;

public class TaskService {

	public void createTask(String title, String description, User user) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setUser(user);
		taskDAO.create(task);
	}
	
	public boolean deleteTast(Long task_id, Long user_id) throws IncorrectRequestException {
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		Task taskToRemove = taskDAO.getTaskById(task_id);
		if(taskToRemove == null || taskToRemove.getUser().getId() != user_id)
			throw new IncorrectRequestException();
		return taskDAO.delete(task_id);
	}
	
	public boolean updateTask(Long task_id, String title, String description, User user) throws IncorrectRequestException {
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		Task taskToUpdate = taskDAO.getTaskById(task_id);
		if(taskToUpdate == null || taskToUpdate.getUser().getId() != user.getId())
			throw new IncorrectRequestException();
		Task updatedTask = new Task();
		updatedTask.setId(task_id);
		updatedTask.setTitle(title);
		updatedTask.setDescription(description);
		updatedTask.setUser(user);
		return taskDAO.update(updatedTask);
	}
	
	public Task getTaskById(Long taskId, User user) throws IncorrectRequestException {
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		Task resultTask = taskDAO.getTaskById(taskId);
		if(resultTask == null || resultTask.getUser().getId() != user.getId())
			throw new IncorrectRequestException();
		return resultTask;
	}
	
	public List<Task> getAllTasksByUser(User user) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		return taskDAO.getAllTasksByUser(user.getId());
	}
}
