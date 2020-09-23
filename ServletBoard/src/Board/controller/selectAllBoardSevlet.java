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

@WebServlet("/selectAllBoard")
public class selectAllBoardSevlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");
		
		//1. 서비스 객체 생성하기
		BoardService boardService = BoardServiceImpl.getInstance();
		
		//2. 게시물 정보 조회
		List<BoardVO> bdList = boardService.getAllBoard();
		
		//3. 결과를 화면(브라우저)에 출력하기
		req.setAttribute("msg", msg);
		req.setAttribute("bdList", bdList);
		
		RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
