package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.member.controller.FrontController;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class InsertMemberCommandHandler implements CommandHandler {
	private static Logger logger = Logger.getLogger(InsertMemberCommandHandler.class);
	private static final String VIEW_PAGE = "/WEB-INF/view/member/insertForm.jsp";
	
	private IAtchFileService fileService = AtchFileServiceImpl.getInstance();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String method = req.getMethod();
		if(method.equals("GET")) {
			return VIEW_PAGE;
		}else if(method.equals("POST")) {
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFile");
			FileVO atchFileVO = new FileVO();
			
			if(item != null && !item.getName().equals("")) {
				atchFileVO = fileService.saveAtchFile(item);
			}
			
			//1. 요청의 파라미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			//2. 서비스 객체 생성하기 
			IMemberService memService = MemberServiceImpl.getInstance();
			
			//3. 회원정보 등록
			MemberVO mv = new MemberVO();
			mv.setMem_id(memId);
			mv.setMem_name(memName);
			mv.setMem_tel(memTel);
			mv.setMem_addr(memAddr);
			if(atchFileVO != null) {
				mv.setAtch_file_id(atchFileVO.getAtchFileId());
			}
			
			int cnt = memService.insertMember(mv); //회원등록
			
			String msg = "";
			
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			
			}
			
			
			//4. 목록조회화면으로 이동
			String re = req.getContextPath() +  "/member/list.do?msg="+ URLEncoder.encode(msg, "UTF-8");
			
			return "redirect:"+re;
			
		}
		
		return null;
	}

}
