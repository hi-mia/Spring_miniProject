<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#subjectA:link {color: white; text-decoration: none;}
#subjectA:visited {color: white; text-decoration: none;}
#subjectA:hover {color: pink; text-decoration: underline;}
#subjectA:active {color: white; text-decoration: none;}

#currentPaging {
   color: red;
   text-decoration: underline;
   cursor: pointer;
}

#paging {
   color: white;
   text-decoration: none;
   cursor: pointer;
}


</style>

<body style="background: linear-gradient(to right, #654ea3, #eaafc8); color:white;">
   
   <h3 style="text-align: center; width: 650px;">게시판</h3>
   
   <input type="hidden" id="pg" value="${pg }"> <%-- BoardController로부터 오는 pg값을 받음 --%> 
   <table id="boardListTable" border="1" bordercolor="white" width="720" cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
     <tr>
        <th align="center" width="70">글번호</th>
        <th align="center" width="350">제목</th>
        <th align="center" width="100">작성자</th>
        <th align="center" width="100">작성일</th>
        <th align="center" width="100">조회수</th>
     </tr>
   </table>

<div id="boardPagingDiv" style="float: left; width: 650px; text-align: center">${boardPaging.pagingHTML}</div>
<br><br>


<form id="boardSearchForm">
   <input type="hidden" name="pg" value="1"> <%-- 검색한 내용 1페이지부터 보여주기 위해서 BoardController 보낸다. --%>
   <div style="width: 640px; text-align: center;">
      <select name="searchOption">
         <option value="subject" >제목</option>
         <option value="id">작성자</option>
         </select>
         <input type="search" name="keyword" id= "keyword" value="${keyword }" />
         <input type="button" value="검색" id="boardSearchBtn" />
   </div>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/boardList.js"></script>
<script type="text/javascript">

function boardPaging(pg){
   var keyword = document.getElementById('keyword').value;
   if(keyword=='') {
      location.href = 'boardList?pg='+pg;
   }else{
	   $('input[name=pg]').val(pg); //그 pg로 value값 고정시켜버림 - form 안에 pg의 값이 1로 고정되어 있기 때문에 이 작업 한다
		
		//문제: 아이디 검색 후 [2]페이지 클릭 -> 그 뒤 이름으로 검색하면 [1]페이지로 다시 가는게 아니라 계속 [2]페이지에 머문다 / 2페이지부터 검색 시작
	   //1번
	   $('#boardSearchBtn').trigger('click'); //강제 이벤트 발생 - 트리거 사용	
		//검색 버튼을 눌렀을 때 다시 [1]페이지로 보내주자 / [1]페이지부터 다시 검색하도록 pg를 바꿔주어야 한다
	   $('input[name=pg]').val(1);
	   
	   //2번인 경우
	   // $('#boardSearchBtn').trigger('click', 'research');
		
//      location.href = 'getBoardSearchList?pg='+pg
//           +'&searchOption=${searchOption}&keyword='+encodeURIComponent('${keyword}');
   }
}
   
</script>

</body>
