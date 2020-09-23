package board_dao;

import java.sql.SQLException;
import java.util.List;

import board_vo.BoardVO;

public interface IBoardDao {
	
	/**
	 * 게시글 작성을 위한 메서드
	 * @param bv
	 * @return 게시글 작성 성공 여부
	 * @throws SQLException
	 */
	public Object insertBoard(BoardVO bv) throws SQLException;
	
	/**
	 * 게시글 전체를 출력하는 메서드
	 * @return 작성된 게시글 목록
	 * @throws SQLException
	 */
	public List<BoardVO> getAllBoard() throws SQLException;
	
	/**
	 * 게시글을 검색하는 메서드
	 * @param bv
	 * @return 검색 조건에 해당하는 게시글
	 * @throws SQLException
	 */
	public List<BoardVO> getSearchBoard(BoardVO bv) throws SQLException;
	
	/**
	 * 게시글을 수정하기 위한 메서드
	 * @param bv
	 * @return 게시글 수정 성공 여부
	 * @throws SQLException
	 */
	public int updateBoard(BoardVO bv) throws SQLException;
	
	/**
	 * 게시글을 삭제하기 위한 메서드
	 * @param board_no
	 * @return 게시글 삭제 성공 여부
	 * @throws SQLException
	 */
	public int deleteBoard(int board_no) throws SQLException;
	
}
