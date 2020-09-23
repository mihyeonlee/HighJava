package board_controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import board_handler.CommandHandler;
import board_handler.NullCommandHandler;


/**
 * 사용자 요청을 받아서 대응되는 핸들러를 호출하도록 함.
 */
public class FrontController extends HttpServlet{

	// 해당 클래스에 대한 로그를 찍는다.
	private static Logger logger = Logger.getLogger(FrontController.class);
	
	// 매핑 정보 저장 - 해당 url이 어떤 controller로 해결하는지
	private Map<String, CommandHandler> cmdHandlerMap = new HashMap<>(); // 핸들러 객체 저장용 맵
	
	// 메서드 호출시 한번만 실행
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// 파라미터 값 얻어오기
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		// 설정파일을 읽어서 대응되는 핸들러 객체를 생성하여 맵에 등록하기
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				CommandHandler handlerInstance = (CommandHandler) klass.newInstance(); // reflection? api를 통해 객체 생성
				
				cmdHandlerMap.put(command, handlerInstance);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		
		logger.info("command : " + command );
		logger.info("commanderMap : " + cmdHandlerMap );
		
		CommandHandler handler = cmdHandlerMap.get(command);
		
		if(handler == null) {
			handler = new NullCommandHandler(); // 404 에러 출력
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
				req.getRequestDispatcher(viewPage).forward(req, resp);
			}
			
		}
	}
	
}
