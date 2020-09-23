<%@page import="Board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
  BoardVO bdVO = (BoardVO)request.getAttribute("bdVO");
 
 %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
</head>
<body>

	<form action="<%=request.getContextPath()%>/updateBoard" method="post">
		<table>
			<tr>
				<td>게시글 번호</td>
				<td><input type="text" name="bdNo" value="<%= bdVO.getBoard_no()%>"></td>
			</tr>
			<tr>
				<td>게시글 제목</td>
				<td><input type="text" name="bdTitle" value=""></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="bdWriter" value="<%= bdVO.getBoard_writer()%>"></td>
			</tr>
			<tr>
				<td>작성일자</td>
				<td><input type="text" name="bdDate" value="<%= bdVO.getBoard_date()%>"></td>
			</tr>
			<tr>
				<td>게시글 내용</td>
				<td><input type="text" name="bdContent" value=""></td>
			</tr>
		</table>
	<input type="submit" value="게시물등록">
	</form>
</body>
</html>