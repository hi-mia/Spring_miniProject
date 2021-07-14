<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
div#nameDiv, div#idDiv, div#pwdDiv {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>
 <h3>회원정보수정</h3>
 <form name="modifyForm" id="modifyForm" method="post" action="/spring/member/modify">
 <table border="1" cellpadding="5" cellspacing="0">
  <tr>
   <td align="center" width="100">이름</td>
   <td>
    <input type="text" name="name" id="name" value="${memberDTO.name }">
    <div id="nameDiv"></div>
   </td>
  </tr>
 
  <tr>
   <td align="center">아이디</td>
   <td>
    <input type="text" name="id" id="id" value="${memberDTO.id }" readonly>
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
   <td align="center">재확인</td>
   <td>
    <input type="password" name="repwd" id="repwd">
     <div id="pwdDiv"></div>
   </td>
  </tr>
 
  <tr>
   <td align="center">성별</td>
   <td>
    <input type="radio" name="gender" value="0">남
    <input type="radio" name="gender" value="1">여
   </td>
  </tr>
 
  <tr>
   <td align="center">이메일</td>
   <td>
    <input type="text" name="email1" value="${memberDTO.email1 }">
    @
    <input type="email" list="email2" name="email2" placeholder="직접입력" >
  	 <datalist id="email2">	 
  	  <option value="gmail.com">
  	  <option value="hanmail.net">
  	  <option value="naver.com">   
  	 </datalist>
  	</td>
  </tr>
  
  <tr>
   <td align="center">핸드폰</td>
   <td>
    <select name="tel1" style="width: 70px;">
   	 <option value="010">010</option>
   	 <option value="011">011</option>
   	 <option value="019">019</option>  
   	</select> - 
   	<input type="text" name="tel2" size="5" value="${memberDTO.tel2 }"> - 
   	<input type="text" name="tel3" size="5" value="${memberDTO.tel3 }">
   </td>
  </tr>
   
  <tr>
   <td align="center">주소</td>
   <td>
    <input type="text" name="zipcode" id="zipcode" value="${memberDTO.zipcode }" readonly>
    <input type="button" value="우편번호검색" id="checkPostBtn"><br>
    <input type="text" name="addr1" id="addr1" placeholder="주소" size="50" value="${memberDTO.addr1 }" readonly><br>
    <input type="text" name="addr2" id="addr2" placeholder="상세주소" size="50" value="${memberDTO.addr2 }">
   </td>
  </tr>
   
  <tr>
   <td colspan ="2" align = "center">
    <input type="button" value="회원정보수정" id="modifyBtn">
    <input type="reset"  value="다시작성">
   </td>
  </tr>
 </table>
 </form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script src="/spring/js/member.js"></script>
<script type="text/javascript">
window.onload = function() {
   //document.modifyForm.gender[1].checked = true; // 0은 남자 1은여자로 들어감
   document.modifyForm.gender["${memberDTO.gender}"].checked = true;
   document.modifyForm.email2.value = "${memberDTO.email2}";
   document.modifyForm.tel1.value = "${memberDTO.tel1}";
}

</script>
</body>
</html>
