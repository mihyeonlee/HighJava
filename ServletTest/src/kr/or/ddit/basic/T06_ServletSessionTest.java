package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T06_ServletSessionTest extends HttpServlet{
/**
 * 세션(HttpSession)객체에 대하여...
 * 
 * - 세션을 통해서 사용자(웹브라우저)별로 구분하여 정보를 관리할 수 있다. (세션ID이용)
 * - 쿠키를 사용할 때보다 보안이 향상된다. (정보가 서버에 저장되기 때문에)
 * - 세션객체를 가져오는 방법
 * 		HttpSession session = request.getSession(boolean값);
 * 		boolean값 : true인 경우 => 세션객체가 존재하지 않으면 새로 생성한다. 
 * 				   false인 경우 => 세션객체가 존재하지 않으면 null을 리턴함.
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션을 가져오는데 없으면 새로 생성한다.
		HttpSession session = req.getSession(true); // true 없으면 만들고 false는 없으면 없는대로 냅둬라
		
		// 생성시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		// 마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		session.setMaxInactiveInterval(10);
		
		String title = "재방문을 환영합니다.";
		Integer visitCnt = new Integer(0);
		String visitCntKey = new String("visitCount");
		String userIdKey = new String("userId");
		String userId = new String("ABCD");
		
		if(session.isNew()) { //세션이 새로 만들어 졌는지 확인 
			title = "처음 방문을 환영합니다.";
			session.setAttribute(userIdKey, userId);
			
		}else {
			visitCnt = (Integer)session.getAttribute(visitCntKey);
			visitCnt++;
			userId = (String)session.getAttribute(userIdKey);
		}
		session.setAttribute(visitCntKey, visitCnt);
		
		// 응답헤더에 인코딩 및 content type 설정
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println(
				"<!DOCTYPE html>\n" + "<head><title>" + title + "</title></head>\n"
						+ "<body>\n" + "<h1 align=\"center\">" + title + "</h1>\n"
								+ "<h2 align=\"center\">세션정보</h2>\n"
								+ "<table border =\"1\" align=\"center\">\n"
								+ "<tr bgcolor=\"orange\">\n" + "<th>구분</th><th>값</th></tr>\n"
										+ "<tr>\n"
										+ "<td>세션ID</td>\n"
										+ "<td>" + session.getId() + "</td>"
										+ "</tr>\n" 
										
										+ "<tr>\n"
										+ "<td>생성시간</td>\n"
										+ "<td>" + createTime + "</td>"
										+ "</tr>\n" 
										
										+ "<tr>\n"
										+ "<td>마지막 접근시간</td>\n"
										+ "<td>" + lastAccessTime + "</td>"
										+ "</tr>\n" 
										
										+ "<tr>\n"
										+ "<td>User ID</td>\n"
										+ "<td>" + userId + "</td>"
										+ "</tr>\n" 
										
										+ "<tr>\n"
										+ "<td>방문횟수</td>\n"
										+ "<td>" + visitCnt + "</td>"
										+ "</tr>\n"
										
										+ "</table>\n"
										+ "</body>\n"
										+ "</html>" 
				);
	}
	
	
	
}
