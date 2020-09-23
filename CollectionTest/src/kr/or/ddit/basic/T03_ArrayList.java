package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T03_ArrayList {
	public static void main(String[] args) {
		//문제) 5명의 사람 이름을 입력하여 ArrayList에 저장하고, 이중에 '김'씨 성의 이름을 출력하시오.
		// 단. 입력은 Scanner를 이용하여 입력받는다.
		Scanner sc = new Scanner(System.in);
		List<String> nList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			System.out.println("이름을 입력해주세요.");
			String name = sc.next();
			nList.add(name);
		}
		for (int i = 0; i < nList.size(); i++) {
			System.out.println("=== 김씨 성을 가진사람들 ===");
//			if(nList.get(i).contains("김")) {
//				System.out.println(nList.get(i));
//			}
			if(nList.get(i).indexOf("김")==0) {
				System.out.println(nList);
			}
			
			if(nList.get(i).startsWith("김")) {
				
			}
			
			if(nList.get(i).charAt(0)=='김') {
				
			}
		}
		
		
		
		
		
		
	}

}
