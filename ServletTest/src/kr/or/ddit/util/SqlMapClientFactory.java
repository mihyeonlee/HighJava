package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	private static SqlMapClient smc; // SqlMapClient 객체변수 선언 dao에서 한개의client객체를 쓰기위해 dao가 아무리 많아져도 괜찮아진다.
	
	private SqlMapClientFactory() { } 
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			Reader rd;
			try {
				// 1-1. xml문서 읽어오기
				Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩 설정 (영어만 쓰면 안해도됨)
				Resources.setCharset(charset);
				
				rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성(SqlMapClient 객체)
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close(); // Reader객체 닫기 
				
			}catch (IOException e) {
				System.out.println("SqlMapClient객체 생성 실패!");
				e.printStackTrace();
			}
		}
		return smc;	
	}
	
	
	
	
}
