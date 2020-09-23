package board.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardServiceImpl;
import board.service.IBoardService;


public class DeleteBoardBoardHandler implements BoardHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1. 삭제할 게시물번호 가져오기
				int board_no = Integer.parseInt(req.getParameter("board_no"));
				
				//2. 서비스 객체 생성
				IBoardService service = BoardServiceImpl.getInstance();
				
				//3. 회원정보 조회
				int cnt = service.deleteBoard(board_no);
				
				String msg = "";
				
				if(cnt > 0) {
					msg ="성공";
				}else {
					msg ="실패";
				}
				
				//4. 목록 조회 화면으로 이동
				String redirectUrl = req.getContextPath() + "/board/list.do?msg=" + URLEncoder.encode(msg,"utf-8");
				resp.sendRedirect(redirectUrl); // 목록 조회화면으로 리다이렉트 처리하기
		return null;
	}

}
