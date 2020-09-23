package Board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardService;
import Board.service.BoardServiceImpl;
import Board.vo.BoardVO;

@WebServlet("/insertBoard")
public class insertBoardServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	//한글깨짐 방지
		
		//1. 요청 파라미터 정보 가져오기
//		int bdNo = Integer.parseInt(req.getParameter("bdNo"));	//insert.jsp꺼 불러옴
		String bdTitle = req.getParameter("bdTitle");
		String bdWriter = req.getParameter("bdWriter");
//		String bdDate = req.getParameter("bdDate");
		String bdContent = req.getParameter("bdContent");
		
		//2. 서비스 객체 생성하기
		BoardService service = BoardServiceImpl.getInstance();
		
		//3. 회원정보 등록
		BoardVO bd = new BoardVO();
//		bd.setBoard_no(bdNo);
		bd.setBoard_title(bdTitle);
		bd.setBoard_writer(bdWriter);
//		bd.setBoard_date(bdDate);
		bd.setBoard_content(bdContent);
		
		int cnt = service.insertBoard(bd);
		
		String msg="";
		if(cnt >0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//4. 목록 조회화면으로 이동
		String redirectUrl = req.getContextPath()+ "/selectAllBoard?msg="
				+ URLEncoder.encode(msg,"utf-8");
		resp.sendRedirect(redirectUrl);
	} 

}
