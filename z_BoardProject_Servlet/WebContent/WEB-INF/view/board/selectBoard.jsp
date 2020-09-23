<%@page import="board_vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardVO bv = (BoardVO) request.getAttribute("boardList");

	// 줄 바꿈 처리를 해준다.
	String cont = bv.getBoard_content().replaceAll(System.lineSeparator(), "<br>");

%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 내용 보기</title>
</head>
 <form action="updateBoard.do" method="get">
  <table border="1">
  <tr>
   <td>제 목</td>
   <td><%=bv.getBoard_title()%></td>
  </tr>
  <tr>
   <td>작 성 자</td>
   <td><%=bv.getBoard_writer()%></td>
  </tr>
  <tr>
   <td>작성일자</td>
   <td><%=bv.getBoard_date()%></td>
  </tr>
  <tr>
   <td>내 용</td>
   <td><%=cont%></td>
  </tr>
  <tr>
  	<td colspan="2">
  	 <a href="selectAllBoard.do">[목록으로]</a>
  	 <a href="updateBoard.do?board_no=<%=bv.getBoard_no()%>">[수정]</a>
  	 <a href="deleteBoard.do?board_no=<%=bv.getBoard_no()%>">[삭제]</a>
  	</td>
  </tr>
  </table>
 </form>
<body>

</body>
</html>