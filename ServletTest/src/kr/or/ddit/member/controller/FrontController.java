package kr.or.ddit.member.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import kr.or.ddit.member.handler.CommandHandler;
import kr.or.ddit.member.handler.NullCommandHandler;
import kr.or.ddit.util.FileUploadRequestWrapper;

/**
 * 사용자 요청을 받아서 대응되는 핸들러를 호출하도록 함.
 * @author PC-03
 *
 */
public class FrontController extends HttpServlet{
	private static Logger logger = Logger.getLogger(FrontController.class);
	
	// 핸들러 객체 저장용 맵 
	private Map<String, CommandHandler> cmdHanderMap = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// config에는 web.xml에 매핑되어 있는 정보가 담긴다.
		// 해당 handler의 객체를 생성한다.
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		 
		// 설정파일을 읽어서 대응되는 핸들러 객체를 생성하여 맵에 등록하기 
		// 톰캣이 구동하려면 컴퓨터에 저장되어있는 주소를 알아야함. 그래서 getReatPath가져옴
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		// 
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			
			// 제너릭? 와일드카드  klass는 class를 변수명으로 사용할 수없기 때문에 보통이렇게 사용함
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				CommandHandler handlerInstance = (CommandHandler) klass.newInstance();
				
				cmdHanderMap.put(command, handlerInstance);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FileUploadRequestWrapper requestWrapper;
		try {
			requestWrapper = new FileUploadRequestWrapper(req);
			process(requestWrapper,resp);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath()) ==0) {
			command = command.substring(req.getContextPath().length());
		}
		
		logger.info("command : " + command);
		logger.info("cmdHanderMap : " + cmdHanderMap);
		
		CommandHandler handler = cmdHanderMap.get(command);
		
		if(handler == null) {
			handler = new NullCommandHandler();
		}
		
		String viewPage = "";
		
		try {
			viewPage = handler.process(req, resp); // 커맨드 객체 처리 
		}catch (Throwable e) {
			throw new ServletException();
		}
		
		logger.info("viewPage : " + viewPage);
		
		if(viewPage != null) {
			if(viewPage.startsWith("redirect:")) {
				resp.sendRedirect(viewPage.substring(9));
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
		
		
		
		
	}
	

}
