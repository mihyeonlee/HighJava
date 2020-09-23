package Board.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	
	private static SqlMapClient smc;	// SqlMapClient객체 변수 선언
	
	public SqlMapClientFactory() { }
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			Reader rd;
			try {
				// 1-1. xml문서 읽어오기
				Charset charset =Charset.forName("UTF-8");	// 설정파일의 인코딩 설정
				Resources.setCharset(charset);
				
				rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close();	// Reader객체 닫기
				
				
			} catch (IOException ex) {
				System.out.println("SqlMapClient객체 생성 실패");
				ex.printStackTrace();
			}
		}return smc;
	}

}
