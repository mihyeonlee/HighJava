package kr.or.ddit.basic;

public class T01_ThreadTest {
	public static void main(String[] args) {
		//메인쓰레드 자동생성 위에서부터 코드를 읽어감 
		
		//싱글 쓰레드 프로그램
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
		
		
		
		
		
	}//main메서드가 끝나면 main스레드도 종료
}
