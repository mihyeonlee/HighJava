package Board.service;

import java.util.List;

import Board.dao.BoardDao;
import Board.dao.BoardDaoImpl;
import Board.vo.BoardVO;

public class BoardServiceImpl implements BoardService{
	
	private BoardDao dao;	// 사용할 Dao의 객체변수를 선언
	private static BoardServiceImpl service;
	public BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}

	public static BoardServiceImpl getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}

	@Override
	public int insertBoard(BoardVO bd) {
		return dao.insertBoard(bd);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public int updateBoard(BoardVO bd) {
		return dao.updateBoard(bd);
	}

	@Override
	public int deleteBoard(int bdNo) {
		return dao.deleteBoard(bdNo);
	}

	@Override
	public BoardVO getBoard(int bdNo) {
		return dao.getBoard(bdNo);
	}

//	@Override
//	public List<BoardVO> getSearchBoard(BoardVO bd) {
//		return dao.getSearchBoard(bd);
//	}



}
