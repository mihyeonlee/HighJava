package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		//멀티 스레드 프로그램 방식 
		//방법1: Thread클래스를 상속한 class의 인스턴스를 생성한 후 //그럼 그 클래스는 쓰레드클래스가 되고 
		//		이 인스턴스의 start()메서드를 호출한다. // 생성된 객체는 쓰레드 객체가 된다.
		MyThread1 th1 = new MyThread1();
//		th1.start();  //시작 스레드 동작시키기 위해 필수

		//방법2: Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 
		//		이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다. 
		//		이때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
//		th2.start();
		
		//방법3: 익명클래스를 이용하는 방법 (일회성)
		// Runnable 인터페이스를 구현한 익명클래스를 Thread인스턴스를 생성할때 매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i<=200;i++) {
					System.out.print("@");
					
					try {
						// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춤.
						// 시간은 밀리세컨드 단위를 사용한다. 
						// 즉, 1000ms는 1초를 의미한다.
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th1.start();
		th2.start();
		th3.start();
		System.out.println("main메서드 작업 끝");
		
	}
}
// run메서드를 오버라이드 하려고 extends 스레드객체는 실행될때 run을 가장먼저 찾아 실행한다. (리턴타입과 파라미터가 없는 run())
class MyThread1 extends Thread{
	
	@Override
	public void run() {
		for(int i = 1; i<=200;i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다. 
				// 즉, 1000ms는 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// Runnable에도 run()가 반드시 있다. 
class MyThread2 implements Runnable{

	@Override
	public void run() {
		for(int i = 1; i<=200;i++) {
			System.out.print("$");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다. 
				// 즉, 1000ms는 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}








