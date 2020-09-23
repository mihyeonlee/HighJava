<%@page import="board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardVO bv = (BoardVO)request.getAttribute("bv"); 
	
// 	String board_content = "";
// 	if(bv.getBoard_content() != null){
// 		board_content = bv.getBoard_content().replaceAll(System.lineSeparator(), "<br>");
// 	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update.do" method="get">
	  <input type="hidden" name="board_no" value="<%= bv.getBoard_no() %>" >
		<table board="1">
			<tr>
			<td>글 번호:</td>
			<td><%=bv.getBoard_no()%></td>
		  </tr>
		  	
		  <tr>
			<td>제목:</td>
			<td><%=bv.getBoard_title()%></td>
		  </tr>
		  	
		  <tr>
			<td>내용:</td>
			<td><textarea><%=bv.getBoard_content()%></textarea></td>
		  </tr>
		  	
		  <tr>
			<td>작성자:</td>
			<td><%=bv.getBoard_writer()%></td>
		  </tr>
		  <tr>
		  	<td colspan="2">
		  		<a href="list.do">[목록으로]</a>
		  		<a href="update.do?board_no=<%=bv.getBoard_no()%>">[회원정보 수정]</a>
		  		<a href="delete.do?board_no=<%=bv.getBoard_no()%>">[회원정보 삭제]</a>
		  	</td>
		  </tr>
		</table>
		<input type="submit" value="회원수정">
	
	</form>

</body>
</html>