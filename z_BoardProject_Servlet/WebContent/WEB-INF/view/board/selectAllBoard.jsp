<%@page import="board_vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	// 자바코드 작성
	
	// servlet에서 보낸 결과값 받기 - 속성값은 set에서 설정해준 이름과 동일
	// getAttribute() 메서드는 Object타입으로 반환하기 때문에 캐스팅이 필요하다.
	String msg = (String) request.getAttribute("msg");
	List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");

%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<style>
table{
 /* 선을 하나로 합치기 */
 border-collapse: collapse;
 
 text-align: center;
 
}
</style>
</head>
<body>
<table border="1">
 <tr>
  <td colspan="4">
   <a href="insertBoard.do">[게시글 작성]</a>
  </td>
 </tr>
 <tr>
  <td>순</td>
  <td>제 목</td>
  <td>작 성 자</td>
  <td>작성일자</td>
 </tr>
<%
 // 게시글이 존재하지 않을 경우
 if(boardList.size() == 0 || boardList == null){
%>
	<tr>
	 <td colspan="4">
	   게시글이 존재하지 않습니다.
	 </td>
	</tr>
<%	 
 }else{ // 게시글이 존재할 경우
	 for(int i=0; i<boardList.size(); i++){
		BoardVO bv = boardList.get(i);
%>
		<tr>
		 <td><%=i+1%></td>
		 <td><a href="selectBoard.do?board_no=<%=bv.getBoard_no()%>"><%=bv.getBoard_title() %></a></td>
		 <td><%=bv.getBoard_writer() %></td>
		 <td><%=bv.getBoard_date() %></td>
		</tr>
<%
	 } 
 }
%> 
</table>

<%  
 if(msg.equals("성공")){
%>
 <script>
  alert("정상적으로 처리되었습니다.");
 </script>
<%	 
 }
%>

</body>
</html>