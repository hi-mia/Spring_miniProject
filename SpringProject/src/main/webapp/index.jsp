<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">.
<title>Insert title here</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	height: 100%;
	width: auto;
}

#header {
	width: 1700px;
	height: 10%;
	text-align: center;
}

#container {
	margin: auto;
	width: 1700px;
	height: 500px;
}

#container:after {
	content: '';
	display: block;
	clear: both;
	float: none;
}

#nav {
	margin-left: 10px;
	/* width: 400px; */
	width: 25%;
	height: 100%;
	float: left;
}

#section {
	width: 70%;
	height: 100%;
	float: left;
}

#footer {
	width: 1700px;
	height: 10%;
}


input[type=text], input[type=password] {
   background: transparent; 
   border:0; 
   color:white; 
   outline:none;
}
input[type=button], input[type=reset]{
   background-color: rgba(255,255,255,0.5); 
   border:0; 
   color: #ffffff;  
   font-size:11pt;
}
textarea {
   border:0; 
   background: transparent; 
   color:white; 
   font-size:10pt; 
   outline:none; 
}
::placeholder { 
  color: white;
}

table {
	border-radius: 5px;
	border-color: white;
}
</style>
</head>
<body style="background: linear-gradient(to right, #654ea3, #eaafc8 ); color: white;">
<div id="header">
	<h1>
		<img src="/spring/image/Brown.png" width="70" height="70" alt="브라운"
		onclick="location.href='/spring/index.jsp'" style="cursor: pointer;"> MVC 기반의 미니 프로젝트
	</h1>
	<br>
	<jsp:include page="/main/menu.jsp" />
</div>

<div id="container">
	<div id="nav">
		<jsp:include page="/main/nav.jsp" />
	</div>
	<div id="section">
		<c:if test="${empty display }">
			<h1>
				홈페이지를 방문해주셔서 감사합니다.<br>
				Have a nice day!! <br>
				<img src="/spring/image/main.jpg" width="500" height="500" alt="메인">
			</h1>
		</c:if>
		<c:if test="${not empty display }">
			<jsp:include page="${display }" />
		</c:if>
	</div>
</div>

<div id="footer">
	<p>김미경</p>
</div>
</body>
</html>
