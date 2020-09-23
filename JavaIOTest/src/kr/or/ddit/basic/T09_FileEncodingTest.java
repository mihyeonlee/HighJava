package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
/*
 	InputStreamReader 객체는 파일의 인코딩 방식을 지정할 수 있다.
 	형식) new InputStreamReader(바이트 기반 스트림 객체, 인코딩 방식)
 	
 	**인코딩 방식
 	한글 인코딩 방식은 크게 UTF-8과 EUC-KR방식 두가지로 나뉜다.
 	원래 한글 윈도우는 CP949방식을 사용했는데, 
 	윈도우를 개발한 마이크로소프트에서 EUC-KR방식에서 확장하였기 때문에 MS949라고도 부른다.
 	
 	한글 Windows의 메모장에서 이야기하는 ANSI 인코딩이란, CP949(Code Page 949)를 말한다. 
 	CP949는 EUC-KR의 확장이며, 하위 호환성이 있다. 
 	- MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
 	- UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자:1byte, 한글:3byte)
 	- US-ASCII => 영문 전용 인코딩 방식
 	
 	ANSI는 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없었다가 나중에 여기에 EUC-KR, CP949 이라는 식으로 한글이 포함되었다.
 	
 	참고)
 		ASCII => extended ASCII(ISO 8859-1) 
 			  => 조합형(초성, 중성, 종성), 완성형(KSC 5601)
 			  
 		=> 윈도우계열: CP949(확장완성형) - 일부문자(8822자) 추가함.
 		=> 유닉스계열: EUC-KR(확장유닉스 코드)
 		
 		=> ANSI계열 => EUC-KR
 		-------------------------------------------------------------------------------
 		=> 유니코드(UTF-8)
 */
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		//InputStreamReader 스트림 => 바이트기반 스트림을 문자기반 스트림으로 변환해 주는 보조 스트림.
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			// FileInputStream객체를 생성한 후 이 객체를 매개변수로 받는
			// InputStreamReader객체를 생성한다.
//			fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt"); // 밑에 인코딩 방식이 다르기때문에 이상하게나온다.
			
//			isr = new InputStreamReader(fis,"cp949"); //인코딩방식을 알려준다.
			isr = new InputStreamReader(fis,"utf-8"); // 파일형식과 인코딩방식이 일치해야 정확히 나온다.
			
			int c;
			while((c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력끝.");
			
			isr.close(); //보조 스트림만 닫아도 된다.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}







































