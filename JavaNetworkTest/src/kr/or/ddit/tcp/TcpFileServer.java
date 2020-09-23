package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
// 서버는 클라이언트가 접속하면 서버 컴퓨터의 D:/D_Other폴더에 있는 Tulips.jpg파일을 클라이언트로 전송한다.
	private ServerSocket server;
	private Socket socket;
	private OutputStream out;
	private FileInputStream fis;
	
	/**
	 * 서버시작하는 메서드
	 */
	public void serverStart() {
		File file = new File("d:/D_Other/Tulips.jpg");
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료");
			
			socket = server.accept();
			
			System.out.println("파일전송 시작.");
			fis = new FileInputStream(file);
			out = socket.getOutputStream();
			
			byte[] tmp = new byte[1024]; // 한꺼번에 읽어와 전송할 데이터 저장 변수 선언
			
			int length = 0;
			
			//-1이 나오면 읽을게 없음
			while((length = fis.read(tmp)) != -1) { 
				out.write(tmp, 0, length);
			}
			
			out.flush();
			System.out.println("파일 전송 완료");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {fis.close();}catch(IOException e) {}
			}
			if(out != null) {
				try {out.close();}catch(IOException e) {}
			}
			if(socket != null) {
				try {socket.close();}catch(IOException e) {}
			}
			if(server != null) {
				try {server.close();}catch(IOException e) {}
			}
		}
	}
	
	/**
	 * main메서드
	 */
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
}
