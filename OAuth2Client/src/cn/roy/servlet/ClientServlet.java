package cn.roy.servlet;

import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		Enumeration<String> initParameterNames = config.getInitParameterNames();
		while(initParameterNames.hasMoreElements()){
			String paramName = initParameterNames.nextElement();
			servletContext.setAttribute(paramName, config.getInitParameter(paramName));
		}
	}
}
