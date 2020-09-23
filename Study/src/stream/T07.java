package stream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07 {
	public static void main(String[] args) {
		//사용자가 입력한 내용 그대로 파일로 저장하기
		// 콘솔과 연결된 입력용 문자 스트림을 생성한다. 
		
		//바이트 기반의 스트림을 문자기반 스트림으로 변환해주는 보조스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//파일 출력용 문자기반 스트림 
		FileWriter fw = null;
		
		try {
			//파일 출력용 문자기반 스트림 객체 생성 파일생성해줌.
			fw = new FileWriter("d:/D_Other/testChar.txt");
			System.out.println("아무거나 입력하세요.");
			int c;
			
			while((c=isr.read()) != -1) {
				fw.write(c);
			}
			System.out.println("작업 끝.");
			
			isr.close();
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
