package board_handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;

public class DeleteBoardCommandHandler implements CommandHandler {
	
	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	private static final String VIEW_PAGE = "/WEB-INF/view/board/selectAllBoard.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 1. 클라이언트가 요청한 정보 가져오기
		// 삭제할 게시글 NO 가져오기
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		// 2. 서비스 메서드 호출
		int result = service.deleteBoard(board_no);
		
		String msg = "";
		
		if(result > 0) msg = "성공";
		else msg = "실패";
		
		// 3. req에 저장 - 게시판 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/board/selectAllBoard.do?msg=" 
				+ URLEncoder.encode(msg, "utf-8");
		
		return "redirect:"+redirectUrl;
	}

}
