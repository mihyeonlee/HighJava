package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 동작 방식에 대하여...
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 Servlet Container로 전송(요청)한다.
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 * 	(로딩이 안된 경우에는 로딩함. 로딩시 init()메서드 호출됨.)
 * 3. Servlet Container는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 * 	(HttpServletRequest 및  HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
 * 4. service()메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다. (doGet, doPost, doPut, doDelete 등)
 * 5. 요청 처리가 완료되면 (HttpServletRequest 및 HttpServletResponse 객체는 소멸된다.
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.
 */
public class T02_ServletTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post방식으로 넘어오는 Byte데이터를 인코딩 처리함. get방식은 톰캣의 URIEncoding 설정을 이용함.
		// 반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용됨.
//		req.setCharacterEncoding("utf-8");
		
		String name = req.getParameter("name"); // 요청 정보로부터 name 값을 가져옴.
		String age = req.getParameter("age");
		String food = req.getParameter("food");
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(food);
		System.out.println("요청 URL : " + req.getServletPath()); // 요청 URL
		//---------------------------------------------------------------------------------------------------------
		
		
//		resp.setCharacterEncoding("utf-8");  				// 응답메시지 인코딩 설정
//		resp.setContentType("text/plain; charset=UTF-8");  // 응답 메시지 컨텐트타입 설정 
		resp.setContentType("text/plain");  // 응답 메시지 컨텐트타입 설정 
		
		// 실제 수행 할 로직(기능)이 시작되는 부분... 
		
		PrintWriter out = resp.getWriter(); // 사용자에게 보내는 화면에 적을 수 잇는 객체		
		out.println("name => "+ name);
		out.println("age => "+ age);
		out.println("food => "+ food); // 버퍼에만 있숨 메서드가 끝나면 톰캣이 보낼텐데 RequestDispatcher가 호출되며 그 요청을 수행한다. 
		//out.flush(); 
		//java.lang.IllegalStateException: Cannot forward after response has been committed
		//버퍼의 내용을 방출하기 시작하면 forward및 redirect 시 예외가 발생함. (방출전엔 버퍼내용 무시됨) 한번방출하면 끝
		
//		 RequestDispatcher dispatcher = req.getRequestDispatcher("/index.html"); // html
//		 dispatcher.forward(req, resp); // 작업과정을 사용자가 알 수 없다. 어떤 작업을 요청했는지 알수 없음.
		
		// URL주소가 html페이지로 변경된다. 요청페이지가 그대로 들어나며 요청이 여러번일어난다. forward는 변하지 않는다.
		//resp.sendRedirect(req.getContextPath() + "/index.html");
		 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
