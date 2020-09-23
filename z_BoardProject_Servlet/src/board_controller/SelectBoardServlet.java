package board_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;
import board_vo.BoardVO;

@WebServlet("/selectBoard")
public class SelectBoardServlet extends HttpServlet {

	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 데이터 가져오기
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		// 2. 서비스 객체 가져오기 및 메서드 호출
		BoardVO bv = new BoardVO();
		bv.setBoard_no(board_no);
		List<BoardVO> boardList = service.getSearchBoard(bv);
		
		// 3. 결과값을 request 객체에 저장
		req.setAttribute("boardList", boardList);
		
		// 4. jsp로 이동
		req.getRequestDispatcher("/WEB-INF/view/board/selectBoard.jsp").forward(req, resp);
		
	}
	
}
