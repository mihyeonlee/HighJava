package IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


public class Hotel {
	Scanner sc = new Scanner(System.in);
	//방번호와 방상태정보를 담는 Map객체 room생성
	private Map<String, RoomInfo> room;
	File file = new File("d:/D_Other/HotelIO.bin"); //파일객체생성
	
	// room 초기화
	private Hotel(){
		room = new HashMap<String, RoomInfo>();
		room.put("101", new RoomInfo("101", ""));
		room.put("102", new RoomInfo("102", ""));
	}
	
	public static void main(String[] args) {
		Hotel h = new Hotel();
		h.openHotel();
	}
	
	/**
	 * 전체 방상태정보를 출력하기 위한 메서드
	 */
	private void outputFile(){
		if(!file.exists()){
			try {
				file.createNewFile();
				System.out.println("파일생성성공");
			} catch (IOException e) {
				System.out.println("파일생성실패");
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(
							new BufferedOutputStream(
									new FileOutputStream(file)));
			oos.writeObject(room);
			System.out.println("영업정보가 저장되었습니다.");
			oos.close();
		
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * 전체방상태정보파일을 읽어와 room에 저장
	 * room: 전체방정보
	 */
	private void inputFile(){
		if(!file.exists()){
			try {
				file.createNewFile();
//				System.out.println("파일생성성공");
			} catch (IOException e) {
//				System.out.println("파일생성실패");
				e.printStackTrace();
			}
		}
		
		
		//입력용 스트림 객체 생성
		try {
			ObjectInputStream ois = 
					new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(file)));
		
			Object obj = null; 
			
			try{
				
				while((obj = ois.readObject())!= null){
					
					room = (Map<String, RoomInfo>)obj;
				}
				ois.close();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
//			System.out.println("출력작업끝");
		}
		
	}
	
	
	/**
	 * 호텔을 여는 메서드
	 */
	private void openHotel() {
		inputFile();
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
	 * roomNum: 방번호 
	 */
	public void checkIn() {
		String roomNum = null;
		String guestName = null;
		
		
		
		while(true) {
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.println("이전메뉴로 돌아가기-> 1");
			System.out.println("*******************************************");
			System.out.print("방 번호 입력 => ");
			try {
				roomNum = sc.next();
				if("1".equals(roomNum)) {
					return;
				}
				if(!room.containsKey(roomNum)) {
					System.out.println("없는 방번호 입니다.");
					System.out.println("*******************************************");
					continue;
				}
				if(!room.get(roomNum).getGuestName().equals("")) {
					System.out.println(roomNum+"방에는 이미 사람이 있습니다.");
					System.out.println("*******************************************");
					continue;
				}
			} catch (NullPointerException e) {
				System.out.println("이름을 입력해주세요.");
				break;
			}
			break;
		}
		while(true) {
			System.out.println("*******************************************");
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("투숙객이름 >> ");
			try {
				guestName = sc.next();
			} catch (NullPointerException e) {
				System.out.println("잘못입력되었습니다. 다시입력해주세요.");
				continue;
			}finally {
				sc = new Scanner(System.in);
			}
			break;
		}
		room.put(roomNum, new RoomInfo(roomNum, guestName));
		System.out.println("체크인 되었습니다.");
		
		
	}
	
	/**
	 * 2.체크아웃하는 메서드
	 */
	public void checkOut() {
		String roomNum = null;
		while(true) {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			
			try {
				roomNum = sc.next();
				System.out.println("*******************************************");
				if(!room.containsKey(roomNum)) {
					System.out.println("없는 방번호 입니다. 다시입력해주세요.");
					System.out.println("*******************************************");
					continue;
				}
			} catch (NullPointerException e) {
				System.out.println("다시 입력해주세요.");
				System.out.println("*******************************************");
				continue;
			}
			if(room.get(roomNum).getGuestName()==null) {
				System.out.println(roomNum+"에는 체크인한 사람이 없습니다.");
				System.out.println("*******************************************");
				return;
			}else {
				room.get(roomNum).setGuestName("");
				System.out.println("체크아웃 되었습니다.");
			}
			break;
		}
	}
	
	/**
	 * 3.객실상태 확인하는 메서드
	 */
	public void roomCondition() {
		Set<Entry<String, RoomInfo>> mapSet = room.entrySet();
		Iterator<Entry<String, RoomInfo>> it = mapSet.iterator();
		
		while(it.hasNext()) {
			Entry<String, RoomInfo> entry = it.next();
			try {
				
				if(entry.getValue().getGuestName()!=null) {
					
					System.out.println("방번호: "+entry.getKey()+
										",투숙객: "+entry.getValue().getGuestName());
				}else {
					System.out.println("방번호: "+entry.getKey()+
							",투숙객: 없음");
				}
				

			}catch (NullPointerException e) {
				System.out.println("*******************************************");
				System.out.println("예약된 객실이 없습니다.");
				break;
			}
		}
	}
	
	
	/**
	 * 업무종료하는 메서드
	 */
	public void closeHotel() {
		outputFile();
		System.out.println("영업을 종료합니다. 띠로리리");
		System.out.println("*******************************************");
		System.exit(0);
	}
	
	
	
	
	
	
	
	
}

/**
 * 방상태정보 담겨있는 클래스 
 * roomNum: 방번호  guestName: 투숙객이름
 * @author algus
 */
class RoomInfo implements Serializable{
	
	private String roomNum;
	private String guestName;
	
	public RoomInfo(String roomNum, String guestName) {
		this.roomNum = roomNum;
		this.guestName = guestName;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
}









