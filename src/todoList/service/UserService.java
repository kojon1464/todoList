package todoList.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import todoList.Exceptions.PasswordNotMachingExeption;
import todoList.beans.User;
import todoList.dao.DAOFactory;
import todoList.dao.UserDAO;

public class UserService {

	public void addUser(String email, String password1, String password2) throws PasswordNotMachingExeption {
		
		if(!password1.equals(password2))
			throw new PasswordNotMachingExeption();
			
		User user = new User();
		user.setEmail(email);
		user.setPassword(encryptPassword(password1));
		UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
		userDAO.create(user);
	}
	
	private String encryptPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger number = new BigInteger(1, digest.digest(password.getBytes()));
		return number.toString(16);
	}
	
	public User readUserByEmail(String email) {
		
		UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
		return userDAO.getUserByEmail(email);
	}
}
