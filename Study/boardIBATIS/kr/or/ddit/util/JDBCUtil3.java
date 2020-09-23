package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil3 {
	static ResourceBundle bundle; // ResourceBundle객체 변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("db"); // 객체생성
		
		try {
			Class.forName(bundle.getString("driver")); // getString으로 키값을 불러올수있다.
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(bundle.getString("url"),
												bundle.getString("user"),
												bundle.getString("pass"));
		}catch (SQLException e) {
			System.out.println("db연결 실패");
			e.printStackTrace();
			
			return null;
		}
	}
	
}
