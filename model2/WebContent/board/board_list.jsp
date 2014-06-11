<%@page import="com.naver.model.Board18Bean"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%
	List boardList=(List)request.getAttribute("boardlist");
	int listcount = ((Integer)request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer)request.getAttribute("page")).intValue();
	int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer)request.getAttribute("startpage")).intValue();
	int endpage = ((Integer)request.getAttribute("endpage")).intValue();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC 게시판</title>
</head>
<body>
	<!-- 게시판 리스트 -->
	<table width="50%" border="0" cellpadding="0" cellspacing="0">
		<%
if(listcount>0){
%>
		<tr align="center" valign="middle">
			<td colspan="4">MVC 게시판</td>
			<td align="right"><font size="2">글 개수 :${listcount}</font></td>
		</tr>
		<tr align="center" valign="middle" bordercolor="#333333">
			<td style="font-family: Tahoma; font-size: 8pt;" width="8%"
				heignt="26">
				<div align="center">번호</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="50%">
				<div align="center">제목</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="14%">
				<div align="center">작성자</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="17%">
				<div align="center">날짜</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="11%">
				<div align="center">조회수</div>
			</td>
		</tr>
		<%
   for(int i = 0; i <boardList.size(); i++){
		Board18Bean bl=(Board18Bean)boardList.get(i);   
   
%>
		<tr align="center" valign="middle" bordercolor="#333333"
			onmouseover="this.style.backgroundColor = 'f8f8f8'"
			onmouseout="this.style.backgroundColor="">
			<td height="23" style="font-family: tahoma; font-size: 10pt;">
			<%=bl.getBoard_no()%>
			</td>

			<td style="font-family: Tahoma; font-size: 10pt;">
				<div align="left">
					<%if(bl.getBoard_re_lev()!=0){ %>
					<%for(int a =0; a<=bl.getBoard_re_lev()*2;a++){ %>
					&nbsp;
					<%} %>
					▶
					<%}else{ %>
					▶
					<%} %>
					<a href ="./BoardDetailAction.bo?no=<%=bl.getBoard_no()%>">
						<%=bl.getBoard_title()%>
						
					</a>
					
				</div>
			</td>
			<td style="font-family: Tahoma; font-size: 10pt;">
				<div align = "center"><%=bl.getBoard_name()%></div>
			</td>
			<td style="font-family: Tahoma; font-size: 10pt;">
				<div align = "center"><%=bl.getDate()%></div>
			</td>
			<td style="font-family: Tahoma; font-size: 10pt;">
				<div align = "center"><%=bl.getBoard_hit()%></div>
			</td>
		</tr>
<%}%>
<tr align="center" height="20">
		<td colspan = "7" style=font-family:Tahoma;font-size:10pt;>
				<%if(nowpage<=1){ %>
				[이전]&nbsp;
				<%}else{ %>
				<a href="./BoardList.bo?page=<%=nowpage-1 %>">[이전]</a>
				<%}%>
				
				
				<% for(int a = startpage; a<=endpage; a++){ 
						if(a==nowpage){%>
						[<%=a %>]
						<%}else{ %>
					<a href="./BoardList.bo?page=<%=a %>">[<%=a %>]</a>
					&nbsp;
					<%} %>
					<%} %>
					
					<%if(nowpage>=maxpage){ %>
				[다음]
			<%}else{ %>
			<a href="./BoardList.bo?page=<%=nowpage+1 %>">[다음]</a>
			<%} %>
			</td>
			</tr>
		<%
}else{
		%>
		<tr align ="center" valign="middle">
		<td colspan="4">MVC게시판</td>
		<td align =right>
		<font size="2">등록된 글이 없습니다.</font>
		</td>
		</tr>
		<%} %>
		<tr align ="right">
		<td colspan ="5">
			<a href ="./BoardWrite.bo">글쓰기</a>
			</td>
			</tr>
	</table>
</body>
</html>