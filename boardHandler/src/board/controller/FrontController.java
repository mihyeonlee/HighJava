package board.controller;

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

import org.apache.log4j.Logger;

import board.handler.BoardHandler;
import board.handler.NullHandler;

/** 
 * 사용자 요청을 받아서 대응되는 핸들러를 호출하도록 한다.
 */
public class FrontController extends HttpServlet{
	private static Logger logger = Logger.getLogger(FrontController.class);
	
	// 핸들러 객체 저장용 맵을 만든다.
	private Map<String, BoardHandler> cmdHanderMap = new HashMap<>();
	
	// init()을 호출할때 ServletConfig 자동생성, 서블릿당 하나가 생성된다.
	// web.xml에서 <init-param>을 통해 서블릿초기화시 넘겨받을 값을 설정할 수 있다. <param-name>,<param-value>설정가능
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		//getInitParameter(String paramName) : web.xml에서 지정한 param-name값을 넘겨주면 param-value를 반환한다.
		//configFilePath = /WEB-INF/handler.properties 가 된다.
		String configFilePath = config.getInitParameter("handler-config");
		
		//properties파일을 읽기위한 properties객체생성
		Properties handlerProp = new Properties();
		
		//톰캣이 컴퓨터내에 존재하는 경로를 찾을 수 있도록 주어진 이클립스주소의 절대경로(컴퓨터저장된경로)를 반환한다.
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		
		try {
			
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// keySet()로 handlerProp의 키값을 key에 담고 그걸 command에 담는다.
		for(Object key : handlerProp.keySet()) {
			String command = (String)key;
			
			Class<?> klass;
			try {
				// klass = handlerProp의 value값이 담긴다.
				klass = Class.forName(handlerProp.getProperty(command));
				
				// value에는 handler 클래스의 주소가 담겨있고 객체를 생성한다. 
				BoardHandler handlerInstance = (BoardHandler) klass.newInstance();
				
				cmdHanderMap.put(command, handlerInstance);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
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
		
		// url을 가져온다.
		String command = req.getRequestURI();
		
		// 가져온 url에 절대경로가 존재하면 절대경로길이만큼 잘라 반환.
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		
		// 로그찍기
		logger.info("command : " + command);
		logger.info("cmdHanderMap : " + cmdHanderMap);
		
		// 핸들러 처리
		BoardHandler handler = cmdHanderMap.get(command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";
		
		try {
			// 핸들러객체처리
			viewPage = handler.process(req, resp);
		} catch (Throwable e) {
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
