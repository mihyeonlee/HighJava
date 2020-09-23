package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress클래스 => IP주소를 다루기 위한 클래스
		
		// naver사이트의 ip정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("Host Name => "+naverIp.getHostName()); // 호스트이름(머신이름) 또는 IP주소
		System.out.println("Host Address =>"+naverIp.getHostAddress());
		System.out.println();
		
		// 자기자신 컴퓨터의 IP주소 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => "+ localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => "+ localIp.getHostAddress());
		System.out.println();
		
		// ip주소가 여러개인 호스트의 정보 가져오기 
		InetAddress[] naverIps = InetAddress.getAllByName("www.sbs.co.kr");
		for(InetAddress ip : naverIps) {
			System.out.println(ip.toString());
		}
	}
}
