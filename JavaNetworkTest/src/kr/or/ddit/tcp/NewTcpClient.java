package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;

public class NewTcpClient {
	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket("192.168.44.44",7777);
		System.out.println("서버에 연결되었습니다...");
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}
