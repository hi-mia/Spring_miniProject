<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
div#subjectDiv, div#contentDiv {
	color:red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>
<h3>답글쓰기</h3>
<form id="boardReplyForm">
<input type="hidden" name="pseq" value="${pseq }">
<input type="hidden" name="pg" value="${pg }">
<table border="3" cellpadding="5" cellspacing="0">
<tr>
 <td align="center" width="100">제목</td>
 <td>
  <input type="text" name="subject" id="subject" size="50" placeholder="제목입력">
  <div id="subjectDiv"></div>
 </td>
</tr>
 
<tr>
 <td align="center">내용</td>
 <td>
  <textarea cols="50" rows="15" name="content" id="content" placeholder="내용입력"></textarea>
  <div id="contentDiv"></div>
 </td>
</tr>

<tr>
 <td colspan="2" align="center">
  <input type="button" value="답글쓰기" id="boardReplyBtn">
  <input type="reset"  value="다시작성">
 </td>
</tr>

</table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/board.js"></script>
</body>
</html>