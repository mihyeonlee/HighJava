<%@page import="board_vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardVO bv = (BoardVO) request.getAttribute("boardList");

	//줄 바꿈 처리를 해준다.
	String cont = bv.getBoard_content().replaceAll(System.lineSeparator(), "\r\n");
	
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
</head>
<body>
<form action="updateBoard.do" method="post">
<input type="hidden" name="board_no" value="<%=bv.getBoard_no() %>">
  <table>
  <tr>
   <td>제 목 :</td>
   <td><input type="text" name="board_title" value="<%=bv.getBoard_title()%>"></td>
  </tr>
  <tr>
   <td>작성자 :</td>
   <td><input type="text" name="board_writer" value="<%=bv.getBoard_writer()%>"></td>
  </tr>
  <tr>
   <td>내 용 </td>
   <td><textarea name="board_content" rows="10" cols="50" value="<%=cont%>"><%=cont%></textarea></td>
  </tr>
  </table>
  <input type="submit" value="수정">
</form>
</body>
</html>