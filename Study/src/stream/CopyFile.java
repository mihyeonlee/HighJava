package stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); //시작시간
		FileInputStream iis = null;  //파일을 읽어오는 바이트기반 스트림객체생성
		FileOutputStream ois = null; //파일을 입력하는 바이트기반 스트림객체생성
		
		try {
			iis = new FileInputStream("d:\\D_Other\\Tulips.jpg"); // 복사할 파일주소를 적는다. 문자입력시 예외처리
			ois = new FileOutputStream("d:\\D_Other\\복사본_Tulips.jpg"); // 입력할 파일을 만든다.
			int c ; // 읽어온 바이트를 담을 변수
			
			while((c=iis.read())!= -1) {  // 해당파일에 읽어올 수 있는 데이터가 없을때까지 반복
				ois.write(c);  // 하나씩 읽어와서 지정한 위치에 생성된 파일에 데이터를 쓴다.
			}
			iis.close();
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); //작업종료시간
		System.out.println("경과시간: "+(endTime-startTime));
		
		long startTime2 = System.currentTimeMillis(); //버퍼사용 시작시간
		FileInputStream fis2 = null;
		BufferedInputStream bis = null;
		FileOutputStream fos2 = null;
		BufferedOutputStream bos = null;
		try {
			fis2 = new FileInputStream("d:\\D_Other\\Tulips.jpg");
			bis = new BufferedInputStream(fis2);
			fos2 = new FileOutputStream("d:\\D_Other\\복사본_Tulips.jpg");
			bos = new BufferedOutputStream(fos2);
			int temp;
			while((temp=bis.read()) != -1) {
				bos.write(temp);
			}
			bis.close();
			bos.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		long endTime2 = System.currentTimeMillis(); //버퍼사용 종료시간
		System.out.println("버퍼사용 경과시간 : "+(endTime2-startTime2));
		
		
		
		
		
		
	}
	

}
