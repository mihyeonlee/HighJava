<%@page import="board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
	String msg = (String) request.getAttribute("msg");

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글목록</title>
</head>
<body>
<table border="1"> 
  <tr>
    <td colspan="4"><a href="insert.do">[게시글 작성하기]</a></td>
  </tr>
  
  <tr>
  	<th>글 번호</th>
  	<th>title</th>
  	<th>작성자</th>
  	<th>작성일</th>
  </tr>
<%
	int boSize = boardList.size();
	if(boSize > 0){
		for(int i=0; i<boSize;i++){
%>			
	<tr>
		<td><%= boardList.get(i).getBoard_no() %></td>	
		<td><a href="select.do?board_no=<%= boardList.get(i).getBoard_no() %>"><%= boardList.get(i).getBoard_title() %></a></td>	
		<td><%= boardList.get(i).getBoard_writer() %></td>	
		<td><%= boardList.get(i).getBoard_date() %></td>	
	</tr>
		
		
			
			
<%
		}
	}else{
%>
	<tr>
		<td colspan="4">회원정보가 존재하지 않습니다.</td>
	</tr>
		
<%		
	}
%>			

</table>

</body>
</html>