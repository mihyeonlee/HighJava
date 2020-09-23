package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSort {
	/*
	  문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	     Student클래스를 만든다.
	     생성자는 학번(String), 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	  
		이 Student객체들은 List에 저장하여 관리한다.
		List에 저장된 데이터들을  학번의 오름차순 으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램하시오.
		(총점이 같으면 학번의 내림차순으로 정렬되도록 하시오.)
		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
	 */
	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student("2013001", "이미자", 99,80,30));
		list.add(new Student("2013002", "구기자", 55,50,30));
		list.add(new Student("2013003", "사귀자", 66,55,22));
		list.add(new Student("2013004", "사미자", 87,80,10));
		list.add(new Student("2013005", "오미자", 87,80,10));
		
		System.out.println("정렬전");
		for(Student st : list) {
			System.out.println(st);
		}
		
		System.out.println("순위부여");
		
		ListSort.setRank(list);
		for(Student st : list) {
			System.out.println(st);
		}
		System.out.println("총점순으로 정렬");
		Collections.sort(list, new DescTotal());
		for(Student st:list) {
			System.out.println(st);
		}
		
		
	}
	static void setRank(List<Student> list) {
		for(Student st1 : list) {
			int rank = 1;
			for(Student st2 : list) {
				if(st1.getTotal()<st2.getTotal()) {
					rank++;
				}
			}
			st1.setRank(rank);
		}
	}
		
		
		
	}
	
	
class DescTotal implements Comparator<Student>{
	
	@Override
	public int compare(Student st1, Student st2) {
		if(st1.getTotal()==st2.getTotal()) {
			return st1.getNum().compareTo(st2.getNum())*-1;
		}else {
			return Integer.compare(st1.getTotal(), st2.getTotal())*-1;
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}


class Student implements Comparable<Student>{

	String num;
	String name;
	int ks;
	int es;
	int ms;
	int total;
	int rank;
	
	
	@Override
	public String toString() {
		return "Student [학번: " + num + "  이름: " + name + "  국어: " + ks + "\t영어: " + es + "\t수학: " + ms + "\t총점: "
				+ total + "  순위: " + rank + "]";
	}


	@Override
	public int compareTo(Student st) {
		return num.compareTo(st.getNum());
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


	public int getKs() {
		return ks;
	}


	public void setKs(int ks) {
		this.ks = ks;
	}


	public int getEs() {
		return es;
	}


	public void setEs(int es) {
		this.es = es;
	}


	public int getMs() {
		return ms;
	}


	public void setMs(int ms) {
		this.ms = ms;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public Student(String num, String name, int ks, int es, int ms) {
		super();
		this.num = num;
		this.name = name;
		this.ks = ks;
		this.es = es;
		this.ms = ms;
		this.total = ks+es+ms;
	}
	
	
	
	
	
}