package kr.or.ddit.basic;

class Util2{
	// Number 클래스를 상속받은 타입은 다 가능하다. 물론 넘버도 포함해서. (Number + extends Number)
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

/**
 * 제한된 타입 파라미터(Bounded Type Parameter) 예제
 */
public class T04_GenericMethodTest {
	public static void main(String[] args) {
		
		int result1 = Util2.compare(10, 20); //AutoBoxing
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3); //AutoBoxing
		System.out.println(result2);
		
//		Util2.compare("c", "java"); //에러발생 String은 Number를 상속받지 않았기 때문에.
		
	}
}
