package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BaseBallGame {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		BaseBallGame bg = new BaseBallGame();
		bg.gameStart();
		
		
	}
	
	private List<Integer> gameNum() {
		Set<Integer> gNum = new HashSet<>();
		while(gNum.size()<3) {
			int n = (int)(Math.random()*9+1);
			gNum.add(n);
		}
		List<Integer> gList = new ArrayList<Integer>(gNum);
		System.out.println("섞기전 게임번호: "+gList);
		Collections.shuffle(gList);
		System.out.println("섞은 후 게임번호: "+gList);
		return gList;
	}
		
		
	private List<Integer> pNum() {
		List<Integer> pList = new ArrayList<>();
		System.out.print("숫자입력 : ");
		while(true) {
			int a = 0 ,b =0,c= 0;  
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			if(a<1 && a>9) {
				System.out.println("1~9사이의 숫자만 입력해주세요.");
				continue;
			}else if(b<1 && b>9) {
				System.out.println("1~9사이의 숫자만 입력해주세요.");
				continue;
			}else if(c<1 && c>9) {
				System.out.println("1~9사이의 숫자만 입력해주세요.");
				continue;
			}
			if(a==b) {
				System.out.println("같은숫자는 입력할 수 없습니다.");
				continue;
			}else if(a==c){
				System.out.println("같은숫자는 입력할 수 없습니다.");
				continue;
			}else if(b==c) {
				System.out.println("같은 숫자는 입력할 수 없습니다.");
				continue;
			}
			pList.add(a);
			pList.add(b);
			pList.add(c);
			System.out.println(pList);
			break;
		}
		return pList;
	}
	
	
	
	private void gameStart() {
		List<Integer> gList = gameNum();
		int strike = 0;
		int ball = 0;
		int count = 0;
		do {
			List<Integer> pList = pNum();
			strike =0;
			ball=0;
			for (int i = 0; i < gList.size(); i++) {
				
				for (int j = 0; j < pList.size(); j++) {
					if(gList.get(i)==pList.get(j)) {
						if(i==j) {
							strike++;
						}else {
							ball++;
						}
					}
				}
			}
			count++;
			System.out.println(strike+"S "+ball+"B ");
		} while (strike!=3);
		System.out.println(count+"번째에 맞추셨습니다.");
		
	}  
	
	
	
		
		
		
	
		
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
