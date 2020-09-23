package board.service;

import java.util.List;

import board.vo.BoardVO;
/**
 * Service객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 보통 Dao의 메서드와 같은 구조를 같는다.
 * @author PC-03
 *
 */
public interface IBoardService {
	
	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param bv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 게시글번호를 이용하여 DB에서 select하는 메서드 해당하는 자료를 List에 담아서 반환하는 메서드
	 * @param board_no 조회할 조건
	 * @return BoardVO객체를 담고있는 List객체
	 */
	public List<BoardVO> searchBoard(BoardVO bv);
	
	/**
	 * 하나의 Board bv 자료를 이용하여 DB를 update하는 메서드
	 * @param bv update할 게시글 정보가 들어있는 BoardVO객체
	 * @return update성공하면 1, 실패하면 0
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시글번호를 이용해 DB에 있는 게시글정보를 삭제하는 메서드
	 * @param board_no 삭제할 게시글번호
	 * @return 성공하면 1, 실패하면 0
	 */
	public int deleteBoard(int board_no);
	
	/**
	 * DB의 board테이블의 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return BoardVO객체를 담고있는 List객체
	 */
	public List<BoardVO> displayBoardAll();

	/**
	 * 주어진 글번호가 DB에 존재하는지 여부를 알아내는 메서드
	 * @param board_no 검색할 게시글 번호
	 * @return 존재하면 true, 없으면 false
	 */
	public boolean getBoardNO(int board_no);
		

}
