package kr.or.ddit.basic;
/*
  문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
            컴퓨터의 숫자는 난수를 이용하여 구한다. 
      (스트라이크는 'S', 볼은 'B'로 출력한다.)
      
      컴퓨터의 난수가 9 5 7 일때 실행  예시)
      	숫자입력 => 3 5 9 
      	3 5 9 => 1S 0B
      	숫자입력 => 7 8 9
      	7 8 9 => 0S 2B
      	
      	...
      	숫자입력 => 9 5 7
      	9 5 7 => 3S 0B
      	
      	5번만에 맞췄군요.
    
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class T11_BaseBallTest {
	public static void main(String[] args) {
		new T11_BaseBallTest().gameStart();
	}
	
	int[] num = new int[3];  //난수가 저장될 배열
	int[] user;				 // 사용자로부터 입력받은 데이터 저장용 
	
	int strike;
	int ball; 
	
	Scanner sc = new Scanner(System.in);
	
	// 게임을 시작하는 메서드 
	public void gameStart() {
		getRndNum(); // 난수를 만드는 메서드 
		
		// 확인용 출력
		System.out.println("난수값 =>"+num[0]+" "+num[1] + " "+ num[2]);
		
		int cnt = 0;
		
		do {
			cnt++;
			intputNum(); // 사용자로부터 입력받는 처리하기 위한 메서드 
			ballCount(); // 볼카운트를 처리하는 메서드 
			
			
			
			
			
		} while (strike !=3); // 스트라이크가 3개가 나올때까지 계속 진행 
		System.out.println(cnt + "번째 만에 맞췄군요.");
		
	}
	/**
	 * 스트라이크와 볼 판정 및 결과를 출력하는 메서드 
	 */
	private void ballCount() {
		strike = 0; // 초기화
		ball = 0; // 게임이 돌때마다 초기화
		
		for(int i =0;i<num.length;i++) {
			for(int j=0;j<user.length;j++) {
				if(num[i]==user[j]) { //값이 같은지 비교
					if(i==j) { //값이 같고, 인덱스도 같은 경우 
						strike++;
					}else {
						ball++;
					}
				}
			}
		}
		System.out.println(user[0]+" " +user[1]+" "+user[2]+" ===> "+ strike + "S "+ball+"B ");
	}
	/**
	 * 사용자로부터 3개의 정수를 입력받아 배열에 저장하는 메서드
	 * (입력한 정수들은 서로 중복되지 않게 처리한다.)
	 */
	private void intputNum() {
		int n1=0, n2=0, n3=0; // 입력한 값을 저장할 변수
		
		do {
			System.out.print("중복되지 않는 정수 3개 입력 => ");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();
			
			if(n1 == n2 || n1==n3||n2==n3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다.");
			}
		} while (n1 == n2 || n1==n3||n2==n3);
		
		user = new int[] {n1,n2,n3};
		
		
	}

	/**
	 * 1~9사이의 서로다른 난수를 3개 만들어 배열에 저장하는 메서드(Set이용)
	 */
	private void getRndNum() {
		Set<Integer> bbNumSet = new HashSet<>();
		//set을 이용한 3개의 난수 만들기
		while(bbNumSet.size()<3) {
			bbNumSet.add((int)(Math.random()*9+1));
		}
		// Set의 자료를 배열에 저장하기 
		Iterator<Integer> it = bbNumSet.iterator();
		
		int i = 0; //배열의 첨자역할
		while(it.hasNext()) {
			num[i++] = it.next().intValue();
		}
		// 데이터 섞기(0번째 자료와 난수번째 자료를 교환하는 방법으로 데이터를 섞는다.)
		for(int j = 1;j<100;j++) {
			int rnd = (int)(Math.random()*num.length);
			int temp = num[0];
			num[0] = num[rnd];
			num[rnd] = temp;
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
