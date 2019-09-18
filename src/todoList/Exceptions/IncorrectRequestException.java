package todoList.Exceptions;

public class IncorrectRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "IncorretRequestException";
	}
}
