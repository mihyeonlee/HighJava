package board_handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;
import board_vo.BoardVO;

public class SelectBoardCommandHandler implements CommandHandler {

	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	private static final String VIEW_PAGE = "/WEB-INF/view/board/selectBoard.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 1. 데이터 가져오기
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		// 2. 서비스 객체 가져오기 및 메서드 호출
		BoardVO bv = new BoardVO();
		bv.setBoard_no(board_no);
		List<BoardVO> boardList = service.getSearchBoard(bv);
		
		// 3. 결과값을 request 객체에 저장
		req.setAttribute("boardList", boardList.get(0)); // 한개만 가져온다.
		
		return VIEW_PAGE;
	}

}
