package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleHttpServer {
	
	private static final Logger LOGGER = Logger.getLogger("SimpleHttpServer");
	
	private final byte[] content;
	private final byte[] header;
	private final int port;
	private final String encoding;
	
	public SimpleHttpServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
		
		this(data.getBytes(encoding), encoding, mimeType, port);
		
	}
	
	public SimpleHttpServer(byte[] data, String encoding, String mimeType, int port) {
		this.content = data;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n" // status라인
				+ "Server: TestServer 1.0\r\n"
				+ "Content-length: "+ this.content.length + "\r\n"
				+ "Content-type: "+ mimeType + "; charset="+ encoding +"\r\n\r\n";
		this.header = header.getBytes(Charset.forName("US-ASCII"));
	}
	
	public void start() {
//		ServerSocket server = null;  이 과정을 try(요안에 넣어서 해결할 수 있음) 자원 close기능까지해줌 try with resource
		try(ServerSocket server = new ServerSocket(this.port)) {
//			server = new ServerSocket(this.port);
			LOGGER.info("접속 요청 대기 중... : 포트번호 => "+ server.getLocalPort());
			LOGGER.info("전송 데이터: ");
			LOGGER.info(new String(this.content,encoding));
			
			while (true) {
				try {
					Socket socket = server.accept();
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start();
				} catch (IOException ex) {
					LOGGER.log(Level.WARNING,"접속 대기중 예외발생함",ex);
				}catch (RuntimeException ex) {
					LOGGER.log(Level.SEVERE,"알수없는 에러발생", ex);
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "서버 구동 실패!", e);
		}
	}
	
	private class HttpHandler implements Runnable{
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				OutputStream out = new BufferedOutputStream(socket.getOutputStream());
				BufferedReader br = new BufferedReader(
										new InputStreamReader(
												socket.getInputStream()));
				
				// + 로 String을 붙이면 느리다. 이거 해결해주는 클래스 StringBuilder 메서드 append
				StringBuilder request = new StringBuilder();
				
				while (true) {
					String str = br.readLine();
					
					if(str.equals("")) break; // 빈줄을 만나면  중지한다.
					
					request.append(str + "\n");
				}
				
				System.out.println("요청헤더정보:\n"+request.toString());
				
				// HTTP1.0 이나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				if(request.toString().indexOf("HTTP/") != -1) {
					out.write(header);
				}
				
				System.out.println("응답헤더:\n" + new String(header));
				
				out.write(content);
				out.flush();
				
			} catch (IOException ex) {
				LOGGER.log(Level.WARNING, "클라이언트에 전송중 에러발생", ex);
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // 소켓 닫기
			}
			
		}
		
	}
	
	// args[] => 사용자가 입력한 정보. 
	public static void main(String[] args) {
		// 대기(listen)할 포트번호를 설정한다.
		int port;
		try {
			port = Integer.parseInt(args[1]);  // args[1] => port번호는 정수 
			
			if(port < 1 || port > 65535) port = 80;  
		}catch (RuntimeException ex) {
			port = 80; // 기본값
		}
		
		String encoding = "UTF-8"; // Default
		
		if(args.length>0) encoding = args[2]; // 따로 입력한 인코딩 정보가 있으면 변경
		
		FileInputStream fis = null;
		
		try {
			File file = new File(args[0]);  // args[0] => 파일명
			byte[] data = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			fis.read(data);
			
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]); // 파일명을 써주면 MIME타입을 가져온다. Ex) html/xml
			SimpleHttpServer server = new SimpleHttpServer(data, encoding, contentType, port);
			server.start(); // 서버시작
		}catch(ArrayIndexOutOfBoundsException ex ) {
			System.out.println("Usage: java SimpleHTTPServer filename port encoding"); // args[0]: filename [1]: port [2]: encoding
			
		}catch(IOException ex) {
			LOGGER.severe("IO 에러: "+ex.getMessage());
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
