package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {
	public static void main(String[] args) {
		Thread autoSave = new AutoSaveThread();
		
		// 데몬스레드로 설정하기(start()메서드 호출하기 전에 설정한다.)
		//setDaemon(true)으로 데몬스레드설정  얘도 호출전에 설정한다.
		autoSave.setDaemon(true); 
		
		autoSave.start();//시작
		
		try {
			for(int i=1; i<=20;i++) {
				System.out.println("작업"+i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 스레드 종료");
	}
}

/**
 * 자동 저장하는 기능을 제공하는 스레드 클래스
 * (3초에 한번씩 저장하기)
 */
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true) { //무한루프 여도 위에서 데몬스레드로 설정했고 때문에 일반스레드가 종료되면 같이 종료된다.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();// 저장기능 호출
		}
		
	}
	
}




