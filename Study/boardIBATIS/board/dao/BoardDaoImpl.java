package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.omg.PortableServer.IdAssignmentPolicyOperations;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;

public class BoardDaoImpl implements IBoardDao {
	
	private SqlMapClient smc;
	
	private static IBoardDao dao;
	private BoardDaoImpl() {
		Reader rd;
		try {
			// 1-1 xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); 
			Resources.setCharset(charset);
			
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-2 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성(SqlMapClient 객체)
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		}catch (IOException e) {
			System.out.println("객체생성실패");
			e.printStackTrace();
		}
	}
	
	public static IBoardDao getInstance() {
		if(dao==null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("board.insertBoard",bv);
			//xml에서는 성공시 null을 반환한다.
			if(obj == null) {
				cnt = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = smc.queryForList("board.searchBoard",bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard",bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.deleteBoard",board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			boardList = smc.queryForList("board.displayBoardAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public boolean getBoardNO(int board_no) {
		boolean gbn = false;
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("board.getBoardNO",board_no);
			if(cnt>0) {
				gbn = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gbn;
	}
	
	

}
