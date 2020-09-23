<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 게시물 작성</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/insertBoard" method="post">
		<table>
			<tr>
				<td>게시물 번호</td>
				<td></td>
			</tr>
			<tr>
				<td>게시물 제목</td>
				<td><input type="text" name="bdTitle"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="bdWriter"></td>
			</tr>
			<tr>
				<td>작성일자</td>
				<td></td>
			</tr>
			<tr>
				<td>게시물 내용</td>
				<td><textarea name="bdContent"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="게시물 등록">
	</form>
</body>
</html>