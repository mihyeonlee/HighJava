package kr.or.ddit.basic;

public class TestClass {
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	
	public static final int LION = 0;
	public static final int TIGER = 1;
	
	
	public static void main(String[] args) {
		// 이런경우 오류도 나지 않아서 잡기가 힘들다 그래서 나온게 enum
		if(LION==0) {
			System.out.println("ZERO 입니다.");
		}
	}

}
