package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparable and Comparator 구현 예제
 *
 */
public class T07_ListSortTest {
	public static void main(String[] args) {
		List<Member> list = new ArrayList<Member>();
		list.add(new Member(1, "홍길동", "010-1111-1111"));
		list.add(new Member(5, "변학도", "010-2222-2222"));
		list.add(new Member(9, "성춘향", "010-3333-3333"));
		list.add(new Member(3, "이순신", "010-4444-4444"));
		list.add(new Member(2, "강감찬", "010-5555-5555"));
		list.add(new Member(6, "일지매", "010-6666-6666"));
		
		System.out.println("정렬전...");
		for(Member mem : list) {
			System.out.println(mem);
		}
		System.out.println("====================================");
		
		Collections.sort(list);
		
		System.out.println("이름의 오름차순 정렬 후...");
		for(Member mem: list) {
			System.out.println(mem);
			
		}
		System.out.println("====================================");
		
		Collections.sort(list,new SortNumDesc());
		
		System.out.println("번호의 내림차순으로 정렬 후...");
		for(Member mem: list) {
			System.out.println(mem);
			
		}
		System.out.println("====================================");
	}
}

// 정렬 기준의 외부 선언을 위해서는 Comparator 인터페이스를 구현하면 된다.
// (Member 의 번호(num)의 내림차순으로 정렬하기)
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum()>mem2.getNum()) {
//			return -1;  //내림차순은 첫번째값이 더 크면 -1반환하게해주고 //오름차순은 반대로 1
//		}else if(mem1.getNum()==mem2.getNum()) {
//			return 0;  //같은건 똑같이 0
//		}else {
//			return 1;  //내림차순은 첫번째값이 작으면 1반환하게해주면된다.//오름차순은 반대로-1
//		}
		
//		Wrapper클래스에서 제공하는 메서드를 이용하는 방법1
		// 내림 차순일 경우에는 -1을 곱해준다.
//		return Integer.compare(mem1.getNum(), mem2.getNum())*-1;
//		Wrapper클래스에서 제공하는 메서드를 이용하는 방법2
		return new Integer(mem1.getNum()).compareTo(mem2.getNum())*-1;
	}
	
}


/*
   회원 정보를 저장할 클래스
   회원이름을 기준으로 오름차순 정렬이 될 수 있도록 구현하기. 
 */
class Member implements Comparable<Member>{
	private int num; //번호
	private String name; //이름
	private String tel; //전화번호
	
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	// 이름을 기준으로 오름차순정렬이 되도록 설정한다.
	@Override
	public int compareTo(Member mem) {
		
		return getName().compareTo(mem.getName());
	}
	
	
	
}
