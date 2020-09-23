package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	
	public static void main(String[] args) {
		Thread th1 = new GameStart();
		Thread th2 = new Count5();
		th1.start();
		th2.start();
		
	}

}

/**
 * 가위바위보 게임시작 스레드
 */
class GameStart extends Thread{

	@Override
	public void run() {
		List<String> srpArr = new ArrayList<String>();
		srpArr.add("가위"); //0
		srpArr.add("바위"); //1
		srpArr.add("보");  //2
		
		int comIndex = (int)(Math.random()*2);
		String com = srpArr.get(comIndex);
		
		String user = "";
		do {
			user = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요.");
		} while (!srpArr.contains(user));
		
		int userIndex = srpArr.indexOf(user);
		
		T07_ThreadGame.inputCheck = true;
		
//	
		if(comIndex==0) {
			if(userIndex==0) {
				print(com, user);
				System.out.println("결과: 무승부입니다.");
			}else if(userIndex==1) {
				print(com, user);
				System.out.println("결과: 승리하셨습니다.");
			}else{
				print(com, user);
				System.out.println("결과: 패배하셨습니다.");
			}
		}else if(comIndex==1) {
			if(userIndex==0) {
				print(com, user);
				System.out.println("결과: 패배하셨습니다.");
			}else if(userIndex==1) {
				print(com, user);
				System.out.println("결과: 무승부입니다.");
			}else {
				print(com, user);
				System.out.println("결과: 승리하셨습니다.");
			}
		}else {
			if(userIndex==0) {
				print(com, user);
				System.out.println("결과: 승리하셨습니다.");
			}else if(userIndex==1) {
				print(com, user);
				System.out.println("결과: 패배하셨습니다.");
			}else {
				print(com, user);
				System.out.println("결과: 무승부입니다.");
			}
		}
	}
	
	/**
	 * 컴퓨터와 사용자의 결과출력메서드
	 * @param com
	 * @param user
	 */
	static void print(String com, String user) {
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터: "+com);
		System.out.println("사용자: "+user);
	}

}

/**
 * 5초를 세는 스레드
 */
class Count5 extends Thread{
	
	@Override
	public void run() {
		for(int i=5 ; i>=1 ; i--) {
			if(T07_ThreadGame.inputCheck) {
				return; // 입력이 true이면 되돌려보낸다.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);  //1초 기다리는 메서드
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("입력시간 5초가 경과해 패배하셨습니다.");
		System.exit(0);
	
	}
	
}




