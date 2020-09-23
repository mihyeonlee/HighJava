package kr.or.ddit.member.dao;

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

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private SqlMapClient smc;
	
	private static IMemberDao dao;
	
	private MemberDaoImpl() {
		Reader rd;
		try {
			// 1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩 설정 (영어만 쓰면 안해도됨)
			Resources.setCharset(charset);
			
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성(SqlMapClient 객체)
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기 
			
		}catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패!");
			e.printStackTrace();
		}
	}
	
	public static IMemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("member.insertMember",mv);
			//xml에서 insert는 성공시 null반환한다.
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		
		try {
			int cnt = (int)smc.queryForObject("member.getMember",memId);
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember",mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember",memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = smc.queryForList("member.getSearchMember",mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return memList;
	}
	

}
