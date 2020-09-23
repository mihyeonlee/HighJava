package kr.or.ddit.basic;
/*
  문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
     Student클래스를 만든다.
     생성자는 학번(String), 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
  
	이 Student객체들은 List에 저장하여 관리한다.
	List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램하시오.
	(총점이 같으면 학번의 내림차순으로 정렬되도록 하시오.)
	(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T08_StudentTest {
	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("2013002","이미현",99,99,99));
		list.add(new Student("2013001","먀먀먀",50,40,10));
		list.add(new Student("2013003","퍄퍄퍄",10,90,20));
		list.add(new Student("2014002","차차차",99,85,89));
		list.add(new Student("2015002","호호호",45,75,88));
		list.add(new Student("2015001","뮤뮤뮤",45,75,88));
		
		T08_StudentTest.setRanking(list); // 순위정보 설정
		
		for(Student st : list) {
			System.out.println(st);
		}
		
		
		System.out.println("정렬 후: ");
		Collections.sort(list);
		
		for(Student st : list) {
			System.out.println(st);
		}
		
		System.out.println("총점 순");
		Collections.sort(list, new SortByTotal());
		
		for(Student st : list) {
			System.out.println(st);
		}
		
		

	}
	
	public static void setRanking(List<Student> list) {
		for(Student st : list) { //등수를 구할 자료
			int rank = 1;
			for(Student st2 : list) { //비교할 자료
				if(st.getSum()<st2.getSum()) {
					rank++;
				}
			}
			st.setRank(rank);
		}
	}
}
/**
 * 총점의 역순으로 정렬하는데 총점이 같으면 학번의 내림차순으로 정렬되도록 한다.
 * @author PC-03
 *
 */
class SortByTotal implements Comparator<Student>{

	@Override
	public int compare(Student st1, Student st2) {
		if(st1.getSum()==st2.getSum()) {
			return st1.getNum().compareTo(st2.getNum())*-1;
		}else {
			return Integer.compare(st1.getSum(), st2.getSum())*-1;
		}
	}
	
}

/**
 * 학생정보를 저장할 VO value Object
 *
 */
class Student implements Comparable<Student> {
	
	private String num;
	private String name;
	private int kNum;
	private int eNum;
	private int mNum;
	private int sum;
	private int rank;
	
	@Override
	public int compareTo(Student st) {
		return num.compareTo(st.getNum());
	}
	
	@Override
	public String toString() {
		return "Student [학번 : " + num + " 이름 : " + name + " 국어점수 : " + kNum + " 영어점수 : " + eNum + " 수학점수 : " + mNum
				+ " 총점 : " + sum + " 등수 : " + rank + "]";
	}

	public Student(String num, String name, int kNum, int eNum, int mNum) {
		super();
		this.num = num;
		this.name = name;
		this.kNum = kNum;
		this.eNum = eNum;
		this.mNum = mNum;
		this.sum = kNum+eNum+mNum;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getkNum() {
		return kNum;
	}
	public void setkNum(int kNum) {
		this.kNum = kNum;
	}
	public int geteNum() {
		return eNum;
	}
	public void seteNum(int eNum) {
		this.eNum = eNum;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public int getSum() {
		return sum;
	}
	
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		
		this.rank = rank;
	}
	
	
	
}



