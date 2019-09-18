package todoList.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoList.Exceptions.PasswordNotMachingExeption;
import todoList.service.UserService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("inputEmail");
		String password1 = request.getParameter("inputPassword");
		String password2 = request.getParameter("inputConfirmPassword");
		
		UserService service = new UserService();
		
		if(service.readUserByEmail(email) != null) {
			request.setAttribute("errorMessage", "Typed email has been already registered!");
			request.setAttribute("returnAdress", "register");
			request.getRequestDispatcher("WEB-INF/jsp/alert.jsp").forward(request, response);
			return;
		}
		
		try {
			service.addUser(email, password1, password2);
		} catch (PasswordNotMachingExeption e) {
			request.setAttribute("errorMessage", "Typed passwords does not match!</br> Pleas enter maching password in both inputs");
			request.setAttribute("returnAdress", "register");
			request.getRequestDispatcher("WEB-INF/jsp/alert.jsp").forward(request, response);
			return;
		}
		request.setAttribute("successMessage", "Registracion process completed");
		request.setAttribute("returnAdress", "home");
		request.getRequestDispatcher("WEB-INF/jsp/alert.jsp").forward(request, response);
	}
}
