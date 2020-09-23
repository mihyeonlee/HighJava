package kr.or.ddit.basic;
/*
	제너릭 메서드<T, R> R method(T t)    R=> 리턴타입  (T t)=>파라미터
	
	파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드 
	** 선언방법 : 리턴타입 앞에 <>기호를 추가하고 타입 파라미터를 기술 후 사용한다. 제너릭메서드는 꼭 리턴타입 앞에 적는다. 
 */



// Pair(얘도 제너릭클래스)객체 두개를 받음 리턴타입은 boolean  제너릭은 선언하는시점에 한다. 
// 받는 객체가 제너릭클래스이기 때문에 메서드도 제너릭타입으로 선언한다.
class Util{  // 그럼 R = boolean이 됨.
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare =  p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
		// 두개의 결과를 모두 만족할때 true리턴
	}
	
}

/**
 * 멀티타입<K, V>을 가지는 제너릭 클래스 => 얘를 사용하는 메서드를 선언할때는 그 메서드도 제너릭해질 수 밖에 없다. 타입은 사용할때 지정하기 때문에 
 * @param <K> T를 써도 상관없지만 얘가 key라는 의미를 더 주기위해서 K로 선언
 * @param <V>
 */
class Pair<K, V>{
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	//키와 값을 출력하는 메서드 //이 메서드 내에 있는 K, V는 지역변수 때문에 클래스제너릭타입과 다른애다. 같은 K,V가 아님. 의미가다름. 이렇게 혼동되게 선언하지 말자.
	//지역변수로 요 메서드 내에서만 의미가 있다. 
	public <K, V> void displayAll(K key, V value) {
		System.out.println(key.toString()+" : "+ value.toString());
	}
	
}

public class T03_GenericMethodTest {
	public static void main(String[] args) {
		// 사용할 때 제너릭클래스에 타입을 Integer, String으로 타입지정해줌. 
		Pair<Integer, String>p1 = new Pair<Integer, String>(1,"홍길동");
		Pair<Integer, String>p2 = new Pair<Integer, String>(1,"홍길동");
		
		//K=>Integer V=>String
		boolean result1 = Util.<Integer,String>compare(p1, p2);
		//구체적으로 타입을 명시적으로 지정(생략가능) p1, p2만 봐도 타입을 알 수 있다. 그래서 생략가능
//		boolean result1 = Util.compare(p1, p2);
		
		if (result1) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체아님.");
		}
		
		//제너릭 타입변경
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
//		Pair<String, String> p3 = new Pair<String, String>(1, "홍길동"); //값의 타입을 달리넣으면 오류난다. 타입안전(Type Safety)하기 때문에 
		Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");
		
		boolean result2 = Util.compare(p3, p4);
		
		if (result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체아님.");
		}
		
		//키와 값을 출력하는 메서드 테스트  //
		p1.<String, Integer>displayAll("키", 1234);
		p1.displayAll("키", 1234);
		p1.displayAll("키", "123");
		
		
	}
}
