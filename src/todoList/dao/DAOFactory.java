package todoList.dao;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();
	
	public abstract TaskDAO getTaskDAO();
	
	public static DAOFactory getDAOFactory() {
		return new MysqlDAOFactory();
	}
}
