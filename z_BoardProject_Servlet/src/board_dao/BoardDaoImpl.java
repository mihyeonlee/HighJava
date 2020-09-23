package board_dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board_util.SqlMapClientFactory;
import board_vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private static IBoardDao dao;
	private SqlMapClient smc;
	
	// 싱글톤을 이용한 객체 생성 제한
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}

	@Override
	public Object insertBoard(BoardVO bv) throws SQLException {
		return smc.insert("board.insertBoard", bv);
	}

	@Override
	public List<BoardVO> getAllBoard() throws SQLException {
		return smc.queryForList("board.getAllBoard");
	}

	@Override
	public int updateBoard(BoardVO bv) throws SQLException {
		return smc.update("board.updateBoard", bv);
	}

	@Override
	public int deleteBoard(int board_no) throws SQLException {
		return smc.delete("board.deleteBoard", board_no);
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) throws SQLException {
		return smc.queryForList("board.getSearchBoard", bv);
	}

}
