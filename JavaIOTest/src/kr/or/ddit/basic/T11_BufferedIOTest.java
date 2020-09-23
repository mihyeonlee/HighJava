package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		
		//입출력 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192bytes(8kb)로 설정된다.
			//버퍼의 크기가 클수록 메모리를 많이 차지하게 된다. 메모리는 한정되어있기 때문에 사용할 만큼 적당히 설정한다. 
			
			//버퍼 크기가 5인 보조스트림 객체 생성
			bos = new BufferedOutputStream(fos, 5);
			//숫자 자체를 문자로 저장하기 위해 ''사용함.
			for(int i='1';i<='9';i++) {
				bos.write(i); // 크기(5)만큼 버퍼에 담기면 write해줌  1-5까지 담기고 write 6789는 4개기 때문에 write되지 않다가 밑에 flush()를만나 해결
			}
			
			bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다. (close시 자동으로 호출됨)
			
			System.out.println("작업 끝.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
