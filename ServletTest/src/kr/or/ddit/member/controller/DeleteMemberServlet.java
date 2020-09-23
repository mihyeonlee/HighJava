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

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet{
	private IMemberService memberService = MemberServiceImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 삭제할 회원ID 가져오기
		String memId = req.getParameter("memId");
		
		// 2. 회원정보 조회
		int cnt = memberService.deleteMember(memId);
		
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
