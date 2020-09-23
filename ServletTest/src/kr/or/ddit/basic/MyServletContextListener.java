package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener{
	
	// ServletContextListener 에있는 메서드들
	/**
	 * 생성자
	 */
	public MyServletContextListener() {
		System.out.println("[MyServletContextListener] 생성자 호출됨");
	}
	
	/**
	 * 서블릿 컨텍스트가 제거 되었을때 호출됨
	 * 제거됐을때 하고싶은 작업을 안에 구현하면 된다.
	 * @param sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] 서블릿 컨텍스트가 제거 되었음");
		
	}
	
	/**
	 * 서블릿 컨텍스트가 초기화 되었을때 호출됨
	 * 하고싶은 작업을 안에 구현하면 된다.
	 * @param sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] 서블릿 컨텍스트가 초기화 되었음");
	}

	
	//===================================================================================
	// ServletContextAttributeListener 안에 있는 메서드들
	
	/**
	 * 서블릿 컨텍스트에 속성이 추가되었을 때 호출됨
	 * 하고싶은 작업을 안에 구현하면 된다.
	 * @param scae
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener]"
				+ " 서블릿 컨텍스트에 속성이 추가 되었음 => " + scae.getName());
	}

	/**
	 * 서블릿 컨텍스트에 속성이 제거 되었을 때 호출됨
	 * 하고싶은 작업을 안에 구현하면 된다.
	 * @param scae
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener]"
				+ " 서블릿 컨텍스트에 속성이 제거 되었음 => " + scae.getName());
	}

	/**
	 * 서블릿 컨텍스트에 속성이 변경되었을 때 호출됨
	 * 하고싶은 작업을 안에 구현하면 된다.
	 * @param scae
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener]"
				+ " 서블릿 컨텍스트에 속성이 변경 되었음 => " + scae.getName());
	}
	
	
}
