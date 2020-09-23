package kr.or.ddit.structural.decorator;

public class YellowShapedDecorator extends ShapeDecorator{
	
	// 파라미터가 들어가 기본생성자가 아니기 때문에 super(decoratedShape)써줘야함. 따로 호출해야함.
	public YellowShapedDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}
	
	@Override
	public void draw() {
//		if(사용자가 권한이 있다면) {
//			decoratedShape.draw();
//		}
		System.out.println("노란색 경계선을 만들어줌");
	}
}
