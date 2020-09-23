<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
</head>
<body>
 <form action="insertBoard.do" method="post">
  <table>
  <tr>
   <td>제 목 :</td>
   <td><input type="text" name="board_title" value=""></td>
  </tr>
  <tr>
   <td>작성자 :</td>
   <td><input type="text" name="board_writer" value=""></td>
  </tr>
  <tr>
   <td>내 용 </td>
   <td><textarea rows="10" cols="50" name="board_content"></textarea></td>
  </tr>
  </table>
  <input type="submit" value="게시글 등록">
 </form>
</body>
</html>