package kr.or.ddit.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class DeleteMemberCommandHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/WEB-INF/view/member/list.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		IMemberService memberService = MemberServiceImpl.getInstance();
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
		
		return VIEW_PAGE;
	}

	
	
	
	
	
	
	
	
}
