package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/selectMember")
public class SelectMemberServlet extends HttpServlet{
	private IMemberService memberService = MemberServiceImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 호출하기
		
		// 2. 회원정보 조회
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		List<MemberVO> memList = memberService.getSearchMember(mv);
		
		// 3. request객체에 회원정보 저장 setAttribute사용
		req.setAttribute("memVO", memList.get(0));
		
		// 4. VIEW화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/member/select.jsp");
		dispatcher.forward(req, resp);
	}
}
