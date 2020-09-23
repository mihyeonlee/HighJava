package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

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
public class T05_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt; // 
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	// Log4j를 이용한 로그를 남기기 위한 로거 생성
	private static final Logger SQL_LOGGER = Logger.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = Logger.getLogger(T05_MemberInfoTest.class);
	
	
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
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(memId+"회원정보 삭제 성공");
			}else {
				System.out.println(memId+"회원정보 삭제 실패");
			}
			
		} catch (SQLException e) {
			System.out.println(memId+"회원정보 삭제 실패");
			e.printStackTrace();
		}finally {
			disconnect();
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
			System.out.println("수정할 회원ID를 입력하세요. >> ");
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
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update mymember \r\n " + 
					" set mem_name= ? \r\n " + 
					"   ,mem_tel = ? \r\n " + 
					"   ,mem_addr = ? \r\n " + 
					"  where MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원의 정보를 수정했습니다. ");
			}else {
				System.out.println(memId+"회원정보 수정 실패");
			}
		}catch (SQLException e) {
			System.out.println(memId+"회원정보 수정 실패");
			e.printStackTrace();
		}finally {
			disconnect();
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
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t"+memTel + "\t"+memAddr);
			}
			System.out.println("------------------------------------------------");
			System.out.println("출력작업 끝");
		} catch (SQLException e) {
			System.out.println("회원자료 가져오기 실패");
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
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
		
		try {
			conn = JDBCUtil2.getConnection();
			String sql = "insert into mymember(mem_id, mem_name, mem_tel, mem_addr)"
					+ " values(?,?,?,?)"; // 공백처리 잘해야한다. 
			
			SQL_LOGGER.debug("쿼리 : "+ sql);
			
			pstmt = conn.prepareStatement(sql); // sql을 파라미터로 가져가기 때문에 sql문작성되어있어야함.
			// sql문안에 있는 물음표에 값을 넣어준다.
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			PARAM_LOGGER.debug("파라미터: ("+ memId
								+ ", " + memName
								+ ", " + memTel
								+ ", " + memAddr + ")");
			
			
			int cnt = pstmt.executeUpdate();
			
			RESULT_LOGGER.warn("결과: "+cnt);
			
			if(cnt >0) {
				System.out.println(memId+"회원추가작업 성공!");
			}else {
				System.out.println(memId+"회원추가작업 실패");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("회원 추가작업 실패");
		}finally {
			disconnect();
		}
		
			
	}
	
	/**
	 * 회원ID를 이용하여 회원이 존재하는지를 알려주는 메서드
	 * @param memId
	 * @return true:이미존재 , false: 존재하지 않음
	 */
	private boolean getMember(String memId) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from mymember"
					+" where mem_id = ?"; // ?를 이용해 틀만 만들어 놓고 나중에 쿼리를 만드는 시점에 값만 바꿔넣는다. 
										// 코드재활용가능.
			
			// prepareStatement()는 객체를 만드는 시점에 쿼리가 준비되어있어야한다. statement()와 차이점.
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			//sql문의 결과를 cnt에 담는다.
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt >0) {
				chk = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		}finally {
			disconnect();
		}
		return chk;
	}

	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		//6. 종료(사용했던 자원을 모두 반납한다.)
		if(rs!=null)try {rs.close();}catch (SQLException e) { }
		if(stmt!=null)try {stmt.close();}catch (SQLException e) { }
		if(pstmt!=null)try {pstmt.close();}catch (SQLException e) { }
		if(conn!=null)try {conn.close();}catch (SQLException e) { }
		
	}

	public static void main(String[] args) {
		T05_MemberInfoTest memObj = new T05_MemberInfoTest();
		memObj.start();
	}

}






