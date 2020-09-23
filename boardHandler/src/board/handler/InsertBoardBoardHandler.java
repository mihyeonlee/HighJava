package board.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class InsertBoardBoardHandler implements BoardHandler{
	private static final String VIEW_PAGE = "/WEB-INF/view/board/insertForm.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String method = req.getMethod();
		if(method.equals("GET")) {
			return VIEW_PAGE;
		}else if(method.equals("POST")) {
			//1. 요청의 파라미터 정보 가져오기
			String board_title= req.getParameter("board_title");
			String board_writer = req.getParameter("board_writer");
			String board_content = req.getParameter("board_content");
			
			
			//2. 서비스 객체 생성하기 
			IBoardService boardService = BoardServiceImpl.getInstance();
			
			//3. 회원정보 등록
			BoardVO bv = new BoardVO();
			bv.setBoard_content(board_content);
			bv.setBoard_title(board_title);
			bv.setBoard_writer(board_writer);
			
			int cnt = boardService.insertBoard(bv); //등록
			
			String msg = "";
			
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			
			}
			
			
			//4. 목록조회화면으로 이동
			String re = req.getContextPath() +  "/board/list.do?msg="+ URLEncoder.encode(msg, "UTF-8");
			
			return "redirect:"+re;
			
		}
		
		return null;
	}

}
