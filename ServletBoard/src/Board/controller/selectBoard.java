package Board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardService;
import Board.service.BoardServiceImpl;
import Board.vo.BoardVO;

/**
 * Servlet implementation class selectBoard
 */
@WebServlet("/selectBoard")
public class selectBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int bdNo = Integer.parseInt(req.getParameter("bdNo"));
		
		//2. 서비스 객체생성
		BoardService service = BoardServiceImpl.getInstance();
		
		//3. 게시물정보 조회
		BoardVO bd = new BoardVO();
		bd.setBoard_no(bdNo);
		BoardVO list = service.getBoard(bdNo);
		
		//4. request객체에 게시물 정보 저장
		req.setAttribute("bdVO", list);
		
		//5. VIEW화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/board/select.jsp");
		dispatcher.forward(req, resp);
	}

}
