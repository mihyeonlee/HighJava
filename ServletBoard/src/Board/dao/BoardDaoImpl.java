package Board.dao;


import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import Board.service.BoardService;
import Board.service.BoardServiceImpl;
import Board.vo.BoardVO;

public class BoardDaoImpl implements BoardDao{
	
	private SqlMapClient smc;
	
	private static BoardDao dao;
	
	public BoardDaoImpl() {
		Reader rd;
		try {
			// 1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8");	// 설정파일의 인코딩 설정
			Resources.setCharset(charset);
			
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패!");
			e.printStackTrace();
		}
	}
	
	public static BoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertBoard(BoardVO bd) {
		int cnt = 0;
		try {
			Object obj = smc.insert("board.insertBoard",bd);
			if(obj == null) {
				cnt =1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> bdList = new ArrayList<>();
		
		try {
			bdList = smc.queryForList("board.getBoardAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return bdList;
	}

	@Override
	public int updateBoard(BoardVO bd) {
		int cnt =0;
		
		try {
			cnt = smc.update("board.updateBoard",bd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int bdNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard",bdNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public BoardVO getBoard(int bdNo) {
		BoardVO list = new BoardVO();
		
		try {
			list = (BoardVO)smc.queryForObject("board.getBoard",bdNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	@Override
//	public List<BoardVO> getSearchBoard(BoardVO bd) {
//		List<BoardVO> bdList = new ArrayList<>();
//		try {
//			bdList = smc.queryForList("board.getSearchBoard", bd);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return bdList;
//	}

}
