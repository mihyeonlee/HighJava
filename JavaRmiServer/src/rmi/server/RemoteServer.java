package rmi.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import rmi.inf.RemoteInterface;
import rmi.vo.FileInfoVO;
import rmi.vo.TestVO;

/**
 * RMI용 서비스를 제공하는 객체는 RMI용 인터페이스를 구현하고
 * UnicastRemoteObject 클래스를 상속해서 작성한다.
 * 원격에 있는 자바객체를 로컬에 있는 것 처럼 호출할 수 있다.
 * UnicastRemote
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{
	
	// RMI용 인터페이스를 구현한 객체의 생성자도 RemoteException을 throws하도록 작성한다.
	protected RemoteServer() throws RemoteException{
		//super();
		
		
	}

	@Override
	public int doRemotePrint(String str) throws RemoteException {
		int length = str.length();
		
		System.out.println("클라이언트에서 보내온 메시지: "+str);
		System.out.println("출력 끝");
		
		return length;
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("클라이언트에서 보낸 List값들...");
		for(int i =0;i<list.size();i++) {
			System.out.println((i+1)+"번째: "+list.get(i));
		}
		System.out.println("List 출력 끝 ");
		
	}

	@Override
	public void doPrintVO(TestVO vo) throws RemoteException {
		System.out.println("클라이언트에서 보내온 TestVO객체 값 출력");
		System.out.println("testId: "+vo.getTestId());
		System.out.println("testNum: "+vo.getTestNum());
		System.out.println("TestVO객체 출력 끝");
		
	}

	@Override
	public void setFiles(FileInfoVO[] fInfo) throws RemoteException {
		
		FileOutputStream fos = null;
		String dir = "D:/C_Lib/"; // 파일이 저장될 폴더 지정
		System.out.println("파일 저장시작");
		for(int i=0;i<fInfo.length;i++) {
			try {
				fos= new FileOutputStream(dir + fInfo[i].getFileName());
				
				// 클라이언트에서 전달한 파일데이터(byte[])를 서버측에 저장한다.
				fos.write(fInfo[i].getFileData());
				fos.close();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("파일저장완료");
	}
	
	/**
	 * main메서드
	 * 1. RMI서버 등록 클라이언트가 사용할 수 있도록
	 */
	public static void main(String[] args) {
		try {
			// 구현한 RMI용 객체를 클라이언트에서 사용할 수 있도록 RMI서버에 등록한다.!!!!!!!!!!!
			
			// 1. RMI용 인터페이스를 구현한 객체 생성
			RemoteInterface inf = new RemoteServer();
			
			// 2. 구현한 객체를 클라이언트에서 찾을 수 있도록 Registry객체를 생성해서 등록한다.
			
			// 포트번호를 지정해서 Registry객체 생성(기본포트 값: 1099)
			Registry reg = LocateRegistry.createRegistry(8888);
			
			// Registry서버에 제공하는 객체 등록
			// 형식) Registry객체 변수.rebind("객체의 Alias", 등록할 객체변수);
			 reg.rebind("server", inf);
			 
			 System.out.println("서버가 준비되었습니다.");
			
		} catch (RemoteException e) {
			// TODO: handle exception
		}
	}
	
	
}
