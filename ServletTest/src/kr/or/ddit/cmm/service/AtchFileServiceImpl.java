package kr.or.ddit.cmm.service;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import kr.or.ddit.cmm.dao.AtchFileDaoImpl;
import kr.or.ddit.cmm.dao.IAtchFileDao;
import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class AtchFileServiceImpl implements IAtchFileService{
	// 다오객체 가져오기
	private IAtchFileDao fileDao;
	
	//싱글톤하기위한 본인멤버객체
	private static IAtchFileService fileService;
	
	//생성자에서 다오객체가져오기
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		return fileService;
	}
	
	@Override
	public FileVO saveAtchFile(FileItem item) throws Exception {
		
		//전체경로를 제외한 파일명만 추출 어딘가 저장되어있는데 그 어딘가에서 경로명 다뜯어내고 파일명만 필요
		String orignFileName = new File(item.getName()).getName();
		long fileSize = item.getSize(); // 파일사이즈
		
		String fileExtension = orignFileName.lastIndexOf(".") < 0 ? "" : orignFileName.substring(orignFileName.lastIndexOf(".")+1);
		String saveFileName = UUID.randomUUID().toString().replace("-", "");
		
		File upload = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!upload.exists()) {
			upload.mkdirs();
		}
		String filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY + File.separator + saveFileName;
		
		File saveFile = new File(filePath);
		
		item.write(saveFile); // 업로드 파일 저장
		
		// 파일 저장 서비스 호출...
		FileVO vo = new FileVO();
		vo.setFileExtsn(fileExtension);
		vo.setFileSize(fileSize);
		vo.setFileStrePath(filePath);
		vo.setOrignlFileNm(orignFileName);
		
		fileDao.insertAtchFile(vo); // 첨부파일 정보 저장 
		
		item.delete(); // 임시 업로드 파일 삭제 
		
		return vo ;
	}

	@Override
	public FileVO select(int atchFileId) throws SQLException {
		
		return fileDao.selectAtchFile(atchFileId);
	}

}
