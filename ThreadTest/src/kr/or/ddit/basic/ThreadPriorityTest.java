package kr.or.ddit.basic;

public class ThreadPriorityTest {
	public static void main(String[] args) {
		Thread th1 = new ThreadTest1();
		Thread th2 = new ThreadTest2();
		
		//우선순위는 start()메서드를 호출하기 전에 설정해야한다. 후에 하면 아무효과가 없다 그 전에 세팅해줘야 그렇게 돌아감  근데 잘 안먹힘
		th1.setPriority(10); // 숫자가 높은게 우선순위가 높다. 가장높은게 10
		th2.setPriority(1);  // 가장낮은게 1
		
		System.out.println("th1의 우선순위: "+th1.getPriority());
		System.out.println("th2의 우선순위: "+th2.getPriority());
		
		th1.start();
		th2.start();
		
		try {
			th1.join(); // 스레드가 끝날때까지 메인스레드가 기다린다.
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("최대 우선순위: "+Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위: "+Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위: "+Thread.NORM_PRIORITY);
		
		
	}

}

class ThreadTest1 extends Thread{

	@Override
	public void run() {
		for(char ch='A';ch<='Z';ch++) {
			System.out.println(ch);
			//아무것도 하지 않는 반복문 (시간때우기용)
			for(long i=1;i<1000000000L;i++) {}
		}
	}
	
	
}

class ThreadTest2 extends Thread{
	
	@Override
	public void run() {
		for(char ch='a';ch<='z';ch++) {
			System.out.println(ch);
			//아무것도 하지 않는 반복문 (시간때우기용)
			for(long i=1;i<1000000000L;i++) {}
		}
	}
	
	
}








