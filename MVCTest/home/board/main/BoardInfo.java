package board.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class BoardInfo {
	public static void main(String[] args) {
		BoardInfo bi = new BoardInfo();
		bi.start();
	}
	
	
	//스캐너객체
	private Scanner scan;
	
	//Service객체 변수 선언.
	private IBoardService service;
	
	public BoardInfo() {
		scan = new Scanner(System.in);
		service = new BoardServiceImpl();
	}
	
	/**
	 * 메뉴를 출력하기 위한 메서드
	 */
	private void displayMenu() {
		System.out.println();
		System.out.println("==========================================");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새게시글 작성");
		System.out.println("  2. 게시글 검색");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 삭제");
		System.out.println("  5. 전체 게시글 출력");
		System.out.println("  6. 작업 끝.");
		System.out.println("==========================================");
		System.out.print("원하는 작업 선택 >> ");
		System.out.println();
		
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	private void start() {
		int choice;
		do {
			displayMenu(); //전체메뉴 출력
			choice = scan.nextInt();
			switch(choice) {
			case 1: 
				insertBoard();
				break;
			case 2: 
				searchBoard();
				break;
			case 3: 
				updateBoard();
				break;
			case 4: 
				deleteBoard();
				break;
			case 5: 
				displayBoardAll();
				break;
			case 6: 
				System.out.println("작업을 종료합니다. 띠로리리");
				break;
			default:
				System.out.println("목록에 있는 번호만 입력해 주세요.");
				break;
				
			}
		}while(choice!=6);
	}
	
	
	
	
	/**
	 * 새로운 게시글을 추가하기 위한 메서드
	 */
	private void insertBoard() {
		
		scan.nextLine();
		System.out.print("제목 >> ");
		String board_title = scan.nextLine().trim();
		
		System.out.print("작성자 >> ");
		String board_writer = scan.next().trim();
		
		
		scan.nextLine();//입력버퍼비우기
		System.out.println("내용 >> ");
		String board_content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_content(board_content);
		
		int isb = service.insertBoard(bv);
		
		System.out.println("------------------------------------------");
		if(isb>0) {
			System.out.println("글 작성 성공!");
		}else {
			System.out.println("글 작성 실패");
		}
	}

	/**
	 * 게시글 번호로 해당 게시글정보를 조회하는 메서드
	 */
	private void searchBoard() {
		while(true) {
			System.out.println("------------------------------------------");
			System.out.println("검색할 게시글번호를 입력하세요.");
			System.out.println("------------------------------------------");
			System.out.println("게시글 번호 >> ");
			int board_no = scan.nextInt();
			
			boolean gbn = service.getBoardNO(board_no);
			
			if(gbn==false) {
				System.out.println("해당하는 게시글번호가 없습니다. 다시입력해주세요.");
				System.out.println();
				continue;
			}
			
			scan.nextLine();
			
			System.out.print("제목 >> ");
			String board_title = scan.nextLine().trim();
			System.out.print("작성자 >> ");
			String board_writer = scan.nextLine().trim();
			System.out.print("내용 >> ");
			String board_content = scan.nextLine().trim();
			
			BoardVO bv = new BoardVO();
			bv.setBoard_no(board_no);
			bv.setBoard_title(board_title);
			bv.setBoard_writer(board_writer);
			bv.setBoard_content(board_content);
			
			List<BoardVO> boardList = service.searchBoard(bv);
			for(BoardVO bv2 : boardList) {
				System.out.println("==========================================");
				System.out.print(bv2.getBoard_no()+" . ");
				System.out.println(bv2.getBoard_title());
				System.out.println("------------------------------------------");
				System.out.println("작성자 : "+bv2.getBoard_writer());
				System.out.println("내용 : "+bv2.getBoard_content());
				System.out.println("작성일 : "+bv2.getBoard_date());
			}
			break;
		}
		System.out.println("==========================================");
		System.out.println("<끝>");
	}

	private void updateBoard() {
		System.out.println();
		int board_no = 0;
		boolean gbn = false;
		while(true) {
			System.out.println("수정할 게시글 번호를 입력하세요.");
			board_no = scan.nextInt();
			
			
			gbn = service.getBoardNO(board_no);
			
			if(!gbn) {
				System.out.println("수정할 자료가 없으니 다시 입력해주세요.");
				System.out.println();
				continue;
			}
			break;
		}
			scan.nextLine(); //버퍼비우기
			System.out.println("수정할 내용을 입력해 주세요.");
			System.out.print("새로운 제목 >> ");
			String board_title = scan.nextLine();
			System.out.println("새로운 내용 >> ");
			String board_content = scan.nextLine();
			
			BoardVO bv = new BoardVO();
			bv.setBoard_no(board_no);
			bv.setBoard_title(board_title);
			bv.setBoard_content(board_content);
			
			int cnt = service.updateBoard(bv);
			System.out.println("------------------------------------------");
			if(cnt>0) {
				System.out.println("게시글 수정 완료!");
			}else {
				System.out.println("게시글 수정 실패!");
			}
	}

	private void deleteBoard() {
		System.out.println();
		int board_no = 0;
		boolean gbn = false;
		
		do {
			System.out.println("삭제할 글번호를 입력하세요.");
			board_no = scan.nextInt();
			
			gbn = getBoardNO(board_no);
			
			if(gbn==false) {
				System.out.println("삭제할 게시글 번호가 없습니다.");
				System.out.println("삭제할 게시글이 없으니 다시 입력해주세요.");
			}
		} while (gbn==false);
		
		int cnt = service.deleteBoard(board_no);
		
		if(cnt>0) {
			System.out.println("게시글삭제 성공");
		}else {
			System.out.println("게시글 삭제 실패");
		}
		
	}

	private void displayBoardAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		boardList = service.displayBoardAll();
		
		for(BoardVO bv2 : boardList) {
			System.out.println("==========================================");
			System.out.print(bv2.getBoard_no()+" . ");
			System.out.println(bv2.getBoard_title());
			System.out.println("------------------------------------------");
			System.out.println("작성자 : "+bv2.getBoard_writer());
			System.out.println("------------------------------------------");
			System.out.println("내용 : "+bv2.getBoard_content());
			System.out.println("작성일 : "+bv2.getBoard_date());
		}
		System.out.println("==========================================");
		System.out.println("<끝>");
		
	}
	
	private boolean getBoardNO(int board_no) {
		boolean gbn = false;
		
		gbn = service.getBoardNO(board_no);
		
		return gbn;
	}

	
	

}
