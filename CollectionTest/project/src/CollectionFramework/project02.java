package CollectionFramework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Map을 이용한 호텔 운영을 관리하는 프로그램
 * 키값은 방번호
 * @author 김미연
 * @since 2020-07-17
 */
public class project02 {
	
	/*
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 2 <-- 입력
																	
	어느방을 체크아웃 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	체크아웃 되었습니다.
																			
	 *******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 1 <-- 입력
																					
	어느방에 체크인 하시겠습니까?
	방번호 입력 => 102 <-- 입력
																							
	누구를 체크인 하시겠습니까?
	이름 입력 => 허준 <-- 입력
	102방에는 이미 사람이 있습니다.
																									
	 *******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 2 <-- 입력
																											
	어느방을 체크아웃 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	101방에는 체크인한 사람이 없습니다.
																													
	 *******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 3 <-- 입력
																															
	방번호 : 102, 투숙객 : 성춘향
																															
	 *******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 4 <-- 입력
																																	
	 **************************
	호텔 문을 닫았습니다.
	 **************************
	 */
	private Scanner sc = new Scanner(System.in);
	private Map<String, Hotel> map = new HashMap<>();
	
	public static void main(String[] args) {
		new project02().beginMenu();
	}
	
	/**
	 * 메뉴를 보여주는 메서드
	 */
	private void beginMenu() {
		/*
		  **************************
	  	    호텔 문을 열었습니다.
		  **************************
	
		  *******************************************
		    어떤 업무를 하시겠습니까?
		  1.체크인  2.체크아웃 3.객실상태 4.업무종료
		  *******************************************
		 */
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		System.out.println("*******************************************");
		int input = 0; // 입력값 
		while(true) {
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인       2.체크아웃       3.객실상태       4.업무종료");
			System.out.println("*******************************************");
			
			try {
				System.out.print("메뉴선택 => ");
				input = sc.nextInt();
			}catch(Exception e) {
				System.out.println("잘못입력하였습니다. 다시 입력해주세요.");
				continue;
			}
			
			switch(input) {
			case 1 : // 체크인
				checkIn();
				break;
			case 2 : // 체크아웃
				checkOut();
				break;
			case 3 : // 객실상태
				roomCon();
				break;
			case 4 : // 업무종료
				end();
				return;
			default :
				System.out.println("잘못입력하였습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	/**
	 * 체크인을 하는 메서드
	 */
	private void checkIn() {
		/*
		  *******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 1 <-- 입력
			
	어느방에 체크인 하시겠습니까?
	방번호 입력 => 101 <-- 입력
					
	누구를 체크인 하시겠습니까?
	이름 입력 => 홍길동 <-- 입력
	체크인 되었습니다.
							
	 *******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	메뉴선택 => 1 <-- 입력
									
	어느방에 체크인 하시겠습니까?
	방번호 입력 => 102 <-- 입력
											
	누구를 체크인 하시겠습니까?
	이름 입력 => 성춘향 <-- 입력
	체크인 되었습니다
		 */
		String roomNum = null;
		while(true)
		{
			System.out.println("어느방에 체크인 하시겠습니까?");
			try {
				System.out.print("방번호 입력 => ");
				roomNum = sc.next();
				if(map.get(roomNum)!=null) { // 체크인 되어있는지 확인
					System.out.println("이미 체크인 된 방입니다. 다시 입력해주세요.");
					continue;
				}
				break;
			}catch(Exception e) {
				System.out.println("잘못입력하였습니다. 다시 입력해주세요.");
				continue;
			}
		}
		
		String name = null;
		while(true)
		{
			System.out.println("누구를 체크인 하시겠습니까?");
			try {
				System.out.print("이름 입력 => ");
				name = sc.next();
				break;
			}catch(Exception e) {
				System.out.println("잘못입력하였습니다. 다시 입력해주세요.");
				continue;
			}
		}
		Hotel hotel = new Hotel(roomNum, name);
		System.out.println(hotel.getName()+"씨의 체크인이 완료되었습니다.");
		map.put(roomNum,hotel);
	}

	/**
	 * 체크아웃을 하는 메서드
	 */
	private void checkOut() {
		/*
		 어느방을 체크아웃 하시겠습니까?
		방번호 입력 => 101 <-- 입력
		101방에는 체크인한 사람이 없습니다.
		 */
		if(map.size() == 0) {
			System.out.println("체크인한 사람이 없습니다. 이전으로 돌아갑니다.");
			return;
		}
		String roomNum = null;
		while(true)
		{
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			try {
				System.out.print("방번호 입력 => ");
				roomNum = sc.next();
				if(map.get(roomNum) == null) {
					System.out.println("체크인한 사람이 없습니다. 다시 입력해주세요.");
					continue;
				}
				break;
			}catch(Exception e) {
				System.out.println("잘못입력하였습니다. 다시 입력해주세요.");
				continue;
			}
		}
		Hotel h = map.remove(roomNum);
		System.out.println(h.getRoomNum()+"방이 체크아웃 되었습니다.");
	}

	/**
	 * 방의 상태를 확인하는 메서드
	 */
	private void roomCon() {
		/*
		
		 *******************************************
		어떤 업무를 하시겠습니까?
				1.체크인  2.체크아웃 3.객실상태 4.업무종료
		 *******************************************
				메뉴선택 => 3 <-- 입력
				
				방번호 : 102, 투숙객 : 성춘향
				방번호 : 101, 투숙객 : 홍길동
				
		 *******************************************
		 */
		
		if(map.size() == 0) {
			System.out.println("체크인을 한 고객이 없습니다.");
			return;
		}
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			Hotel h = map.get(it.next());
			System.out.println("방번호 : "+h.getRoomNum() + " , 투숙객 : "+h.getName());
		}
	}

	/**
	 * 업무를 종료하는 메서드
	 */
	private void end() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}
	
}

class Hotel{
	private String roomNum;
	private String name;
	
	public Hotel(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}
	
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
