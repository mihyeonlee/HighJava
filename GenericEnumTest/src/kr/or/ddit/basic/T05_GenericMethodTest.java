package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 	와일드 카드 예제(Wild card) 예제
 	
 	
 	<? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능하다.  <? extends Number> 넘버를 상속받은 애들+넘버포함 
 	<? super T>   => 와일드 카드의 하한 제한. T와 그 조상들만 가능하다.  
 	<?>           => 모든 타입 가능 <? extends Object> 와 동일. 
 	
 	** 와일드 카드와 제한된 타입 파라미터 비교:
 	1. 동일한 파라미터 타입으로 강제하고 싶은 경우.
 	  (타입 파라미터가 한개만 사용될 경우에는 둘 중 아무거나 사용해도 동일함.)
 	  
 	ex) public static <T extends Number> void copy(List<T> dest, List<T> src)  // 
 	    => 메서드의 파라미터타입을 동일한 타입으로 강제함. List안에 두 타입이 동일해야함. 인티저면 둘다 인티저 사용할땐 타입무조건적어야하고.
 	    public static <T extends Number> void copy(List<T> dest, List<S> src)  //
 	    => 와일드카드를 사용한  것과 동일해짐. 
 	    
 	    // 사용하는 시점에 써서 선언할때 제한된 타입파라미터를 쓴 효과를 낼 수 있다. 
 	      public static void copy(List<? extends Number> dest, 
 	      						  List<? extends Number> src)
 	    => 동일 타입으로 강제하지 않음. dest랑 src의 타입이 넘버만 상속했기만 하면된다.
 	    
    2. Type parameters는 하한 제한만 가능하다. (와일드 카드는 상한, 하한가능)
    ex) public void print(List<? super Integer> list) //Ok
    	public <T super Integer> void print(List<T> list) //에러 이런문법은 없다. 제한된 타입파라미터는 상속만 올 수 있다.
  
 */
public class T05_GenericMethodTest {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>(); //과일상자
		FruitBox<Apple> appleBox = new FruitBox<>(); //사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox); //과일상자인 경우에는 문제없음
		Juicer.makeJuice(appleBox);
		
	}
}

class Juicer{
//	static void makeJuice(FruitBox<Fruit>box) {
//	제한된 타입 파라미터 문법으로 해결
//	static <T extends Fruit> void makeJuice(FruitBox<T>box) {
	// 와일드카드 문법으로 해결
	static void makeJuice(FruitBox<? extends Fruit>box) {
//		static <T> void makeJuice(FruitBox<T> box) {
//	static void makeJuice(FruitBox<?> box) {  //밑에서 타입사용하는게 있기 때문에 사용이 없는경우에는 이렇게 쓸 수도있다. 
	
		String fruitListStr = ""; //과일목록
		
		int cnt = 0;
		for(Fruit f: box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		System.out.println(fruitListStr + "=> 쥬스완성!");
	}
}
/**
 *  과일
 */
class Fruit{
	private String name; //과일이름

	public Fruit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일("+ name + ")";
	}
}

/**
 * 사과
 */
class Apple extends Fruit {

	public Apple() {
		super("사과");
	}
}
/**
 * 포도
 */
class Grape extends Fruit{

	public Grape() {
		super("포도");
	}
}
/** 
 * 과일상자
 * @param <T>
 */
class FruitBox <T>{
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
	


}






















