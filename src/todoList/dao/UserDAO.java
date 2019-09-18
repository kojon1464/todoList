package todoList.dao;

import todoList.beans.User;

public interface UserDAO extends GenericDAO<User, Long> {

	public User getUserByEmail(String email);
}
