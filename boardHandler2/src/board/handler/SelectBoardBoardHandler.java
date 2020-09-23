package board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class SelectBoardBoardHandler implements BoardHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/select.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		//1. 서비스 객체 생성
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_no(board_no);
		
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		//3. request객체에 회원정보저장 
		req.setAttribute("boardVO", boardList.get(0));
		
		return VIEW_PAGE;
	}

}
