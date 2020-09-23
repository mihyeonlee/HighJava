package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일 업로드 예제
 * @author PC-03
 *
 */
public class UploadServlet extends HttpServlet{
	//파일넘겨줄땐 post방식이고 인코딩타입은 multipart form-data 로해야 잘된다.ㅋㅋㅋ 중요함
	private static final String UPLOAD_DIRECTORY = "upload_files";
	// 메모리 임계크기 (이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THREASHOLD = 1024*1024*3;  // 3MB
	
	private static final long MAX_FILE_SIZE = 1024*1024*40; // 파일 1개당 최대 크기 40MB
	private static final long MAX_REQIEST_SIZE = 1024*1024*50; // 요청 파일 최대 크기 50MB
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(req)) { // 인코딩 타입이 multipart/form-data인 경우..	.
			// itemfactory에 정보저장
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THREASHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQIEST_SIZE);
			
			//웹애플리케이션 루트 디렉토리 기준으로 업로드 경로 설정하기 실제경로알려주는 getRealPath()
			String uploadPath = getServletContext().getRealPath("")
					+ File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				List<FileItem> formItems = upload.parseRequest(req);
				
				if(formItems != null && formItems.size() >0) {
					for(FileItem item : formItems) {
						if(!item.isFormField()) {// 파일인 경우 폼데이터아니고 바이너리
							String fileName = new File(item.getName()).getName(); //파일명만 추출
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile); // 업로드 파일저장
							req.setAttribute("message", "업로드완료됨. => 파일명: " + fileName);
						}else {
							System.out.println("파라미터명 : "+ item.getFieldName());
							System.out.println("파라미터값 : " + item.getString());
						}
							
					}
				}
			} catch (Exception e) {
				req.setAttribute("message", "예외발생: " + e.getMessage());
			}
			
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨!!");
		}
	}
	
}
