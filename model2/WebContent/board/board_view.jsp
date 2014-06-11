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
</head>
<body>
<!-- 게시판 수정 -->
<table cellpadding ="0" cellspacing="0">
	<tr align ="center" valign="middle">
		<td colspan="5">MVC게시판</td>
	</tr>
	<tr>
		 <td style="font-family:돋음;font-size:12" height="16">
		 	<div align="center">제목&nbsp;&nbsp;</div>
		 </td>
		 <td style="font-family:돋음;font-size:12">
		 	<%=board.getBoard_title()%>
		 </td>
	
	</tr>
	<tr bgcolor ="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
	 	<td style="font-family:돋음;font-size:12">
	 		<div align ="center">내용</div>
	 	</td>
	 	<td style="font-family:돋음;font-size:12">
	 		<table border="0" width="490" height ="250" style="table-layout:fixed">
	 			<tr>
	 				<td style="font-family:돋음;font-size:12">
	 					<%=board.getBoard_cont()%>
	 				</td>
	 			</tr>
	 		</table>
	 	</td>
	 </tr>
	 <tr>
	 	<td style="font-family:돋음;font-size:12">
	 		<div align ="center">첨부파일</div>
	 	</td>
	 	<td style="font-family:돋음;font-size:12">
	 		<%if(!(board.getBoard_file()==null)){ %>
	 		<a href="./boardupload/<%=board.getBoard_file() %>">
	 			<%=board.getBoard_file()%>
	 		</a>
	 		<%} %>
	 	</td>
	 </tr>
	 
	 <tr bgcolor="cccccc">
	 	<td colspan="2" style="height:1px;"></td>
	 </tr>
	 <tr><td colspan="2">&nbsp;</td></tr>
	 
	 <tr align ="center" valign="middle" >
	 	<td colspan="5">
	 		<font size="2">
	 		<a href="./BoardReplyAction.bo?no=<%=board.getBoard_no()%>">
	 		[답변]
	 		</a>&nbsp;&nbsp;
	 		<a href="./BoardModify.bo?no=<%=board.getBoard_no()%>">
	 		[수정]
	 		</a>&nbsp;&nbsp;
	 		<a href="./BoardDelete.bo?no=<%=board.getBoard_no() %>">
	 		[삭제]
	 		</a>&nbsp;&nbsp;
	 		<a hrf="./BoardList.bo">[목록]</a>&nbsp;&nbsp;
	 		</font>
	 	</td>
	</tr>
</table>
</body>
</html>