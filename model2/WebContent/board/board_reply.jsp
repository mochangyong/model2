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
<title>MVC �Խ���</title>
<script language="javascript">

		function replyboard(){
			boardform.submit();
		}
</script>

</head>
<body>
<!-- �Խ��� �亯 -->
<form action="./BoardReplyAction.bo" method="post" name="boardform">
<input type ="hidden" name="board_no" value="<%=board.getBoard_no() %>" />
<input type ="hidden" name="board_re_ref" value="<%=board.getBoard_re_ref() %>" />
<input type ="hidden"  name="board_re_lev" value="<%=board.getBoard_re_lev() %>" />
<input type ="hidden" name="board_re_seq" value="<%=board.getBoard_re_seq() %>" />

<table cellpadding ="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC�Խ���</td>
	</tr>
	<tr>
		<td height="16" style="font-family:����; font-size:12">
			<div align="center">�۾���</div>
		</td>
		<td>
			<input name ="board_name" type="text"/>
		</td>
	</tr>
	<tr>
		<td height="16" style="font-family:����; font-size:12">
			<div align="center">����</div>
		</td>
		<td>
			<input type="text" name="board_title" size="50" maxlength="100"
						value="Re:<%=board.getBoard_title() %>" />
		</td>
	</tr>
	<tr>
		<td style="font-family:����; font-size:12">
			<div align="center">����</div>
		</td>
		<td>
			<textarea rows="67" cols="15" name="board_cont"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:����; font-size:12">
			<div align ="center">��й�ȣ</div>
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
			<a href ="javascript:replyboard()">[���]</a>&nbsp;&nbsp;
			<a href = "javascript:history.go(-1)">[�ڷ�]</a>
		</td>
	</tr>
</table>

</form>
</body>
</html>