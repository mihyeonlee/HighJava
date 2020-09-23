package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T08_FileReaderTest {
	public static void main(String[] args) throws IOException {
		//문자기반의 스트림을 이용한 파일 내용 읽기 한글은 바이트로 불가
		FileReader fr = null;
		
		//문자 단위의 입력을 담당하는 Read형 객체 생성
		fr = new FileReader("d:/D_Other/testChar.txt");
		
		int c;
		while((c=fr.read()) != -1) {
			System.out.print((char)c);
		}
		
		fr.close();
	}
}
