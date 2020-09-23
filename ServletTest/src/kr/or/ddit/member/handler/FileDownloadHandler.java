package kr.or.ddit.member.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.FileVO;

public class FileDownloadHandler implements CommandHandler{
	
	private IAtchFileService fileService = AtchFileServiceImpl.getInstance();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		int fileId = Integer.parseInt( req.getParameter("fileId"));
		
		FileVO fileVO = fileService.select(fileId); // 파일정보 조회 
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream"); // 바이너리를 보낸다는 의미 
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileVO.getOrignlFileNm(), "utf-8")  + "\""); //(헤더이름, 벨류값(다운받을꺼다)파일이름)
		
		// 버퍼를 쓰거나 temp어쩌구 쓰면 속도향상시킬수잇음
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileVO.getFileStrePath()));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int c;
		while((c = bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
		
		return null;
	}

}
