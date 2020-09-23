package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;

public class BoardDaoImpl implements IBoardDao {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;

	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		if(rs!=null)try {rs.close();}catch(SQLException e) {}
		if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
		if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		if(conn!=null)try {conn.close();}catch(SQLException e) {}
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into board(board_no ,board_title , board_writer, board_date, board_content)"
					+ " values(board_seq.nextVal,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			//sql문에 값을 넣어준다. 
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("추가작업실패");
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from board where 1 =1 ";
			if(Integer.toString(bv.getBoard_no()) != null && !Integer.toString(bv.getBoard_no()).equals("")) {
				sql += " and board_no = ? ";
			}
			if(bv.getBoard_title()!=null && !bv.getBoard_title().equals("")) {
				sql += " and board_title like '%' || ? ||'%'";
			}
			if(bv.getBoard_writer()!=null && !bv.getBoard_writer().equals("")) {
				sql += " and board_writer = ? ";
			}
			if(bv.getBoard_date()!=null && !bv.getBoard_date().equals("")) {
				sql += " and board_date = ? ";
			}
			if(bv.getBoard_content()!=null && !bv.getBoard_content().equals("")) {
				sql += " and board_content like '%'||?||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			
			if(Integer.toString(bv.getBoard_no()) != null && !Integer.toString(bv.getBoard_no()).equals("")) {
				pstmt.setInt(index++, bv.getBoard_no());
			}
			if(bv.getBoard_title()!=null && !bv.getBoard_title().equals("")) {
				pstmt.setString(index++, bv.getBoard_title());
			}
			if(bv.getBoard_writer()!=null && !bv.getBoard_writer().equals("")) {
				pstmt.setString(index++, bv.getBoard_writer());
			}
			if(bv.getBoard_date()!=null && !bv.getBoard_date().equals("")) {
				pstmt.setString(index++, bv.getBoard_date());
			}
			if(bv.getBoard_content()!=null && !bv.getBoard_content().equals("")) {
				pstmt.setString(index++, bv.getBoard_content());
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				
				BoardVO bv2 = new BoardVO();
				bv2.setBoard_no(board_no);
				bv2.setBoard_title(board_title);
				bv2.setBoard_writer(board_writer);
				bv2.setBoard_date(board_date);
				bv2.setBoard_content(board_content);
				
				boardList.add(bv2);
			}
			
		}catch (SQLException  e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update board "
					+ " set board_title = ? "
					+ " ,board_date = sysdate "
					+ " ,board_content = ? "
					+ " where BOARD_NO = ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_content());
			pstmt.setInt(3, bv.getBoard_no());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("수정실패");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("삭제실패");
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from board";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				
				BoardVO bv = new BoardVO();
				bv.setBoard_no(board_no);
				bv.setBoard_title(board_title);
				bv.setBoard_writer(board_writer);
				bv.setBoard_date(board_date);
				bv.setBoard_content(board_content);
				
				boardList.add(bv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return boardList;
	}

	@Override
	public boolean getBoardNO(int board_no) {
		boolean gbn = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(*) as cnt from board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt>0) {
				gbn= true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gbn;
	}
	
	

}
