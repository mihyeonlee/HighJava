package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 	Java Reflection에 대하여
 	
 	1. 리플렉션은 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
 	2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
 	3. java.lang.Class의 주요 메서드	
 	  - getName(), getSuperclass(), getInterface(), getModifiers()//접근제어자 등
 	4. java.lang.reflect 패키지의 주요 클래스 
 	  - Field, Method, Constructor, Modifier 등.
 */
public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(PrintAnnotation.id);//상수값 출력 //상수접근:클래스명.상수변수명
		
		// reflection 기능을 이용한 메서드 실행하기 
		// 선언된 메서드 목록 가져오기 
		Method[] declaredMethods = Service.class.getDeclaredMethods(); //클래스는 객체생성하려고 만드는데 그 클래스정보를 가져오기위한 class가 잇음.
		
		for(Method m : declaredMethods) {  // 메서드리스트만 가져오지 순서가 보장되진 않음 때문에 출력시마다 순서가 바뀔 수 있다.
			System.out.println(m.getName()); //메서드명 출력
			
			PrintAnnotation pa = m.getDeclaredAnnotation(PrintAnnotation.class);
			
			for(int i=0;i<pa.count();i++) { //count값만큼
				System.out.print(pa.value()); //value값 출력
			}
			System.out.println(); //줄바꿈 처리
			
			//방법1) new 이용한 객체 생성
			// m.invoke(new Service());
			
			//방법2) reflection이용한 객체 생성
			Class<Service> clazz = Service.class;
			try {
				Service service = (Service) clazz.newInstance();
				m.invoke(service); //()파라미터로 실행할 객체를 넣어주면 메서드를 실행한다. invoke
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
