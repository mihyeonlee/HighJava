package kr.or.ddit.basic;
/*
	Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다.
	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에 실행되는 프로그램에 영향을 줄 수 있다. 
	그래서 현재는 stop()메서드는 권장하지 않는다.(Deprecated)되어있음. 
 */
public class T13_ThreadStopTest {
	public static void main(String[] args) {
//		ThreadStopEx1 th = new ThreadStopEx1();
//		th.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
////		th.stop(); // 자원 정리가 끝나지 않음
//	    th.setStop(true); // 플래그(상태값)를 이용한 중지 방법 플래그를 체크하는 곳에서만 종료하기 때문에 다른방식에 비해 딜레이가 있을 수 있다.
	    //-----------------------------------------
	    
	    // interrupt()메서드를 이용한 스레드 멈추기
	    ThreadStopEx2 th2 = new ThreadStopEx2();
	    th2.start();
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    th2.interrupt();// 인터럽트 발생시킴. 밑에서 sleep()을 이용해 try catch 이걸 이용해 빠져나올수 있게  
	}
}

class ThreadStopEx1 extends Thread{
	private boolean stop;
	
	//stop값을 변경할 수 있게 메서드 생성
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중 ...");
		System.out.println("실행 종료.");
	}
}

// interrupt()를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread{
	@Override
	public void run() {
//		//방법1 => sleep()메서드나 join()메서드 등을 사용했을 때 
//		//		interrupt()메서드 호출하면 interruptedException 이 발생한다.
//		try {
//			while(true) {
//				System.out.println("스레드 처리중...");
//				Thread.sleep(1); // sleep()을 예외처리 
//			}
//		} catch (InterruptedException e) { } //인터럽트를 일부로 발생시켜 catch구문으로 넘어가 종료되게한다. 
		
		//방법2 => interrupt()메서드가 호출 되었는지 검사하기 (isInterrupted, interrupted)
		while(true) {
			System.out.println("스레드 처리 중 ...");
			
			//검사방법1 => 스레드의 인스턴스객체용 메서드를 이용하는 방법
			// 인스턴스메서드로 내가 걸렸을때만 바뀌기 때문에 스레드메서드와 다른점.
//			if(this.isInterrupted()) { //interrupt()메서드가 호출되면 true 인터럽트가 걸릴시
//				System.out.println("인스턴스용 isInterrupted()");
//				break;
//			}
			
			//검사방법2 => 스레드의 정적 메서드를 이용하는 방법 
			// 클래스메서드이기 때문에 한번true가 되고 체크하면 다시 false로 돌려논다.  
			if(Thread.interrupted()) {//interrupt()메서드가 호출되면 true 인터럽트가 걸렸는지 확인할 수 있다.
				System.out.println("정적 메서드 interrupted()");
				break;
			}
		}
	}
}
















