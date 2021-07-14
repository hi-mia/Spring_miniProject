<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.space {
	 white-space : normal;
	 word-break: break-all;
}
</style>

<form name="boardViewForm">
<input type="hidden" name="seq" value= "${seq}"> <!-- 컨트롤러로부터 pg가 넘어온다.. boardDTO에서 넘어오는 게 아님 -->
<input type="hidden" name="pg" value="${pg}">

<table border="1" width="450" frame="hsides" rules="rows" cellpadding="5" style="margin-bottom: 3px;">
	
	<tr>
		<td rowspan="4">
			<img id="image1" width="200" height="200">
		</td>
		<td width="150">상품명 : <span id="imageNameSpan"></span></td>
	</tr>

	<tr>	
		<td width="250">단가 : <span id="imagePriceSpan"></span></td>
	</tr>
	<tr>
		<td width="250">개수 : <span id="imageQtySpan"></span></td>
	</tr>
	<tr>
		<td width="250">합계 : <span id="totalSpan"></span></td>
	</tr>
	
	<tr>
		<td colspan="3" height="200" valign="top">
			<div style="wudth: 100%; height: 100%; overflow: auto;">
				<pre style="white-space: pre-line; word-break:break-all;"><span id="imageContentSpan"></span></pre>
			</div>
		</td>
	</tr>
</table>

<input type="button" value="목록" onclick="location.href='imageboardList?pg=${pg}'">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
//데이터 값 들어오는 거 - 컨트롤러
$(function(){
	$.ajax({
		type: 'post',
		url:'/spring/imageboard/getImageboardView',
		data: 'seq=${seq}',
		dataType: 'json',
		success: function(data){
			console.log(data);
			
			var total = data.imageboardDTO.imagePrice * data.imageboardDTO.imageQty;
			$('#image1').attr('src', '../storage/'+data.imageboardDTO.image1);
			$('#imageNameSpan').text(data.imageboardDTO.imageName);
			$('#imagePriceSpan').text(data.imageboardDTO.imagePrice.toLocaleString()); //쉼표
			$('#imageQtySpan').text(data.imageboardDTO.imageQty);
			$('#totalSpan').text(total.toLocaleString());
			$('#imageContentSpan').text(data.imageboardDTO.imageContent);
		},
		error: function(err){
			console.log(err);
		}
	});
});
</script>
</form>