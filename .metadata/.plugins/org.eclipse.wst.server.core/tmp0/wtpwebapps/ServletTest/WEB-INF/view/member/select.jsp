<%@page import="kr.or.ddit.cmm.vo.FileVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO memVO = (MemberVO) request.getAttribute("memVO");
	// LineSeparator를 이용하여 <br>태그로 변경함. 
	String memAddr = "";
	
	FileVO fileVO = (FileVO) request.getAttribute("fileVO");
	
	if( memVO.getMem_addr() != null){
		memAddr = memVO.getMem_addr().replaceAll(System.lineSeparator(), "<br>");
	}


%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신규회원 등록</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/updateMember" method="get">
		<table border="1">
		  <tr>
			<td>I D:</td>
			<td><%=memVO.getMem_id()%></td>
		  </tr>
		  	
		  <tr>
			<td>이름:</td>
			<td><%=memVO.getMem_name()%></td>
		  </tr>
		  	
		  <tr>
			<td>전화번호:</td>
			<td><%=memVO.getMem_tel()%></td>
		  </tr>
		  	
		  <tr>
			<td>주소:</td>
			<td><%=memAddr%></textarea></td>
		  </tr>
		  
		  <tr>
			<td>첨부파일:</td>
			<td>
  				<% if(fileVO != null){ %>
					<a  href = "<%=request.getContextPath()%>/filedownload.do?fileId=<%=fileVO.getAtchFileId() %>">
						<%=fileVO.getOrignlFileNm() %>
					</a>
					
				<% } %>  
			
			</td>
		  </tr>
		  
		  
		  <tr>
		  	<td colspan="2">
		  		<a href="list.do">[목록으로]</a>
		  		<a href="updateMember?memId=<%=memVO.getMem_id()%>">[회원정보 수정]</a>
		  		<a href="delete.do?memId=<%=memVO.getMem_id()%>">[회원정보 삭제]</a>
		  	</td>
		  </tr>
		  	
		</table>
		<input type="submit" value="회원수정">
	
	</form>

</body>
</html>