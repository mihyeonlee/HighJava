package kr.or.ddit.download;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어노테이션 벨류가 하나면 생략가능 원래는 value = "/DownloadServlet"
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{
	
	private static final String DOWNLOAD_DIR = "d:/D_Other/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileName = "test.txt";
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream"); // 바이너리를 보낸다는 의미 
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); //(헤더이름, 벨류값(다운받을꺼다)파일이름)
		
		// 버퍼를 쓰거나 temp어쩌구 쓰면 속도향상시킬수잇음
		FileInputStream fis = new FileInputStream(DOWNLOAD_DIR + fileName);
		ServletOutputStream out = resp.getOutputStream();
		
		int c;
		while((c = fis.read()) != -1) {
			out.write(c);
		}
		fis.close();
	}
}
