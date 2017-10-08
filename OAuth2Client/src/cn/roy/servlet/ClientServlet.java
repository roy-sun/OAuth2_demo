package cn.roy.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		/*Enumeration<String> initParameterNames = config.getInitParameterNames();
		while(initParameterNames.hasMoreElements()){
			String paramName = initParameterNames.nextElement();
			servletContext.setAttribute(paramName, config.getInitParameter(paramName));
		}*/
		
		try {
			Properties properties = new Properties();
			properties.load(this.getClass().getResourceAsStream("/config.properties"));
			Enumeration<?> propertyNames = properties.propertyNames();
			while(propertyNames.hasMoreElements()){
				String paramName = (String) propertyNames.nextElement();
				servletContext.setAttribute(paramName, properties.get(paramName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		String result = null;
		switch (method) {
		case "accessToken":
			result = accessToken(req);
			break;
		case "userInfo":
			result = userInfo(req);
			break;
		default:
			result = "error";
			break;
		}
		ServletOutputStream out = resp.getOutputStream();
		out.write(result.getBytes());
	}
	
	private String accessToken(HttpServletRequest request){
		String redirect_uri = (String) request.getServletContext().getAttribute("redirect_uri");
		String grant_type = (String) request.getServletContext().getAttribute("grant_type");
		String client_id = (String) request.getServletContext().getAttribute("client_id");
		String client_secret = (String) request.getServletContext().getAttribute("client_secret");
		String code = request.getParameter("code");
		String url = (String) request.getServletContext().getAttribute("accessToken");
		String param = "redirect_uri="+redirect_uri+"&grant_type="+grant_type+"&client_id="+client_id+"&client_secret="+client_secret+"&code="+code;
		return callApi(param,url);
	}
	private String userInfo(HttpServletRequest request){
		String access_token = request.getParameter("access_token");
		String param = "access_token="+access_token;
		String url = (String) request.getServletContext().getAttribute("userInfo");
		return callApi(param,url);
	}
	
	private String callApi(String param,String url){
		String result = "";
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			URL accessTokenUrl = new URL(url);
			URLConnection connection = accessTokenUrl.openConnection();
			// 发送POST请求必须设置如下两行  
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			// 获取URLConnection对象对应的输出流  
			out = new PrintWriter(connection.getOutputStream());  
			// 发送请求参数  
			out.print(param);  
			// flush输出流的缓冲  
			out.flush();  
			// 定义BufferedReader输入流来读取URL的响应  
			in = new BufferedReader(  
			        new InputStreamReader(connection.getInputStream()));  
			String line;  
			while ((line = in.readLine()) != null) {
				if(result.length()>0)
					result += "\n";
			    result += line;  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(out!=null)
				out.close();
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
        return result;
	}
	
}
