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
	
	public boolean deleteTast(Long taskId, Long userId) throws IncorrectRequestException {
		
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		
		Task taskToRemove = taskDAO.getTaskById(taskId);
		if(taskToRemove == null || taskToRemove.getUser().getId() != userId)
			throw new IncorrectRequestException();
		
		return taskDAO.delete(taskId);
	}
	
	public boolean updateTask(Long taskId, String title, String description, User user) throws IncorrectRequestException {
		
		DAOFactory factory = DAOFactory.getDAOFactory();
		TaskDAO taskDAO = factory.getTaskDAO();
		
		Task taskToUpdate = taskDAO.getTaskById(taskId);
		if(taskToUpdate == null || taskToUpdate.getUser().getId() != user.getId())
			throw new IncorrectRequestException();
		
		Task task = new Task();
		task.setId(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setUser(user);
		
		return taskDAO.update(task);
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
