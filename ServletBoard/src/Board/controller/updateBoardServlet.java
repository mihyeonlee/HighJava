package Board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardService;
import Board.service.BoardServiceImpl;
import Board.vo.BoardVO;

/**
 * Servlet implementation class updateBoardServlet
 */
@WebServlet("/updateBoard")
public class updateBoardServlet extends HttpServlet {
		private BoardService service = BoardServiceImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bdNo = Integer.parseInt(req.getParameter("bdNo"));		// jsp꺼 불러오기
		//1. 서비스 객체 생성하기(위에서 선언&생성함)
		
		BoardVO bd = new BoardVO();
		bd.setBoard_no(bdNo);
		BoardVO bdVO = service.getBoard(bdNo);		// bdNo을 service를 이용해서 BoardVO로 넘겨줘야한다.
													// (이거를 하지않으면 update.jsp에서 value값 못불러옴)
		
		//2. request객체에 게시물정보 저장
		req.setAttribute("bdVO",bdVO);
				
		//3. VIEW화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	//한글깨짐 방지
		
		//1. 요청 파라미터 정보 가져오기
		int bdNo = Integer.parseInt(req.getParameter("bdNo"));	//insert.jsp꺼 불러옴
		String bdTitle = req.getParameter("bdTitle");
//		String bdWriter = req.getParameter("bdWriter");	// xml 쿼리 기준으로만 가져오면됨
		String bdContent = req.getParameter("bdContent");
		
		//2. 서비스객체 생성하기(위에서 선언&생성함)
		
		//3. 회원정보 수정
		BoardVO bd = new BoardVO();
		bd.setBoard_no(bdNo);
		bd.setBoard_title(bdTitle);
		bd.setBoard_content(bdContent);
		
		int cnt = service.updateBoard(bd);
		
		String msg = "";
		
		if(cnt > 0) {
			msg ="성공";
		}else {
			msg ="실패";
		}
		
		//4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/selectAllBoard?msg=" + URLEncoder.encode(msg,"utf-8");
		resp.sendRedirect(redirectUrl); // 목록 조회화면으로 리다이렉트 처리하기
				
	}

}
