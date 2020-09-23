package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {
	public static final int DEFAULT_BUFFER_SIZE = 10000;
	
	public static void main(String[] args) throws IOException {
		int port = 8888;
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int nReadSize = 0;
		System.out.println("파일 수신 대기중");
		
		DatagramSocket ds = new DatagramSocket(port);
		FileOutputStream fos = new FileOutputStream("d:/C_Lib/p4.jpg");
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		String str = new String(dp.getData()).trim();
		
		if(str.equals("start")) {
			
			buffer = new byte[DEFAULT_BUFFER_SIZE];
			
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			double startTime = System.currentTimeMillis();
			while(true) {
				ds.receive(dp);
				str = new String(dp.getData()).trim();
				nReadSize = dp.getLength();
				fos.write(dp.getData(),0,nReadSize);
				totalReadBytes += nReadSize;
				
				System.out.println("진행 중 : "+totalReadBytes+"/"+fileSize+"Bytes  ("
						+ (totalReadBytes*100/fileSize)+"%)");
				if(totalReadBytes >= fileSize) {
					break;
				}
			}
			
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime)/1000;
			double transferSpeed = (fileSize / 1000)/ diffTime;
			
			System.out.println("time : "+ diffTime + "초");
			System.out.println("평균 전송 속도 : "+transferSpeed+"KB/s");
			System.out.println("파일 전송 완료");
			
			System.out.println("수신이 완료되었습니다.");
			fos.close();
			ds.close();
			
			
		}else {
			System.out.println("수신처리불가");
			fos.close();
			ds.close();
		}
		
	}

}
