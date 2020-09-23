package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener{

	public MyServletRequestListener() {
		System.out.println("[MyServletRequestListener] 생성자 호출.");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] ServletRequestEvent() 호출.");
		
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] ServletRequestEvent() 호출.");
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] 속성값 추가됨 => " + srae.getName() );
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] 속성값 제거됨 => " + srae.getName());
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] 속성값 변경됨 => " + srae.getName());
	}

}
