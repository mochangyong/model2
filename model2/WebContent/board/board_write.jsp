<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC �Խ���</title>
<script language="javascript">
	function addboard(){
			boardform.submit();

	}
</script>
</head>
<body>
<!-- �Խ��� ��� -->
<form action= "./BoardAddAction.bo" method="post"
	enctype="multipart/form-data" name="boardform">
<table cellpadding="0" cellspacing="0">
	<tr align ="center" valign="middle">
			<td colspan ="5">MVC�Խ���</td>
	</tr>
	<tr>
			<td style="font-family:����;font-size:12" height="16">
				<div align="center">�۾���</div>
			</td>
			<td>
			<input name="board_name" type ="text" size ="10" maxlength="10" value =""/>
			</td>
	<tr>
			<td style="font-family:����;font-size:12" height="16">
				<div align="center">��й�ȣ</div>
			</td>	
			<td>
			   <input name="board_pass" type ="password" size ="10" maxlength="10" value = "" />
			 </td>
	</tr>
	<tr>
		<td style="font-family:����;font-size:12" height="16">
			<div align ="center">����</div>
		</td>
		<td>
			<input name="board_title" type ="text" size= "50" maxlength ="100" value ="" />
		</td>
	</tr>
	<tr>
		<td style="font-family:����;font-size:12" height="16">
			<div align ="center" >����</div>
		</td>
		<td>
			<textarea name="board_cont" rows="15" cols="67"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:����;font-size:12" height="16">
			<div align ="center">���� ÷��</div>
		</td>
		<td>
			<input name = "board_file" type = "file" />
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan = "2" stype = "height:1px;">
		</td>
		</tr>
	<tr><td colspan ="2">&nbsp;</td></tr>
	<tr align ="center" valign ="middle">
		<td colspan ="5">
			<a href ="javascript:addboard()">[���]</a>&nbsp;&nbsp;
			<a href = "javascript:history.go(-1)">[�ڷ�]</a>
		</td>
	</tr>
</table>	
	
</form>
</body>
</html>