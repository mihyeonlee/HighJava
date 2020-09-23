package kr.or.ddit.behavioral.command;

public class SellStock implements Order{
	
	private Stock abcStock;

	@Override
	public void execute() {
		abcStock.sell();
	}
	
	public SellStock(Stock abcStock) {
		this.abcStock = abcStock;
	}
}
