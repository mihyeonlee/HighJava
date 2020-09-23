package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws IOException {
		// TCP 소켓 통신을 하기 위해 ServerSocket 객체 생성 서버소켓은 서버에서 제공 클라이언트는 또 따로
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다.");
		
		// accept()메서드는 Client에서 연결요청이 올 때까지 계속 기다린다. 
		// 연결 요청이 오면 Socket객체를 생성해서 Client의 Socket과 연결한다.
		Socket socket = server.accept();
		
		//------------------------------------------------
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소: "+ socket.getInetAddress());
		
		// Client에 메시지 보내기
		
		// OutputStream객체를 구성하여 전송한다.
		// 접속한 Socket의 getOutputStream()메서드를 이용하여 구한다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("어서 오세요..."); // 메시지 보내기
		System.out.println("메시지를 보냈습니다.");
		
		dos.close();
		server.close();
	}
}
