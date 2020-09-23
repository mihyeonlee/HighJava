package board_controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_service.BoardServiceImpl;
import board_service.IBoardService;
import board_vo.BoardVO;

@WebServlet("/updateBoard")
public class UpdateBoardServlet extends HttpServlet {
	
	// 서비스 객체 생성
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청할 데이터를 가져온다. - 수정할 데이터 조회를 위한  아이디
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		// 2. 서비스 객체가져오기 및 메서드 호출
		BoardVO bv = new BoardVO();
		bv.setBoard_no(board_no);
		
		List<BoardVO> boardList = service.getSearchBoard(bv);
		
		// 3. 결과값을 request에 저장
		req.setAttribute("boardList", boardList.get(0));
		
		// 4. view화면으로 이동 - jsp
		req.getRequestDispatcher("/WEB-INF/view/board/updateBoard.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 변경할 데이터를 가져온다.
		String board_title = req.getParameter("board_title");
		String board_writer = req.getParameter("board_writer");
		String board_content = req.getParameter("board_content");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String board_date = req.getParameter("board_date");
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_content(board_content);
		bv.setBoard_no(board_no);
		bv.setBoard_date(board_date);
		
		// 2. 서비스 객체 가져오기 및 메서드 호출
		int result = service.updateBoard(bv);
		
		String msg = "";
		if(result > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		// 3. 결과값을 request에 저장 - redirect 이용
//		String redirectUrl = req.getContextPath() + "/board/selectAllBoard.do?msg=" 
//				                        + URLEncoder.encode(msg, "utf-8");
		String redirectUrl = req.getContextPath() + "/getAllBoard?msg=" 
				+ URLEncoder.encode(msg, "utf-8");
		
		resp.sendRedirect(redirectUrl);

	
	}

}
