package todoList.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoList.beans.User;
import todoList.service.TaskService;

@WebServlet("/add")
public class AddTaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/addTask.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("inputTitle");
		String description = request.getParameter("inputDescription");
		
		if(request.getSession().getAttribute("user") == null) {
			response.sendError(403);
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		TaskService service = new TaskService();
		service.createTask(title, description, user);
		
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
