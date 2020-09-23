<%@page import="Board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardVO bdVO = (BoardVO) request.getAttribute("bdVO");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 조회</title>
</head>
<body>
	<form action="<%= request.getContextPath()%>/selectBoard" method="get">
		<table border="1">
			<tr>
				<td>게시물 번호</td>
				<td><%= bdVO.getBoard_no() %></td>
			</tr>
			<tr>
				<td>게시물 제목</td>
				<td><%= bdVO.getBoard_title() %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%= bdVO.getBoard_writer() %></td>
			</tr>
			<tr>
				<td>작성일자</td>
				<td><%=bdVO.getBoard_date() %></td>
			</tr>
			<tr>
				<td>게시물 내용</td>
				<td><%= bdVO.getBoard_content() %></td>
			</tr>
		
		</table>
		<a href="selectAllBoard"              >목록으로</a>
		<a href="updateBoard?bdNo=<%= bdVO.getBoard_no() %>">수정</a>		<!--해당 게시물번호를 불러오는것  -->
		<a href="deleteBoard?bdNo=<%= bdVO.getBoard_no() %>">삭제</a>
	</form>

</body>
</html>