package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 클라이언트 서버에 접속하여 서버가 보내주는 파일을 D드라이브의 C_Lib폴더에 저장한다.
 */
public class TcpFileClient {
	
	private Socket socket;
	private InputStream in;
	private FileOutputStream fos;
	
	public void clientStart() {
		File file = new File("d:/C_Lib/Tulips.jpg"); // 저장할 파일 설정
		
		try {
			socket = new Socket("localhost",7777);
			
			System.out.println("파일다운로드 시작");
			
			fos = new FileOutputStream(file);
			in = socket.getInputStream();
			
			byte[] tmp = new byte[1024];
			int length = 0;
			while((length=in.read(tmp))!=-1) {
				fos.write(tmp,0,length);
			}
			fos.flush();
			System.out.println("파일 다운로드 완료");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * main 메서드
	 */
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}

}
