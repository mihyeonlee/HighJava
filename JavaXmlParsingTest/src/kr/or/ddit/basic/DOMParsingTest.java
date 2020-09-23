package kr.or.ddit.basic;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParsingTest {
	public void parse() throws Exception {
		try {
			// DOM Document 객체 생성하기 위한 메서드
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청.
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			// XML파일 지정
			String url = getClass().getResource("/kr/or/ddit/basic/book.xml").toExternalForm();
			System.out.println("url => "+url);
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청(DOM Document 객체를 리턴함)
			Document xmlDoc = builder.parse(url);
			
			// DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
			Element root = xmlDoc.getDocumentElement();
			System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());
			
			// 하위 엘리먼트 접근방법1 : getElementsByTagName() 메서드 이용
			NodeList bookNodeList = root.getElementsByTagName("book");
			Node firstBookNode = bookNodeList.item(0); // 첫번째 항목 가져오기
			Element firstBookElement = (Element) firstBookNode;
			
			// 속성 접근방법1 : 엘리먼트 객체의 getAttribute() 메서드 이용
			System.out.println("엘리먼트 객체의 getAttribute()메서드 이용 => "
					+ firstBookElement.getAttribute("isbn"));
			
			// 속성 접근방법2 : 노드객체의 getAttributes()메서드 이용 (속성노드를 접근하기)
			NamedNodeMap nodeMap = firstBookElement.getAttributes(); // NamedNodeMap타입으로 반환하기 때문에
			System.out.println("노드 객체의 getAttributes()메서드 이용 => "
					+ nodeMap.getNamedItem("isbn").getNodeValue());;
			
			
			// 하위 엘리먼트 접근방법2 : 노드객체의 getChildNodes()메서드 이용(속성노드를 접근하기)
			// 노드는 DOM의 가장 상위
			NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
			for(int i=0; i<firstBookChildNodeList.getLength();i++) {
				Node n = firstBookChildNodeList.item(i);
				System.out.println("노드명: " + n.getNodeName()
								+ ", 노드값: " + n.getNodeValue()
								+ ", 노드타입: " + n.getNodeType()
								+ ", 컨텐트값: " + n.getTextContent());
			}
			
			// 엔터키에 해당하는 부분이 읽힐 수 있으므로, getChildNodes()보다는 
			// getElementsByTagName()을 이용해 접근하는게 좋다.
			Node titleNode = firstBookChildNodeList.item(1); // #text노드(공백문자) 때문에 인덱스 값을 1로 설정해야함.
			Element titleElement = (Element)titleNode;
			System.out.println("titleElement.getTagName() => "+ titleElement.getTagName());
			System.out.println("titleElement.getTextContent()"+ titleElement.getTextContent());
			System.out.println("--------------------------------------------------------");
			
			// 전체출력하기
			// 속성값: isbn, kind
			// 엘리먼트 텍스트 값: title, author, price
			for(int i = 0; i < bookNodeList.getLength();i++) {
				Node bookNode = bookNodeList.item(i);
				Element element = (Element) bookNode; // 밑에 메서드가 Element타입이기 때문에 노드로받지 않고 캐스팅하면 편하다
				String isbn = element.getAttribute("isbn");
				String kind = element.getAttribute("kind");
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String author = element.getElementsByTagName("price").item(0).getTextContent();
				String price = element.getElementsByTagName("price").item(0).getTextContent();
				String str = String.format("%8s %10s %20s %10s %8s", 
						isbn, kind, title, author, price);
				System.out.println(str);
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception {
		DOMParsingTest parser = new DOMParsingTest();
		parser.parse();
	}
}
