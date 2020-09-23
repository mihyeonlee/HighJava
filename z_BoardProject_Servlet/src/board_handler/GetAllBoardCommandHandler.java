package board_handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;
import board_vo.BoardVO;

public class GetAllBoardCommandHandler implements CommandHandler {

	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	private static final String VIEW_PAGE = "/WEB-INF/view/board/selectAllBoard.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// msg가 null 값이면 "" 출력, 아니면 msg에 담긴 문자열 출력
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");
		
		// 1. 요청 데이터 없음.
		
		// 2. 서비스 객체를 통해 메서드 호출
		List<BoardVO> boardList = service.getAllBoard();
		
		// 3. 결과값을 req에 담기
		req.setAttribute("msg", msg);
		req.setAttribute("boardList", boardList);
		
		return VIEW_PAGE;
	}

}
