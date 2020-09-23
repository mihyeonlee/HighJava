package kr.or.ddit.basic;
/**
 * 애너테이션 사용 예제
 */
public class Service {
	
	@PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation("%") //원래는 value="%" 타입요소에 value만 그리고 value한개만 있을때만 생략가능하다. 컴파일러가 자동인식.
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=25) // 타입요소에 2개가 적혀있기때문에 value생략불가 
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
	
	
	
	
}
