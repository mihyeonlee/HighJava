package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	//1. 드라이버 로딩
	// static블럭 class가 로딩되는 시점에 딱한번 실행됨.
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}// static블럭 종료
	
	//2. DB접속
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","lmh","java");
		}catch (SQLException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
}
