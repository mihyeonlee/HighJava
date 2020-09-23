package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) throws IOException {
		//File객체 만들기 연습
		
		//1. File(String 파일 또는 경로명) 파일을 다루기위해서 file객체를 만든다.
		//	=> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는  '\'를 사용하거나 '/'를 사용할 수 있다.
		
		//File클래스로 file객체 생성 . ()에 작업대상을 적는다. 생성하는게 아니다.
		File file = new File("d:\\D_Other\\test.txt");
		System.out.println("파일명: "+ file.getName()); //file이름
		System.out.println("파일 여부: "+file.isFile()); //file이냐? boolean true는 디렉토리가 아니고 파일일때
		System.out.println("디렉토리(여부): "+ file.isDirectory());
		System.out.println("==========================================");
		
		File file2 = new File("d:/D_Other");
		//File file2 = new File("d:/D_Other/test.txt");
		System.out.print(file2.getName()+ "은");
		if(file2.isFile()){
			System.out.println("파일");
		}else if(file2.isDirectory()) {
			System.out.println("디렉토리 (폴더)");
		}
		System.out.println("------------------------------------------");
		
		//2.new File(File parent, String child)
		// => 'parent' 디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
		File file3= new File(file2, "test.txt");
		System.out.println(file3.getName()+"의 용량크기: " + file3.length()+"bytes");
		
		//3. new File(String parent, String child)
		// 절대경로는 경로위치가 명확하지만 상위폴더를 수정시 하위폴더들이 찾지 못함. 
		// 반대로 상대는 상위경로가 변경되어도 상관없다. 그 상위경로를 기준으로 찾기 때문에 어디에 있던 상관없음 
		File file4 = new File("\\D_Other\\test\\..","test.txt"); // ..은 부모디렉토리 .은 자신(현재디렉토리)
		System.out.println("절대경로(D가바로나옴): "+file4.getAbsolutePath()); //파일위치를 절대적으로 지정해주는 경로
		
		System.out.println("상대경로(생성자에 설정해 준 경로): "+ file4.getPath());  //상대경로 어떤경로를 기준으로 경로를 알려주는것
		System.out.println("표준경로: "+ file4.getCanonicalPath()); //IOException던져줌
		
		String path = T01_FileTest.class.getResource("").getPath();
		System.out.println(path); //현재 클래스의 절대경로를 가져온다.
		
		/*
		   디렉토리(폴더) 만들기
		   (make directory) os에서 생성권한이 잇는곳에만 만들 수 있다. 없으면 그냥 생성이 안됨.
		   1. mkdir()  => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		   			   => 중간의 경로가 모두 미리 만들어져 있어야 한다.
		   2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어 준다.
		   
		   => 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환한다. 
		 */
		File file5 = new File("d:/D_Other/연습용/주영언니");
		if(file5.mkdir()) {
			System.out.println(file5.getName()+"만들기 성공!");
		}else {
			System.out.println(file5.getName()+"만들기 실패!");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/srd"); // 만들어져있어야 성공한다. 
		// mkdirs로 하면 성공!
		if(file6.mkdirs()) {
			System.out.println(file6.getName()+"만들기 성공!");
		}else {
			System.out.println(file6.getName()+"만들기 실패!!!");
		}
		System.out.println();
		
		
		
	}
}
