package EnumTest;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class PlanetTest {
	Scanner sc = new Scanner(System.in);
	
	//열거형 객체 선언
	public enum Planet{
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		//괄호 속의 값이 저장될 변수
		private int r;
		//생성자정의
		Planet(int data){
			r = data;
		}
		//괄호 속의 값을 반환하는 메서드
		public int getR() {
			return r;
		}
		
		public long area() {
			
			long area = (long) ((Math.pow(r, 2))*4*3.14);
			return area;
		}
	}
	
	public static void main(String[] args) {
		//열거형이름.values 데이터를 배열로 가져온다.
		Planet[] pArr = Planet.values();
		for(int i=0;i<pArr.length;i++) {
			System.out.println(pArr[i].name()+"의 면적은 약 "+pArr[i].area()+"km² 입니다.");
		}
		
		while(true) {
			try {
				String name = JOptionPane.showInputDialog("면적을 구하려는 행성의 이름을 입력해주세요.");
				System.out.println(name+"의 면적은 "+Planet.valueOf(name).area()+"km² 입니다.");
			} catch (IllegalArgumentException e) {
				System.out.println("없는 행성입니다.");
				continue;
			}
			break;
		}
		
	}
	
	
	

}
