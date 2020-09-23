package board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class SelectAllBoardBoardHandler implements BoardHandler{
	private static final String VIEW_PAGE = "/WEB-INF/view/board/list.jsp";
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");
		
		//1. 서비스객체 생성하기
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		//2. 전체게시글 가져오는 메서드호출
		List<BoardVO> boardList = boardService.displayBoardAll();
		
		//3. 결과를 화면에 출력하기
		req.setAttribute("msg", msg);
		req.setAttribute("boardList", boardList);
		
		
		return VIEW_PAGE;
	}

}
