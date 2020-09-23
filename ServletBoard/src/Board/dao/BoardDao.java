package Board.dao;

import java.util.List;

import Board.vo.BoardVO;

public interface BoardDao {
	
	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param bd DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertBoard(BoardVO bd);
	
	/**
	 * DB의 jdbc_board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return BoardVO객체를 담고 있는 List객체
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 하나의 BoardVO 자료를 이용하여 DB를 update하는 메서드
	 * @param bd update할 게시물정보가 들어있는 BoardVO객체
	 * @return 작업성공 : 1, 작업실패  : 0
	 */
	public int updateBoard(BoardVO bd);
	
	/**
	 * 게시물번호를 매개변수로 받아서 그 게시물 정보를 삭제하는 메서드
	 * @param bdNo 삭제할 게시물번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(int bdNo); 
	
	/**
	 * 게시물번호 를 이용해여 게시물이 존재하는지를 알려주는 메서드
	 * @param bdNo
	 * @return true : 이미 존재함, false : 존재하지 않음.
	 */
	public BoardVO getBoard(int bdNo);
	
	/**
	 * BoardVO에 담긴 자료를 이용하여 게시글을 검색하는 메서드
	 * @param bd 검색할 자료가 들어있는 BoardVO 객체
	 * @return 검색된 결괄르 담은 List객체
	 */
//	public List<BoardVO> getSearchBoard(BoardVO bd);


}
