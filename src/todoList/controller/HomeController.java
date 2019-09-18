package todoList.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoList.beans.Task;
import todoList.beans.User;
import todoList.service.TaskService;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		if(request.getSession().getAttribute("user") == null) {
			response.sendError(403);
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		TaskService service = new TaskService();
		List<Task> tasks = service.getAllTasksByUser(user);
		request.setAttribute("taskList", tasks);
		request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
	}
}
