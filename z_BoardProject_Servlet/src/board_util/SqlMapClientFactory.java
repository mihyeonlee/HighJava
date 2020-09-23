package board_util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapClient 객체를 생성하기 위한 클래스
 */
public class SqlMapClientFactory {

	private static SqlMapClient smc; // SqlMapClient 객체변수 선언
	
	private SqlMapClientFactory() { }
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			// ibatis 기본 설정
			Reader rd;
			
			try {
				// 1-1. xml문서 읽어오기
				Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩 설정 => 한글 설정을 위해, xml파일안에 한글있을수도 있기 때문에 생략 가능하지만 인코딩 해준다.
				Resources.setCharset(charset);
				
				rd = Resources.getResourceAsReader("SqlMapConfig.xml"); // 예외발생
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close(); // Reader 객체 닫기
				
			}catch (IOException e) {
				System.out.println("SqlMapClient객체 생성 실패!!!");
				e.printStackTrace();
			}
		}
		return smc;
	}
	
}
