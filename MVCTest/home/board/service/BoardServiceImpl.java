package board.service;

import java.util.List;

import board.dao.BoardDaoImpl;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	private IBoardDao dao = new BoardDaoImpl();
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = dao.insertBoard(bv);
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = dao.searchBoard(bv);
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = dao.updateBoard(bv);
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		int cnt = dao.deleteBoard(board_no);
		return cnt;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> boardList = dao.displayBoardAll();
		return boardList;
	}

	@Override
	public boolean getBoardNO(int board_no) {
		boolean gbn = dao.getBoardNO(board_no);
		return gbn;
	}

	
	
	

}
