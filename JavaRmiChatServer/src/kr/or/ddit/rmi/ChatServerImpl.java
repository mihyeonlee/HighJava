package kr.or.ddit.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import kr.or.ddit.rmi.inf.ChatClient;
import kr.or.ddit.rmi.inf.ChatServer;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer{
	
	Vector<ChatClient> clientList = new Vector<>();
	
	protected ChatServerImpl() throws RemoteException { }

	@Override
	public void addClient(ChatClient client, String name) throws RemoteException {
		
		clientList.addElement(client);
		
		say(name + "님이 접속하셨습니다.");
		
	}

	@Override
	public void disconnect(ChatClient client, String name) throws RemoteException {
		
		int index = clientList.indexOf(client);
		if(index >= 0) {
			say(name + "님이 퇴장하셨습니다.");
			
			clientList.removeElementAt(index);
			
			System.out.println("현재 클라이언트 수 : " + clientList.size());
			
		}else {
			System.out.println("해당 사용자가 존재하지 않습니다.");
		}
	}

	@Override
	public void say(String msg) throws RemoteException {
		
		int totCnt = clientList.size();
		
		for(int i = 0; i < totCnt; i++) {
			((ChatClient) clientList.elementAt(i)).printMessage(msg);
		}
	}
	
	public static void main(String[] args) {
		
		try {
			ChatServerImpl server = new ChatServerImpl();
			
			Registry reg = LocateRegistry.createRegistry(8888);
			reg.rebind("RMIChatServer", server);
			
			System.out.println("RMI Chat서버 실행중...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	

}
