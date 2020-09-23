package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T10_SessionListenerTest extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("AAA", "AAA입니다.");	// 세션 속성값 추가
		req.getSession().setAttribute("AAA", "AAA수정입니다.");// 세션 속성값 변경
		req.getSession().setAttribute("BBB", "BBB입니다.");  // 세션 속성값추가
		req.getSession().removeAttribute("AAA");  		  // 기존 세션 속성값 제거
		
		//HTTP세션 영역내에 HttpSessionBindingListener를 구현한 객체가 바인딩 되면 호출됨.
		MySessionBindingListener bindingListener = new MySessionBindingListener();
		req.getSession().setAttribute("obj", bindingListener);
		req.getSession().removeAttribute("obj");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
