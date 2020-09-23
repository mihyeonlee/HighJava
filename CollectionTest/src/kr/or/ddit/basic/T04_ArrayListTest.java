package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class T04_ArrayListTest {
	public static void main(String[] args) {
		
	//문제1) 5명의 별명을 입력하여 ArrayList에 저장하고 별명의 길이가 제일 긴 별명을 출력하시오. 
	//단. 각 별명의 길이는 모두 다르게 입력한다.
	
	//문제2) 문제1에서 별명의 길이가 같은 것을 여러개 입력했을 때에도 처리하시오.
	Scanner sc = new Scanner(System.in);
	List<String> nmList = new ArrayList<>();
	String Maxnm = nmList.get(0);
	
	for(int i = 0; i<5; i++) {
		System.out.println("별명을 입력해주세요.");
		String nm = sc.next();
		for (int j = 0; j < nmList.size(); j++) {
			if(nm.length()==nmList.get(j).length()) {
				System.out.println("같은 길이의 별명이 있습니다. 길이를 다르게 다시 입력해주세요.");
				continue;
			}else {
				
				
				nmList.add(nm);
				break;
			}
		
			
		}
	}
		
	int maxLen = nmList.get(0).length();
	for (int i = 0; i < nmList.size(); i++) {
		if(maxLen < nmList.get(i).length() ) {
			maxLen = nmList.get(i).length();
		}
	}
	
	System.out.println("제일 긴 별명들");
		for (int i = 0; i < nmList.size(); i++) {
			if(maxLen==nmList.get(i).length()) {
				System.out.println(nmList.get(i));
			}
			
		}
		
		
		
	}
	
	
	
	
	
	
	
}
