package board_service;

import java.sql.SQLException;
import java.util.List;

import board_vo.BoardVO;

public interface IBoardService {
	
	/**
	 * 게시글 작성을 위한 메서드
	 * @param bv
	 * @return 게시글 작성 성공 여부
	 */
	public Object insertBoard(BoardVO bv);
	
	/**
	 * 게시글 전체를 출력하는 메서드
	 * @return 작성된 게시글 목록
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 게시글을 검색하는 메서드
	 * @param bv
	 * @return 검색 조건에 해당하는 게시글
	 * @throws SQLException
	 */
	public List<BoardVO> getSearchBoard(BoardVO bv);
	
	/**
	 * 게시글을 수정하기 위한 메서드
	 * @param bv
	 * @return 게시글 수정 성공 여부
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시글을 삭제하기 위한 메서드
	 * @param board_no
	 * @return 게시글 삭제 성공 여부
	 */
	public int deleteBoard(int board_no);

}
