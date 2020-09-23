package board_service;

import java.sql.SQLException;
import java.util.List;

import board_dao.BoardDaoImpl;
import board_dao.IBoardDao;
import board_vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao dao;
	private static IBoardService service;
	
	// 싱글톤을 이용한 객체 생성 제한
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}

	@Override
	public Object insertBoard(BoardVO bv) {
		try {
			return dao.insertBoard(bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		try {
			return dao.getAllBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int updateBoard(BoardVO bv) {
		try {
			return dao.updateBoard(bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBoard(int board_no) {
		try {
			return dao.deleteBoard(board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		try {
			return dao.getSearchBoard(bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
