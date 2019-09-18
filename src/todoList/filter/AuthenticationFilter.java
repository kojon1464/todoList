package todoList.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import todoList.beans.User;
import todoList.service.UserService;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(httpRequest.getUserPrincipal() != null && httpRequest.getSession().getAttribute("user") == null)
			saveUserInSesion(httpRequest);
		
		chain.doFilter(request, response);
	}
	
	private void saveUserInSesion(HttpServletRequest request) {
		
		UserService service = new UserService();
		User user = service.readUserByEmail(request.getUserPrincipal().getName());
		request.getSession().setAttribute("user", user);
	}
}
