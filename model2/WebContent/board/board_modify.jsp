<%@page import="com.naver.model.Board18Bean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%
    		Board18Bean board =(Board18Bean)request.getAttribute("boarddata");
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC 게시판</title>
<script type="test/javascript">
	function modifyboard(){
modifyform.submit();
}
</script>
</head>
<body>
<!-- 게시판 수정 -->
<form action="BoardModifyAction.bo" method="post" name="midifyform">
<input type = "hidden" name="board_no" value=<%=board.getBoard_no() %> />
<table cellpadding="0" cellspacing="0">
	<tr align ="center" valign="middle">
		<td colspan="5">MVC게시판</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align ="center">제목</div>
		</td>
		<td>
			<input name="board_title" size="50" maxlength="100"
					value=<%=board.getBoard_title()%> />
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내용</div>
		</td>
		<td>
			<textarea rows="15" cols="67" name="board_cont"><%=board.getBoard_cont() %></textarea>
		</td>
	</tr>
	<%if(!(board.getBoard_file()== null)){%>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			&nbsp;&nbsp;<%=board.getBoard_file()%>
		</td>
	</tr>
	<%} %>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input type ="password" name ="board_pass"/>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align ="center" valign ="middle">
		<td colspan ="5">
			<font size=2>
			<a href ="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href = "javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;
			</font>
	</td>
	</tr>

		

	
	
</table>
</form>
</body>
</html>