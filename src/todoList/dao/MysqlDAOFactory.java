package todoList.dao;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	@Override
	public TaskDAO getTaskDAO() {
		return new TaskDAOImpl();
	}
}
