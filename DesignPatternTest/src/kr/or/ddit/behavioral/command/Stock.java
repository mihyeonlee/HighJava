package kr.or.ddit.behavioral.command;

public class Stock {
	private String name = "ABC";
	private int quantity = 10;
	
	public void buy() {
		System.out.println("주식 [ anme: "+name+", Quantity: "+quantity+"] 구매함.");
	}
	
	public void sell() {
		System.out.println("주식 [ anme: "+name+", Quantity: "+quantity+"] 판매함.");
	}
}
