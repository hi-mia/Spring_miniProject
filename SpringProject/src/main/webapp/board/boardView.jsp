<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">
</style>
<form name="boardViewForm">
<input type="hidden" name="seq" id="seq" value="${seq }"> <!-- 원글번호 -->
<input type="hidden" name="pg" id="pg" value="${pg }">

<table border="1" width="450" frame="hsides" rules="rows" cellpadding="5">
	<tr>
		<td colspan="3"><h3><span id="subjectSpan"></span></h3></td>
	</tr>
	
	<tr>
		<td width="150">글번호 : <span id="seqSpan"></span></td>
		<td width="150">작성자 : <span id="idSpan"></span></td>
		<td width="150">조회수 : <span id="hitSpan"></span></td>
	</tr>
	
	<!-- 
	- 엔터를 안치고 글을 옆으로 길게 쓴 경우
	- 엔터를 계속 쳐서 글이 밑으로 길게 쓴 경우
	 -->
	<tr>
		<td colspan="3" height="300" valign="top">
			<div style="width: 100%; height: 100%; overflow: auto;">
				<pre style="white-space: pre-line; word-break: break-all;">
					<span id="contentSpan"></span>
				</pre>
			</div>
		</td>
	</tr>
</table>

<br>
<input type="button" value="목록" onclick="location.href='boardList.do?pg=${pg}'">

<span id="boardViewSpan">
	<input type="button" value="글수정" onclick="mode(1)">    
	<input type="button" value="글삭제" onclick="mode(2)">
</span>

<input type="button" value="답글" onclick="mode(3)">
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$.ajax({
		type:'post',
		url: '/spring/board/getBoard', //한 사람거 가져와서 getBoard 가져옴
		data: 'seq='+$('#seq').val(),
		dataType:'json',
		success:function(data){
			//console.log(data);
			
			$('#subjectSpan').text(data.boardDTO.subject);
			$('#seqSpan').text(data.boardDTO.seq);
			$('#idSpan').text(data.boardDTO.id);
			$('#hitSpan').text(data.boardDTO.hit);
			$('#contentSpan').text(data.boardDTO.content);
			
			if(data.memId == data.boardDTO.id)
				$('#boardViewSpan').show();
			else
				$('#boardViewSpan').hide();
		},
		error: function(err){
			console.log(err);
		}
	});
});
</script>

<script type="text/javascript">
function mode(num){
	if(num==1){//글수정
		document.boardViewForm.method="post";
		document.boardViewForm.action="boardModifyForm"; //.do 있어도 되고 없어도 됨.. '/'로 이동하기 때문
		document.boardViewForm.submit();
		
	}else if(num==2){//글삭제
		if(confirm("정말로 삭제하시겠습니까?")){
			document.boardViewForm.method="post";
			document.boardViewForm.action="boardDelete";
			document.boardViewForm.submit();
		}
		
	}else if(num==3){//답글
		document.boardViewForm.method="post";
		document.boardViewForm.action="boardReplyForm";
		document.boardViewForm.submit();
	}
	
}
</script>
