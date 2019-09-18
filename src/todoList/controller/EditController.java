package todoList.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoList.Exceptions.IncorrectRequestException;
import todoList.beans.Task;
import todoList.beans.User;
import todoList.service.TaskService;

@WebServlet("/edit")
public class EditController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String taskIdString = request.getParameter("inputId");
		if(taskIdString == null) {
			response.sendError(400);
			return;
		}
		Long taskId;
		try {
			taskId = Long.valueOf(taskIdString);
		} catch (NumberFormatException e) {
			response.sendError(400);
			return;
		}
		TaskService service = new TaskService();
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			response.sendError(403);
			return;
		}
		Task taskBeforeChanges;
		try {
			taskBeforeChanges = service.getTaskById(taskId, user);
		} catch (IncorrectRequestException e) {
			response.sendError(404);
			return;
		}
		request.setAttribute("task", taskBeforeChanges);
		request.getRequestDispatcher("WEB-INF/jsp/editTask.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("inputTitle");
		String description = request.getParameter("inputDescription");
		String taskIdString = request.getParameter("inputId");
		if(taskIdString == null) {
			response.sendError(400);
			return;
		}
		Long taskId;
		try {
			taskId = Long.valueOf(taskIdString);
		} catch (NumberFormatException e) {
			response.sendError(400);
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			response.sendError(403);
			return;
		}
		TaskService service = new TaskService();
		try {
			service.updateTask(taskId, title, description, user);
		} catch (IncorrectRequestException e) {
			response.sendError(404);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
