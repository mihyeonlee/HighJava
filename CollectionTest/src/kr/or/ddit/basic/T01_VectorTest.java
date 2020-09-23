package kr.or.ddit.basic;

import java.util.List;
import java.util.Vector;

public class T01_VectorTest {
	public static void main(String[] args) {
		// Vector => List계열 클래스
		
		List v1 = new Vector();
		
		System.out.println("처음 크기 : "+ v1.size());
		
		// Vector는 add()메서드를 이용하여 데이터를 추가할 수있다. (안에들어가는건 다 객체)기본형을 넣어도 객체로변환됨
		v1.add("aaa"); 
		v1.add(111);
		v1.add(new Integer(123)); // 기본형만 사용한것도 컴파일러가 자동으로 래퍼클래스사용해 오토박싱
		v1.add('a');
		v1.add(true); 
		v1.add(3.14);
		
		System.out.println("현재 크기 : " + v1.size());
		
		// Vector는 addElement()메서드를 이용하여 추가할 수도 잇는데 
		// 이 메서드는 기본적으로 add()메서드와 같은 기능을 수행함.
		// v1.addElement("ccc"); 오류나는 이유는 list타입이기때문에 vector에서만 쓰이는 메서드이기때문에 
		
		System.out.println("v1 => "+ v1.toString());
		
		//add(index, 데이터) => 벡터의 index번째에 '데이터'를 끼워넣는다. index는 0부터 시작한다.
		v1.add(1,"kkk");
		System.out.println("v1 => "+ v1.toString());
		//set(index,데이터) => 벡터의 index번째의 값을 주어진 '데이터'로 덮어쓴다.
		//				  => 반환값 : 원래의 데이터
		String temp = (String) v1.set(0, "zzz");
		System.out.println("set명령 후 v1 => "+v1);
		System.out.println("원래의 데이터: "+temp);
		
		//remove(index) => 벡터의 index번째 자료를 삭제한다.
		//	=> 자료가 삭제되면 index번째 다음번째 이후의 데이터들이 앞으로 자동으로 당겨져서 채워진다.
		//	=> 반환값: 삭제된 데이터
		
		// remove(삭제할 데이터) => '삭제할 데이터'를 찾아 삭제한다.
		//	=> 만약 '삭제할 데이터'가 여러개이면 앞에서부터 삭제한다.
		//	=> 삭제할 데이터가 '정수형'이거나 'char형' 일 경우에는 삭제할 데이터를 객체로 변환해서 사용해야 한다. (아니면 인덱스로 인식되기 때문)
		v1.remove(0);
		System.out.println("0삭제후 : "+v1);
		System.out.println();
		
		temp = (String)v1.remove(0);
		System.out.println("삭제된 자료: "+temp);
		System.out.println("삭제 후: "+ v1);
		System.out.println();
		
		v1.add(123);
		v1.remove(true);
		System.out.println("삭제 후: "+ v1);
		System.out.println();
		
		v1.remove(new Integer(123));
		System.out.println("123삭제후: "+v1);
		System.out.println();
		
		v1.remove(new Character('a'));
		System.out.println("a삭제후 : "+v1);
		System.out.println();
		
		v1.remove(3.14);
		System.out.println("삭제 후: "+v1);
		System.out.println();
		
		//get(index) => index번째 자료를 반환한다.
		int data = (int)v1.get(0);
		System.out.println("0번째 자료: "+data);
		
		System.out.println("---------------------------------------");
		
		/*
		 제너릭 타입(generic type)
		  => Collection객체를 선언할때 <>안에 그 Collection이 저장할 데이터 타입을 정해주는 것을 말한다.
		  => 이런식으로 선언하게 되면 그 데이터 타입 이외의 데이터를 저장할 수 없다. 타입이정확한코드를 가능하게한다.
		  	(제너릭 타입으로 선언할 수 있는 데이터타입은 클래스이어야한다.)
		  	(예: int -> Integer,boolean->Boolean, char->Character)
		  => 제너릭 타입으로 선언하게 되면 데이터를 꺼내올때 별도의 형변환이 필요없다. 
		  
		 */
		//String만 저장가능
		Vector<String> v2 = new Vector<String>();
		
		//Integer만 저장가능
		Vector<Integer> v3 = new Vector<>(); //뒤에선 안써도 타입이 자동저장되는걸 다이아몬드문법이라한다. 
		v3.add(1000);
		
		v2.add("안녕하세요");
		//v2.add(123); //오류 : 다른종류의 데이터를 추가할 수 없다.
		
		String temp2 = v2.get(0);
		System.out.println("temp2 => "+temp2);
		
		//==============================================================
		System.out.println("====클리어시작====");
		v2.clear(); //벡터의 모든데이터를 삭제한다.
		System.out.println("v2의 sizw="+v2.size());
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("삭제 되기 전 : "+ v2);
		
		//removeAll(Collection객체) => 벡터의 데이터중에서 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다. 그래서 v2에있는것도 삭제됨.
		v2.removeAll(v4);
		System.out.println("삭제된 후 v2 => "+v2);
		System.out.println("--------------------------");
		
		v2.clear();
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		//벡터의 데이터들을 순서대로 모두 가져와서 사용하고 싶으면 반복문을 사용하면 된다. (주로 for문 사용)
		for (int i = 0; i < v2.size(); i++) {
			System.out.println(i+"번째 자료 : "+v2.get(i));
		}
		
		System.out.println("=========================================");
		
		/*
		 향상된 for문  <for each문>
		 형식) for(자료형명 변수명: 배열변수나 Collection계열변수){
		 	   처리할 내용들;
		 	  ...
		 	 }
		=> 주어진 '배열변수나Collection계열 변수'의 데이터 개수만큼 반복한다.
		=> 반복때마다 '배열변수나 Collection계열 변수'의 데이터를 하나씩 꺼내와 '변수명'에 저장한 후 '처리할 내용들'을 처리한다.
		 */
		for(String s : v2) {
			System.out.println(s);
		}
		//index값이 없기때문에 그걸 이용한걸 쓰기 힘듦
		System.out.println("-----------------------------------");
		
		// 만약 제너릭을 사용하지 않은 Collection을 향상된 for문으로 처리할 경우에는 Object형으로 처리한다.
		for(Object obj : v1) {
			System.out.println(obj);
		}
		
		System.out.println("-----------벡터 사이즈 및 용량 메서드 예제------------");
		
		Vector v = new Vector(5); // 용량이 5인(사이즈는0) 벡터생성
		v.add("홍길동");
		v.add("박찬호");
		v.add("3");
		print(v);
		
		v.trimToSize();// 벡터의 용량을 현재 벡터 사이즈로 줄인다.
		System.out.println("=== After trimToSize() ===");
		print(v);
		
		v.ensureCapacity(5);// 현재 용량이 최소용량보다 작으면...    용량크기설정
							//신규용량 = 현재용량(3) * 2, 그래도 작다면, 
							//신규용량 = 최소용량으로 설정된다.
		System.out.println("=== After ensureCapacity(5) ===");
		print(v);
		
		v.setSize(7);// 현재 용량이 설정사이즈(7)보다 작으면 신규용량 = 현재용량(6)*2,  사이즈와 용량이 다른걸 기억하자 
					 // 그래도 작다면, 신규용량 = 설정사이즈로 설정된다.
		System.out.println("=== After setSize(7) ===");
		print(v);
		
		v.clear(); // 사이즈만 0이되고 용량은 그대로 유지된다. 
		System.out.println("=== After clear() ===");
		print(v);
 		
	}
	
	private static void print(Vector v) {
		System.out.println(v);
		System.out.println("size : "+v.size());
		System.out.println("capacity : "+ v.capacity());
	}
	
	
	
	
	
	

}
