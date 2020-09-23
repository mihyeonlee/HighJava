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

@WebServlet("/getAllBoard")
public class GetAllBoardServlet extends HttpServlet {
	
	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// msg가 null 값이면 "" 출력, 아니면 msg에 담긴 문자열 출력
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");
		
		// 1. 요청 데이터 없음.
		
		// 2. 서비스 객체를 통해 메서드 호출
		List<BoardVO> boardList = service.getAllBoard();
		
		// 3. 결과값을 req에 담기
		req.setAttribute("msg", msg);
		req.setAttribute("boardList", boardList);
		
		// 4. jsp로 이동
		// 화면 노출 방지를 위해서 접근 불가능한 url을 서버에서 설정
		// WEB-INF 는 서버에서만 접근이 가능
		req.getRequestDispatcher("/WEB-INF/view/board/selectAllBoard.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp); // post 방식으로 처리할 시에도 get방식과 똑같이 처리
	}

}
