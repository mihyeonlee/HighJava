package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt; // 
	private ResultSet rs;
	
	
	
	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		//6. 종료(사용했던 자원을 모두 반납한다.)
		if(rs!=null)try {rs.close();}catch (SQLException e) { }
		if(stmt!=null)try {stmt.close();}catch (SQLException e) { }
		if(pstmt!=null)try {pstmt.close();}catch (SQLException e) { }
		if(conn!=null)try {conn.close();}catch (SQLException e) { }
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil2.getConnection();
			String sql = "insert into mymember(mem_id, mem_name, mem_tel, mem_addr)"
					+ " values(?,?,?,?)"; // 공백처리 잘해야한다. 
			pstmt = conn.prepareStatement(sql); // sql을 파라미터로 가져가기 때문에 sql문작성되어있어야함.
			// sql문안에 있는 물음표에 값을 넣어준다.
			pstmt.setString(1, mv.getMem_id());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("회원 추가작업 실패");
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from mymember"
					+" where mem_id = ?"; // ?를 이용해 틀만 만들어 놓고 나중에 쿼리를 만드는 시점에 값만 바꿔넣는다. 
										// 코드재활용가능.
			
			// prepareStatement()는 객체를 만드는 시점에 쿼리가 준비되어있어야한다. statement()와 차이점.
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			//sql문의 결과를 cnt에 담는다.
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt >0) {
				chk = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		}finally {
			disconnect();
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//반복문 안에서 가져온 레코드 하나 하나를 VO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()) {
				
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv = new MemberVO();
				mv.setMem_id(memId);
				mv.setMem_name(memName);
				mv.setMem_tel(memTel);
				mv.setMem_addr(memAddr);
				
				memList.add(mv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update mymember \r\n " + 
					" set mem_name= ? \r\n " + 
					"   ,mem_tel = ? \r\n " + 
					"   ,mem_addr = ? \r\n " + 
					"  where MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_tel());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(memId+"회원정보 삭제 실패");
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

}
