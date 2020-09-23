package collection;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Room{
	
	
}


public class Hotel {
	Map<Integer, String> room = new HashMap<Integer, String>();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Hotel h = new Hotel();
		h.room.put(101, null);
		h.room.put(102, null);
		h.openHotel();
	}
	
	/**
	 * 호텔을 여는 메서드
	 */
	public void openHotel() {
		System.out.println("********************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("********************");
		System.out.println("");
		int work = 0;
		while(true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?\r\n" + 
					"1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택=>");
			try {
				work = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			
			switch (work) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomCondition();
				break;
			case 4:
				closeHotel();
				break;

			default:
				System.out.println("1~4사이의 숫자 중에 입력해주세요.");
				
			}
			
		}
			
		
	}
	
	
	/**
	 * 1.체크인하는 메서드
	 */
	public void checkIn() {
		int roomNum = 0;
		String cus = null;
		while(true) {
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.print("방 번호 입력=>");
			try {
				roomNum = sc.nextInt();
				if(!room.containsKey(roomNum)) {
					System.out.println("없는 방번호 입니다.");
					continue;
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			break;
		}
		while(true) {
			System.out.println("누구를 체크인 하시겠습니까?");
			try {
				cus = sc.next();
			} catch (Exception e) {
				System.out.println("잘못입력되었습니다. 다시입력해주세요.");
				continue;
			}finally {
				sc = new Scanner(System.in);
			}
			break;
		}
		if(room.get(roomNum)==null) {
			room.put(roomNum, cus);
				if(room.get(roomNum).equals(cus)) {
					System.out.println("체크인 되었습니다.");
				}else {
					System.out.println("체크인에 실패했습니다. 다시시도해 주세요.");
					return;
				}
		}else {
			System.out.println(roomNum+"방에는 이미 사람이 있습니다.");
		}
		
	}
	
	/**
	 * 2.체크아웃하는 메서드
	 */
	public void checkOut() {
		int roomNum = 0;
		String cus = null;
		while(true) {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			try {
				roomNum = sc.nextInt();
				if(!room.containsKey(roomNum)) {
					System.out.println("없는 방번호 입니다. 다시입력해주세요.");
					continue;
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			if(room.get(roomNum)==null) {
				System.out.println(roomNum+"에는 체크인한 사람이 없습니다.");
				return;
			}else {
				room.remove(roomNum);
				System.out.println("체크아웃 되었습니다.");
			}
			break;
		}
	}
	
	/**
	 * 3.객실상태 확인하는 메서드
	 */
	public void roomCondition() {
		Set<Map.Entry<Integer, String>> mapSet = room.entrySet();
		Iterator<Map.Entry<Integer, String>> it = mapSet.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, String> entry = it.next();
			System.out.println("방번호: "+entry.getKey()+",투숙객: "+entry.getValue());
		}
	}
	
	
	/**
	 * 업무종료하는 메서드
	 */
	public void closeHotel() {
		System.exit(0);
	}
	
	
	
	
	
	
	
}











