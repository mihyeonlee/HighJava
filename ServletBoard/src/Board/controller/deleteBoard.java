package Board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardService;
import Board.service.BoardServiceImpl;

/**
 * Servlet implementation class deleteBoard
 */
@WebServlet("/deleteBoard")
public class deleteBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 삭제할 게시물번호 가져오기
		int bdNo = Integer.parseInt(req.getParameter("bdNo"));
		
		//2. 서비스 객체 생성
		BoardService service = BoardServiceImpl.getInstance();
		
		//3. 회원정보 조회
		int cnt = service.deleteBoard(bdNo);
		
		String msg = "";
		
		if(cnt > 0) {
			msg ="성공";
		}else {
			msg ="실패";
		}
		
		//4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/selectAllBoard?msg=" + URLEncoder.encode(msg,"utf-8");
		resp.sendRedirect(redirectUrl); // 목록 조회화면으로 리다이렉트 처리하기
		
	}

	

}
