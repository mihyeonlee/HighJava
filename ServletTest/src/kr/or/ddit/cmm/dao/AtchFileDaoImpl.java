package kr.or.ddit.cmm.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AtchFileDaoImpl implements IAtchFileDao{

	private static IAtchFileDao fileDao;
	private SqlMapClient smc;
	// 유틸에 만들어놓은 sqlmapclient객체 가져오기
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		return fileDao;
	}
	
	@Override
	public int insertAtchFile(FileVO fileVO) throws SQLException {
		Object obj = smc.insert("atchFile.insertAtchFile", fileVO);
		
		int cnt = 0;
		if(obj == null) {
			cnt = 1;
		}
		return 0;
	}

	@Override
	public FileVO selectAtchFile(int atchFileId) throws SQLException {
		FileVO fv = (FileVO) smc.queryForObject("atchFile.selectAtchFile", atchFileId);
		
		
		return fv;
	}

}
