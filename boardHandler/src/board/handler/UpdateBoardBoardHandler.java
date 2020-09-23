package board.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class UpdateBoardBoardHandler implements BoardHandler{
	//1. 서비스 객체 생성하기
	private IBoardService service = BoardServiceImpl.getInstance();
	// update jsp출력
	private static final String UPDATE_PAGE = "/WEB-INF/view/board/update.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String method = req.getMethod();
		if(method.equals("GET")){
			// select에서 넘긴 폼
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			
			BoardVO bv = new BoardVO();
			bv.setBoard_no(board_no);
			
			// update.jsp에서 수정이 안되는 값을 적어놓을때 사용하기 위해서
			List<BoardVO> boardList = service.searchBoard(bv);
			
			//2. request객체에 게시물정보 저장
			req.setAttribute("bv",boardList.get(0));
					
			//3. VIEW화면으로 이동
			
			return UPDATE_PAGE;
			
		}else if(method.equals("POST")){
			// 1. 요청의 파라미터정보 가져오기
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			String board_title= req.getParameter("board_title");
			String board_content = req.getParameter("board_content");
			
			// 수정
			BoardVO bv = new BoardVO();
			bv.setBoard_no(board_no);
			bv.setBoard_content(board_content);
			bv.setBoard_title(board_title);
			
			int cnt = service.updateBoard(bv);
			
			String msg = "";
			
			if(cnt > 0) {
				msg ="성공";
			}else {
				msg ="실패";
			}
			
			//4. 목록조회화면으로 이동
			String re = req.getContextPath() +  "/board/list.do?msg="+ URLEncoder.encode(msg, "UTF-8");
			
			return "redirect:"+re;
			
		}
		return null;
	}

}
