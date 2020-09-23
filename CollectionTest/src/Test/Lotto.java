package Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Lotto {
	static Scanner sc = new Scanner(System.in);
	
	
	
	
	public static void main(String[] args) {
		
		Lotto lo = new Lotto();
		lo.lottoMenu();
		
	}
	/**
	 * 로또 메뉴 선택하는 메서드
	 */
	private void lottoMenu() {
		
		while(true) {
			boolean out = false;
			int input = 0;
			System.out.println("===============================");
			System.out.println("  Lotto 프로그램 ");
			System.out.println("-----------------");
			System.out.println("  1. Lotto 구입");
			System.out.println("  2. 프로그램 종료");
			System.out.println("===============================");
			System.out.print("메뉴선택 :");
			
			try {
				input = sc.nextInt();
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				sc = new Scanner(System.in);
				continue;
			} finally {
				sc = new Scanner(System.in);
			}
			
			switch (input) {
			case 1:
				buyLotto();
				break;
			case 2:
				out = true;
				break;
				
			default:
				System.out.println("1,2번 중에 입력해주세요.");
				break;
			}
			if(out) {
				System.out.println("감사합니다");
				break;
			}
		}
	}
	
	/**
	 * 로또구매하는 메서드 
	 */
	private void buyLotto() {
		int money = 0;
		while(true) {
			System.out.println("\nLotto 구입 시작");
			System.out.println("\n(천원에 로또번호 하나입니다.)");
			System.out.print("금액 입력 : ");
			try {
				money = sc.nextInt();
				if(money<1000) {
					System.out.println("1장에 1000원입니다. 구입하실 수 없습니다.");
					return;
				}
			} catch (Exception e) {
				System.out.println("숫자만 정확히 입력해주세요.");
				sc = new Scanner(System.in);
				continue;
			}finally {
				sc = new Scanner(System.in);
			}
			break;
		}
		int count = money/1000;
		int change = money%1000;
		
		System.out.println("\n행운의 로또번호는 아래와 같습니다.");
		for (int i = 0; i < count; i++) {
			List<Integer> lotto = new ArrayList<Integer>();
			while(true) {
				int num = (int)(Math.random()*45+1);
				boolean result = lotto.contains(num);
				if(result) {
					continue;
				}
				lotto.add(num);
				if(lotto.size()==6) {
					System.out.println("로또번호"+ (i+1) + ": "+lotto.get(0)+","+lotto.get(1)+","+lotto.get(2)+","+lotto.get(3)+","+lotto.get(4)+","+lotto.get(5));
					break;
				}
			}
		}
		System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+change+"원입니다.");
		System.out.println("===============================");
		
	}
	
	
	
	
}
