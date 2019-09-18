package todoList.Exceptions;

public class PasswordNotMachingExeption extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Two wasward wasn't this same";
	}
}
