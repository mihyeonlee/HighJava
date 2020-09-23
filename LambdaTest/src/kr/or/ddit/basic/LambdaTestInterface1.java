package kr.or.ddit.basic;
//함수적 인터페이스: 추상메서드가 1개인 인터페이스. 명시적으로 달아놓으면 추상메서드가 2개이상일때 오류난다. 
@FunctionalInterface
public interface LambdaTestInterface1 {
	//반환값이 없고 매개변수도 없는 추상 메서드선언
	public void test();
	
}

interface LambdaTestInterface2{
	//반환값이 없고 매개변수가 있는 추상메서드 
	public void test1(int a );
}

interface LambdaTestInterface3{
	//반환값이 있고 매개변수가 있는 추상메서드 
	public int test1(int a, int b);
}



















