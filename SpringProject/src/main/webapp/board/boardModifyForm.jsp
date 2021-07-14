<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
div#subjectDiv, div#contentDiv {
	color:red;
	font-size: 8pt;
	font-weight: bold;
}
</style>

<h3>글수정</h3>
<form id="boardModifyForm">
<input type="hidden" name="seq" value="${seq }">
<input type="hidden" name="pg" value="${pg }">

<table border="1" cellpadding="5" cellspacing="0">
<tr>
 <td align="center" width="100">제목</td>
 <td>
  <input type="text" name="subject" id="subject" size="50">
  <div id="subjectDiv"></div>
 </td>
</tr>
 
<tr>
 <td align="center">내용</td>
 <td>
  <textarea cols="50" rows="15" name="content" id="content"></textarea>
  <div id="contentDiv"></div>
 </td>
</tr>

<tr>
 <td colspan="2" align="center">
  <input type="button" value="글수정" id="boardModifyBtn">
  <input type="reset"  value="다시작성">
 </td>
</tr>

</table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoard',
		data: 'seq='+$('input[name=seq]').val(), //name 속성의 seq의 값 이란 뜻 / id면 그냥 #만 쓰면 된다
		dataType: 'json',
		success: function(data){
			$('#subject').val(data.boardDTO.subject);
			$('#content').val(data.boardDTO.content);
		},
		error: function(err){
			console.log(err);
		}
		
	});
});

$('#boardModifyBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	
	if($('#subject').val()==''){
		$('#subjectDiv').text('제목을 입력하세요');
		$('#subjectDiv').css('color','gold');
		$('#subjectDiv').css('font-size','8pt');
		$('#subjectDiv').css('font-weight','bold');
		
	}else if($('#content').val()==''){
		$('#contentDiv').text('내용을 입력하세요')
		$('#contentDiv').css('color','gold')
		$('#contentDiv').css('font-size','8pt')
		$('#contentDiv').css('font-weight','bold');
		
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/board/boardModify',
			data: 
				//{
				//'seq': $('input[name=seq]').val(),
				//'subject': $('#subject').val(),
				//'content': $('#content').val()
				//}, //json형태
				
				$('#boardModifyForm').serialize(),
			
			success: function(){
				alert('수정 완료');
				location.href='/spring/board/boardList';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
	
});
</script>