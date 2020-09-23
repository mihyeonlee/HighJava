package thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Horserace1 {
	static int rankCount = 0;
	static List<Horse> rankList = new ArrayList<Horse>();
	
	public static void main(String[] args) {
		List<Horse> horList = new ArrayList<Horse>();
		horList.add(new Horse("1번말"));
		horList.add(new Horse("2번말"));
		horList.add(new Horse("3번말"));
		horList.add(new Horse("4번말"));
		horList.add(new Horse("5번말"));
		horList.add(new Horse("6번말"));
		horList.add(new Horse("7번말"));
		horList.add(new Horse("8번말"));
		horList.add(new Horse("9번말"));
		horList.add(new Horse("10번말"));

		for(int i=0;i<horList.size();i++) {
			horList.get(i).start();
		}
		
		//일반스레드가 종료될때까지 메인이 기다린다. 
		for(Horse h : horList) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(horList);
		System.out.println("경기 끝 ============================================== ");
		System.out.println("결과  ");
		for(int i=0;i<10;i++) {
			System.out.println(horList.get(i).getRank()+"등 : "+horList.get(i).getHorseName());
			
			
		}
		
		
	}

}

class HorsePositionDisplay extends Thread{
	
	
}




/**
 * 말한마리 경주진행 스레드
 * @author PC-03
 *
 */
class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank = 0;
	private int position = 0;
	
	
	
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Horse(String name) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}
	

	@Override
	public void run() {
		for(int i=0;i<50;i++) {
			StringBuilder track = new StringBuilder("--------------------------------------------------");
			try {
				Thread.sleep((int)(Math.random()*401+100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			track.setCharAt(i,'>');
//			System.out.print(horseName+": ");
//			System.out.println(track.toString());
//			if(i==49) {
//				track.setCharAt(i,'>');
//				System.out.print(name+": ");
//				System.out.print(track.toString());
//			}
		}
		Horserace1.rankCount++;
		rank = Horserace1.rankCount;
//		System.out.println(name+" 도착 "+rank+"등");
		
		
	}

//사용법 숙지하기 
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank, o.getRank());
	}
	
	
}































