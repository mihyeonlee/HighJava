package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03_ServletParameterTest extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("T03_ServletParameterTest 초기화 중...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*
	 	요청 객체로부터 파라미터 데이터 가져오는 방법
	 	
	 	- getParameter() : 파라미터 값이 한개인 경우에 가져올 때 사용한다.
	 	- getParameterValues() : 파라미터값이 여러개인 경우에 사용한다.
	 	- getParameterNames() : request에 존재하는 모든 파라미터 정보를 가져올 때 사용한다.
	  	
	 */
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		//String hobby = req.getParameter("hobby");
		String[] hobbys = req.getParameterValues("hobby");
		String rlgn = req.getParameter("rlgn");
		String[] food = req.getParameterValues("food");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset:UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>username : "+ username + "</p>");
		out.println("<p>password : "+ password + "</p>");
		out.println("<p>gender : "+ gender + "</p>");
		for(String hobby : hobbys) {
			out.println("<p>hobby : "+ hobby + "</p>");
		}
		out.println("<p>종교 : "+ rlgn + "</p>");
		
		if(food != null) {
			for(String s : food) {
				out.print("<p>food : " + s + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			out.println("<p>파라미터 이름 : " + param + "</p>");
		}
		out.println("</body>");
		out.println("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
