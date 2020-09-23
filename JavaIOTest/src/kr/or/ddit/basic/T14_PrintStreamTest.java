package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린트기능 제공 보조 스트림
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		
		//PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브클래스이다.
		//PrintStream은 IOException을 발생시키지 않는다.
		//println 및 print 등 메서드 호출시 마다 autoflush기능이 제공된다.
		
		PrintStream out = new PrintStream(fos); //보조스트림은 기반스트림필요
		out.print("안녕하세요. PrintStream입니다. 오늘 비가 많이 오네요.\n");
		out.println("안녕하세요. PrintStream입니다. 내일은\n");
		out.println("안녕하세요. PrintStream입니다. 안왔으면\n");
		out.println(out); //객체출력
		out.print(3.14);  
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		writer.print("안녕하세요. PrintWriter 입니다.\r\n");
		writer.println("안녕하세요. PrintWriter 입니다.");
		writer.println("안녕하세요. PrintWriter 입니다.");
		
		writer.close();
		
		/*
			PrintStream은 데이터를 문자로 출력하는 기능을 수행한다.(System.out)
			향상된 기능의 PrintWriter가 추가되었지만 계속 사용되고 있음.
			
			PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는데 적합하다. 
		 */
		
	}
}
