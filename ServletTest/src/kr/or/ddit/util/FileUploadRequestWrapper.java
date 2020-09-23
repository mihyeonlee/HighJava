package kr.or.ddit.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * FileUpload API를 사용하는 HttpServletRequestWrapper클래스
 * (HttpServletRequest에 기반한 API를 사용하면서 멀티파트 처리 기능이 추가됨)
 * @author PC-03
 *
 */
public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	public static final String UPLOAD_DIRECTORY = "d:/D_Other/upload_files"; // 업로드 경로
	private boolean multipart = false; // 멀티파트 여부 확인
	
	private Map<String, String[]> parameterMap; // 폼필드(파라미터)데이터를 담기위한 맵
	private Map<String, Object> fileItemMap; 	// FileItem객체를 담기위한 맵
	

	// 기본생성자
	public FileUploadRequestWrapper(HttpServletRequest request) throws FileUploadException{
		this(request, -1, -1, -1, null);
		
	}
	
	// 사이즈정보넣은 생성자
	public FileUploadRequestWrapper(HttpServletRequest request, int memoryThreashold, int fileSizeMax, int maxRequestSize, String repositoryPath) throws FileUploadException {
		
		super(request);
		
		parsing(request, memoryThreashold, fileSizeMax, maxRequestSize, repositoryPath);
		
	}
	
	
	/**
	 * 멀티파트 데이터를 파싱하여 두개의 맵에 나누어 담는다.
	 * @param request
	 * @param memoryThreashold 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	 * @param fileSizeMax 파일 1개당 최대 크기
	 * @param maxRequestSize 요청 파일 최대 크기
	 * @param repositoryPath 임시파일 저장경로
	 * @throws FileUploadException 
	 */
	private void parsing(HttpServletRequest request, int memoryThreashold, int fileSizeMax, int maxRequestSize,
			String repositoryPath) throws FileUploadException {
		
		if(ServletFileUpload.isMultipartContent(request)) {
			multipart = true; // 멀티파트임을 설정함.
			
			parameterMap = new HashMap<String, String[]>();
			fileItemMap = new HashMap<>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			if(memoryThreashold !=-1) {
				factory.setSizeThreshold(memoryThreashold);
			}
			if(repositoryPath != null) {
				factory.setRepository(new File(repositoryPath));
			}else {
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			}
			
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			servletFileUpload.setFileSizeMax(fileSizeMax);
			servletFileUpload.setSizeMax(maxRequestSize);
			
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for(int i=0; i<list.size();i++) {
				FileItem fileItem = (FileItem) list.get(i);
				String name = fileItem.getFieldName(); // 필드명 가져오기
				
				if(fileItem.isFormField()) {
					String value = "";
					try {
						value = fileItem.getString("utf-8");
					}catch(UnsupportedEncodingException ex) {
						ex.printStackTrace();
					}
					String[] values = (String[]) parameterMap.get(name);
					if(values ==  null) { //처음 생성하는 경우
						values = new String[] {value};
					}else {  // 기본에 이미 존재하는 경우 원래것보다 1큰걸 새로만들어서 기존껄 카피한뒤 새로운걸더해 추가 /스트링은 길이조절이 불가
						String[] tempValues = new String[values.length + 1];
						System.arraycopy(values, 0, tempValues, 0, 1);
						tempValues[tempValues.length -1] = value;
						values = tempValues;
					}
					parameterMap.put(name, values);
					
				}else { //파일인 경우
					fileItemMap.put(name, fileItem);
				}
			}
			
		}
		
	}
	
	public boolean isMultipartContent() {
		return multipart;
	}
	
	public String getParameter(String name) {
		if(multipart) {
			String[] values = (String[]) parameterMap.get(name);
			if(values == null) return null;
			
			return values[0];
		}else {
			return super.getParameter(name);
		}
	}
	public String[] getParameterValues(String name) {
		if(multipart) {
			return (String[])parameterMap.get(name);
		}else {
			return super.getParameterValues(name);
		}
	}
	
	public Enumeration<String> getParameterNames(){
		if(multipart) {
			return new Enumeration<String>() {
				Iterator<String> iter = parameterMap.keySet().iterator();

				@Override
				public boolean hasMoreElements() {
					return iter.hasNext();
				}

				@Override
				public String nextElement() {
					return iter.next();
				}
			};
		}else {
			return super.getParameterNames();
		}
	}
	
	public Map<String, String[]> getParameterMap(){
		if(multipart) {
			return parameterMap;
		}else {
			return super.getParameterMap();
		}
	}
	
	public Map<String, Object> getFileItemMap(){
		if(multipart) {
			return fileItemMap;
		}else {
			return null;
		}
	}
	
	public FileItem getFileItem(String name) {
		if(multipart) {
			return (FileItem) fileItemMap.get(name);
		}else {
			return null;
		}
	}
	
	
	
}
