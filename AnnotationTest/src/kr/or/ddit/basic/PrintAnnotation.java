package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 	annotation에 대하여...
 
 프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨것
 (JDK1.5부터 지원)
 주석처럼 프로그램 언어에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공함.
 
** 종류
	1. 표준 애너테이션(주로 컴파일러에게 유용한 정보를 제공하기 위한 애너테이션)
	2. 메타 애너테이션(애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할때 사용하는 애너테이션)
	
** 애너테이션 타입 정의하기
	@interface 애너테이션이름{
		타입요소이름(); // 반환값이 있고 매개변수는 없는 추상메서드의 형태 
		...
	}
	
	애너테이션 요소의 규칙
	1. 요소의 타입은 기본형, String, enum, annotation, class만 허용된다.
	2. ()안에 매개변수를 선언할 수 없다.
	3. 예외를 선언할 수 없다.
	4. 요소의 타입에 타입 매개변수(제너릭타입문자)를 사용할 수 없다. 
		
 */
@Target(ElementType.METHOD) // annotation이 적용가능한 대상을 지정함.(.METHOD)PrintAnnotation(PA)은 메서드에만 사용가능.  어노테이션을 위한 어노테이션을 메타 어노테이션 이라 한다.
@Retention(RetentionPolicy.RUNTIME) // 유지되는 기간을 지정함.(기본:CLASS) PA가 메서드에 붙었을때 유지되는기간
public @interface PrintAnnotation {
	int id = 100; // 상수. static final int id = 100; 자동으로 static final이 붙기때문에 상수가 된다.
	String value() default "-"; // 기본값을 '-'로 지정  default 생략가능 
	int count() default 20; // 기본값을 20으로 지정 
}















