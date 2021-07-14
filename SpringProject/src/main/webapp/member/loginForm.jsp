<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<h3>로그인</h3>
<form name="loginForm">
<table border="1" cellpadding="5" cellspacing="0">
	<tr>
	 <td align="center">아이디</td>
	 <td>
	 	<input type="text" name="id" id="id">
	 	<div id="idDiv"></div>
	 </td>
	</tr>
	
	<tr>
	 <td align="center">비밀번호</td>
	 <td>
	 	<input type="password" name="pwd" id="pwd">
	 	<div id="pwdDiv"></div>
	 </td>
	</tr>
	
	<tr>
	 <td colspan="2" align="center">
	  <input type="button" value="로그인" id="loginBtn">
	  <input type="button" value="회원가입" onclick="location.href='/spring/member/writeForm'"> <!-- /spring/member/writeForm 가능 -->
	 </td>
	</tr>
</table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/spring/js/member.js"></script>
</body>
</html>