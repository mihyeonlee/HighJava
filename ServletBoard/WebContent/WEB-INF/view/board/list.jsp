<%@page import="Board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%
List<BoardVO> bdList = (List<BoardVO>)request.getAttribute("bdList");
String msg = (String)request.getAttribute("msg");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
</head>
<body>
<table border="1">
	<tr>
		<td colspan="5"><a href="<%= request.getContextPath() %>/insertBoard">[게시판등록]</a></td>
	</tr>
	<tr>
		<td>게시판 번호</td>
		<td>게시판 제목</td>
		<td>작성자</td>
		<td>작성날짜</td>
		<td>게시판 내용</td>
	</tr>	

<%
	int bdSize = bdList.size();
	if(bdSize > 0){
		for(int i=0; i<bdSize; i++){
			
%>
	<tr>
		<td><a href="<%= request.getContextPath() %>/selectBoard?bdNo=<%=bdList.get(i).getBoard_no()%>">
		<%= bdList.get(i).getBoard_no() %></a></td>
		<td><%= bdList.get(i).getBoard_title() %></td>
		<td><%= bdList.get(i).getBoard_writer() %></td>
		<td><%= bdList.get(i).getBoard_date() %></td>
		<td><%= bdList.get(i).getBoard_content() %></td>
	</tr>
<%
		}
	}else{
		
%>
	<tr>
		<td colspan="4">게시물이 존재하지 않습니다.</td>
	</tr>
<%
	}
%>

</table>
<%
	if(msg.equals("성공")){
%>
	<script>
		alert('정상적으로 처리되었습니다.');
	</script>
<%
	}
%>	
	

</body>
</html>