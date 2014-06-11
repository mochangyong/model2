<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    int no = 0;	
    String aa = request.getParameter("no");
    if (aa !=null){
    no = Integer.parseInt(request.getParameter("no"));
  
    %>
    <%} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC 게시판</title>
</head>
<body>
<form name="deleteForm" action="./BoardDeleteAction.bo?no=<%=no %>" method="post" >
<table border="1">
<tr>
	<td>
		<font size="12">글 비밀번호:</font>
	</td>
	<td>
		<input name="board_pass" type ="password"/>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<a href="javascript:deleteForm.submit()">삭제</a>
		&nbsp;&mbsp;
		<a href="javascript:history.go(-1)">돌아가기</a>
	</td>
</tr>


</table>



</form>
</body>
</html>