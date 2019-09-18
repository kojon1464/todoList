package todoList.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todoList.Exceptions.IncorrectRequestException;
import todoList.beans.User;
import todoList.service.TaskService;

@WebServlet("/remove")
public class RemoveController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Long task_id = Long.valueOf(request.getParameter("inputId"));
		
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			response.sendError(403);
			return;
		}
		Long user_id = user.getId();
		
		TaskService service = new TaskService();
		try {
			service.deleteTast(task_id, user_id);
		} catch (IncorrectRequestException e) {
			response.sendError(400);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
