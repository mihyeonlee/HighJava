package kr.or.ddit.structural.decorator;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
		
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapedDecorator(new Circle());
		
		Shape redRectangle = new RedShapedDecorator(new Rectangle());
		
		// 기본 원그리기
		circle.draw();
		
		// 빨간색 경계선을 가진 원그리기
		redCircle.draw();
		
		// 빨간색 경계선을 가진 사각형 그리기
		redRectangle.draw();
		
		Shape yellowCircle = new YellowShapedDecorator(new Circle());
		yellowCircle.draw();
		
	}
}
