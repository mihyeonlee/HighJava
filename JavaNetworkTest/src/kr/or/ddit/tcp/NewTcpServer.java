package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NewTcpServer {
	 public static void main(String[] args) {
		// 서버 소케을 만들고, 클라이언트가 접속하면 소켓을 만들어 데이터를 받는 클래스와 데이터를 보내는 클래스에
		// 이 소켓을 넘겨준다.
		// 소켓: 실행하는 프로세스 두 프로세스간에 통신을 할 때 사용하는 것 
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777); // 1024보다 큰 수를 써야한다. 이미사용되는 서비스라서
			System.out.println("서버 준비 완료...");
			socket = server.accept();  // 외부에서 소켓접속이 오는걸 기다린다.(블락)된다.
			
			//System.out.println(socket.toString());
			
			Sender sender = new Sender(socket);
			Receiver reciver = new Receiver(socket);
			
			sender.start();
			reciver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

}
