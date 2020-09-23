package kr.or.ddit.basic;
/*
 	yield()메서드에 대하여
   1. 현재 실행 대기중인 동등한 우선순위의 다른 스레드에게 실행기회를 제공한다. (양보)
   2. 현재 실행중인 스레드의 상태를 Runnable상태로 바꾼다.(Waiting이나 Blocked 상태로 바뀌지 않는다.)
   3. yield()메서드를 실행한다고 해서 현재 실행중인 스레드가 곧바로 Runnable상태로 전이된다고 확신할 수 없다. 
 */
public class T12_ThreadYieldTest {
	public static void main(String[] args) {
		ThreadYield th1 = new ThreadYield("1번스레드");
		ThreadYield th2 = new ThreadYield("2번스레드");
		
		th1.start();
		th2.start();
//		th1.stop();  //권고하지 않음
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th1.work = false; //양보시작
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) { //sleep하는 시간동안 Interrupt가 발생할 수 있다. 이렇게 반드시 처리해야할예외 checked예외 
			e.printStackTrace();
		}
		
		th1.work = true; //양보끝
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th1.stop = true;
		th2.stop = true;
	}
}

// yield()메서드 연습용 스레드
class ThreadYield extends Thread{
	public boolean stop = false; //반복문을 제어할 목적으로 만든 변수
	public boolean work = true; //작업실행을 제어할 목적으로 만든 변수 
	
	public ThreadYield(String name) {
		//스레드에는 기본적으로 name속성이 있고, Thread 생성자 중에서는 
		// name값을 매개변수로 받아서 설정하는 생성자가 있다. 
		super(name); //스레드의 이름 설정
	}
	
	@Override
	public void run() {
		while(!stop) {// stop변수값이 true이면 반복을 멈춘다.
			if(work) {
				//작업하는 영역
				//getName()=> 현재 스레드의 name값 반환 
				System.out.println(getName()+"작업 중");
			}else {
				System.out.println(getName()+"작업양보");
				Thread.yield();
			}
		}
		System.out.println(getName()+"작업 끝 ");
	}
}


















