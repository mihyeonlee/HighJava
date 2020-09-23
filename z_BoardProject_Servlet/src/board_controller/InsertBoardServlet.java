package board_controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;
import board_vo.BoardVO;

@WebServlet("/insertBoard")
public class InsertBoardServlet extends HttpServlet {

	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 게시글 작성 폼으로 이동
		req.getRequestDispatcher("/WEB-INF/view/board/insertBoard.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
		
		// 1. 전송 데이터 가져오기
		String board_title = req.getParameter("board_title");
		String board_writer = req.getParameter("board_writer");
		String board_content = req.getParameter("board_content");
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_content(board_content);
		
		// 2. 서비스 객체 가져오기 및 메서드 호출
		Object result = service.insertBoard(bv);
		
		String msg = "";
		if(result == null) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		// 3. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/getAllBoard?msg=" 
		                                  + URLEncoder.encode(msg, "utf-8");
		
		resp.sendRedirect(redirectUrl);
		
	}
	
}
