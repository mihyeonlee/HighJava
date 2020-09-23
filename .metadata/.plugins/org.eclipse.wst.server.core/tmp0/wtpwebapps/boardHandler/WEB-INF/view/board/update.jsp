<%@page import="board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  BoardVO bv = (BoardVO)request.getAttribute("bv");
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
<style >

</style>
</head>
<body>
	<form action="update.do" method="post">
		<table>
			<tr>
				<td>게시글 번호</td>
				<td><input type="text" name="board_no" value="<%= bv.getBoard_no()%>"></td>
			</tr>
			<tr>
				<td>게시글 제목</td>
				<td><input type="text" name="board_title" value="<%=bv.getBoard_title()%>"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="board_writer" value="<%= bv.getBoard_writer()%>"></td>
			</tr>
			<tr>
				<td>작성일자</td>
				<td><input type="text" name="board_date" value="<%= bv.getBoard_date()%>"></td>
			</tr>
			<tr>
				<td>게시글 내용</td>
				<td><textarea name="board_content" value=""><%= bv.getBoard_content()%></textarea></td>
			</tr>
		</table>
		<br>
	<input type="submit" value="게시글 수정">
	</form>
</body>
</html>