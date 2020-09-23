package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 소켓을 통해서 메시지를 보내는 역할을 한다.
 *
 */
public class Sender extends Thread {
	Socket socket;
	DataOutputStream dos;
	String name; // 사용자 정보
	
	public Sender(Socket socket) {
		this.socket = socket;
		
		name = "[" + socket.getInetAddress()+" : "+socket.getPort() + "]";
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(dos != null) {
			try {
				dos.writeUTF(name+" >>> "+scan.nextLine());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		scan.close();
	}
}
