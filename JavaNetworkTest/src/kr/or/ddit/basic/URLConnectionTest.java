package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		// URLConnection => 어플리케이션과 URL간의 통신 연결을 위한 추상 클래스
		
		// 특정 서버(예: naver서버)의 정보와 파일 내용을 출력하는 예제
		// URL클래스를 통해 url정보를 가져올 수 있다. 
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header정보 가져오기
		
		// URLConnection 객체 구하기
		URLConnection urlCon = url.openConnection();
		
		System.out.println("Content-Type : "+urlCon.getContentType());
		System.out.println("Encoding: "+urlCon.getContentEncoding());
		System.out.println("Content: "+urlCon.getContent());
		System.out.println();
		
		// 전체 Header 정보 출력하기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// Header의 key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key +" : "+ headerMap.get(key));
		}
		System.out.println("---------------------------------------------");
		
		// 해당 호스트의 페이지 내용 가져오기
		//방법1) URLConnection의 getInputStream메서드 이용하기 1byte씩 읽어온다.
		InputStream is = urlCon.getInputStream(); // 헤더정보를 가져오고싶으면 url커넥션객체를 연결해가져오고
		//InputStream is = url.openStream(); html만 가져오려하면 url.openStream()으로 가져올 수 있다.
		InputStreamReader isr = new InputStreamReader(is,"utf-8"); //보조스트림(바이트를 문자기반으로 바꿔주는), 인코딩(한글사용)
		
		//방법2) URL객체의 openStream() 메서드 이용하기 
		//InputStream is = url.openStream(); 
		
		int c;
		while((c=isr.read()) != -1) {
			System.out.print((char)c);
		}
		isr.close();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
