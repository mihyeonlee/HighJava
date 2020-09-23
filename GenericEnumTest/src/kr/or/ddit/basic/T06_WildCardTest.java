package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 제너릭 클래스를 이용한 객체생성 예제
 */
public class T06_WildCardTest {
	public static void main(String[] args) {
		// FruitBox2<? extends Fruit> fruitBox1 = new FruitBox2<Fruit>();
		FruitBox2<?> fruitBox1 = new FruitBox2();  // 프룻박스1의 타입은 제너릭객체를 와일드카드로 가진 프룻박스2타입이다. 하지만 프룻박스2에 제한된타입파라미터가 걸려있어 모든타입이 가능한건 아니다.
		FruitBox2<?> fruitBox2 = new FruitBox2(); //위와 동일 프룻박스2의 제너릭타입으로 올수 있는 타입은 다 올 수 잇다.
		
		//FruitBox2<?>는 FruitBox2<? extends Fruit>를 의미함. 그래서 Object는 올 수 없다.
//		FruitBox2<?> fruitBox3 = new FruitBox2<Object>();
		
		// 두 타입(Object, Fruit)이 일치하지 않음
//		FruitBox2<Object> fruitBox4 = new FruitBox2<Fruit>();
		
		FruitBox2<?> fruitBox5 = new FruitBox2<Fruit>();
		
		FruitBox2<? extends Fruit> fruitBox6 = new FruitBox2<Apple>();
		
		// new 연산자는 타입이 명확해야 객체생성을 할 수 있다.(와일드카드 사용불가) 객체생성할때만은 와일드카드도 사용할 수 없다.
//		FruitBox2<? extends Object> fruitBox7 = new FruitBox2<? extends Object>();
		
	}
}
/**
 * 과일상자2
 * (제한된 타입 파라미터를 이용한 제너릭 클래스 예제)
 * @param <T>
 */
class FruitBox2 <T extends Fruit>{
	
	List<T> itemList = new ArrayList<T>();

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
	
	public void addItem(T item) {
		this.itemList.add(item);
	}
	
}


































