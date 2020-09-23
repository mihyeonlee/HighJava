package board_handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;
import board_vo.BoardVO;

public class InsertBoardCommandHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/board/insertBoard.jsp";
	
	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String method = req.getMethod(); // 전송 방식을 가져온다.
		
		if(method.equals("GET")) { // get방식 일때
			return VIEW_PAGE;
			
		}else if(method.equals("POST")) { // post방식 일때
			req.setCharacterEncoding("utf-8");
			
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
			String redirectUrl = req.getContextPath() + "/board/selectAllBoard.do?msg=" + URLEncoder.encode(msg, "utf-8");
			
			return "redirect:" + redirectUrl;
			
		}
		
		return null;
	}

	
}
