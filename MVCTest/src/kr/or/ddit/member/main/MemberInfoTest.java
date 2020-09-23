package kr.or.ddit.member.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class MemberInfoTest {
	
	// Service객체 변수를 선언한다.
	private IMemberService service; 
	
	private Scanner scan;
	
	public MemberInfoTest() {
		service = new MemberServiceImpl();
		scan = new Scanner(System.in); 
	}
	
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("------------------------------------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
			
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 회원 정보를 삭제하는 메서드(입력받은 회원id를 이용하여 삭제한다.)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.print("삭제할 회원ID를 입력하세요 >> ");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		
		System.out.println("------------------------------------------------");
		
		if(cnt >0) {
			System.out.println(memId+"회원삭제작업 성공!");
		}else {
			System.out.println(memId+"회원삭제작업 실패");
		}
		
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		System.out.println();
		
		String memId = "";
		boolean chk = true;
		
		do {
			System.out.print("수정할 회원ID를 입력하세요. >> ");
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk == false) {
				System.out.println(memId +"는 없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력해주세요.");
			}
		} while (chk == false);
		
		System.out.println("수정할 내용을 입력해주세요.");
		System.out.print("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = service.updateMember(mv);
		System.out.println("------------------------------------------------");
		if(cnt >0) {
			System.out.println(memId+"회원수정작업 성공!");
		}else {
			System.out.println(memId+"회원수정작업 실패");
		}
		
	}

	/**
	 * 전체 회원을 출력하는 메서드 
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주  소");
		System.out.println("------------------------------------------------");
		
		List<MemberVO> memList = service.getAllMember();
		for(MemberVO mv : memList) {
			System.out.println(mv.getMem_id() + "\t" + mv.getMem_name() + 
					"\t" + mv.getMem_tel() + "\t" + mv.getMem_addr());
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("출력 작업 끝.");

	}

	/**
	 * 회원을 추가하는 메서드
	 */
	private void insertMember() {
		
		boolean chk = false; // false면 없는 ID
		
		String memId;
		
		do {
			System.out.println();
			System.out.println("추가할 회원정보를 입력하세요.");
			System.out.print("회원ID >> ");
			
			memId = scan.next(); // ID입력받기
			
			chk = getMember(memId); // 회원존재를 알려주는메서드
			
			if(chk) {
				System.out.println("회원ID가"+memId+"인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		// 중복되는 id가 아닐때까지 반복문이 수행된다. 	
		} while (chk==true);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기 enter값을 없앤다.
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = service.insertMember(mv);
		System.out.println("------------------------------------------------");
		if(cnt >0) {
			System.out.println(memId+"회원추가작업 성공!");
		}else {
			System.out.println(memId+"회원추가작업 실패");
		}
		
		
		
		
	}
	
	/**
	 * 회원ID를 이용하여 회원이 존재하는지를 알려주는 메서드
	 * @param memId
	 * @return true:이미존재 , false: 존재하지 않음
	 */
	private boolean getMember(String memId) {
		return service.getMember(memId);
	}
		
		

	public static void main(String[] args) {
		MemberInfoTest memObj = new MemberInfoTest();
		memObj.start();
	}

}






