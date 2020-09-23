package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
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

@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet {
	// 소스중복으로 멤버변수 선언
	private IMemberService memberService = MemberServiceImpl.getInstance();
	
	// 브라우저에서 허용하는 URL범위를 넘을 경우 전송할 수 없다. 때문에 post방식으로 넘긴다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성하기
		// IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		List<MemberVO> memList = memberService.getSearchMember(mv);
		
		// 3. request객체에 회원정보 저장 setAttribute사용
		req.setAttribute("memVO", memList.get(0));
		
		// 4. VIEW화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/member/updateForm.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
	// post는 양에 한정이 없다. 데이터보낼때 form에 적고 submit해 post방식으로 전송한다. 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 파라미터정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 2. 서비스 객체 생성하기 - 멤버변수선언으로 해결
		
		// 3. 회원정보 수정
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = memberService.updateMember(mv); // 회원정보 수정.
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		// 4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/selectAllMember?msg=" + URLEncoder.encode(msg,"utf-8");
		
		resp.sendRedirect(redirectUrl); // 목록조회화면으로 리다이렉트 처리하기 새로고침해도 update가 반복되지 않는다. forward는 반복됨.
		
		
		
		
		
		
		
		
	}
}
