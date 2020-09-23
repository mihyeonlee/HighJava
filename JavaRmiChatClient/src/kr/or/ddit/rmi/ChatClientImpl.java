package kr.or.ddit.rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import kr.or.ddit.rmi.inf.ChatClient;
import kr.or.ddit.rmi.inf.ChatServer;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable {
	
	private ChatServer server;
	static String name;
	
	public ChatClientImpl(String name) throws RemoteException, NotBoundException {
		
		ChatClientImpl.name = name;
		
		// 등록된 서버를 찾기 위해 Registry객체를 생성한 후 사용할 객체를 불러온다.
		Registry reg = LocateRegistry.getRegistry("192.168.44.45", 8888);
		
		server = (ChatServer) reg.lookup("RMIChatServer");
		
		server.addClient(this, name);
	}
	
	
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
			String message = null;
			
			while((message = br.readLine()) != null) {
				server.say(name + " : " + message);
				
				if(message.equals("exit")) {
					server.disconnect(this, name);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void printMessage(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	public static void main(String[] args) throws Exception, Exception {
		String name = JOptionPane.showInputDialog("대화명을 입력해주세요.");
		
		new Thread(new ChatClientImpl(name)).start();
	}
	
}
