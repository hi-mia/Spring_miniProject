<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>이미지 등록</h3>
<%-- 1. 단순 submit ~ action을 타고가라 --%>
<%-- 
<form id="imageboardWriteForm" method="post" enctype="multipart/form-data" action="imageboardWrite">
--%>

<%-- 2. AJax 통신 --%>
<form id="imageboardWriteForm">
	<table border="1" cellpadding="5" cellspacing="0"  width="500px">
		<tr>
		 	<td align="center" width="100">상품코드</td>
		 	<td>
		  		<input type="text" name="imageId" id="imageId" size="50" value="img_">
		 	</td>
		</tr>
		<tr>
		 	<td align="center">상품명</td>
		 	<td>
		  		<input type="text" name="imageName" id="imageName" size="50" placeholder="상품명 입력">
		 	</td>
		</tr>
 		<tr>
		 	<td align="center">단가</td>
		 	<td>
		  		<input type="text" name="imagePrice" id="imagePrice" size="50" placeholder="단가 입력">
		 	</td>
		</tr>
		 <tr>
		 	<td align="center">개수</td>
		 	<td>
		  		<input type="text" name="imageQty" id="imageQty" size="50" placeholder="개수 입력">
		 	</td>
		</tr>
		<tr>
		 	<td align="center">내용</td>
		 	<td>
		  		<textarea cols="50" rows="15" name="imageContent" id="imageContent" placeholder="내용입력"></textarea>
		 	</td>
			</tr>
		<tr>
			<td colspan="2">
			<input type="file" name="img" size="50"> <!-- controller의 파일 업로드쪽으로 보내기 위해 img로 이름을 바꿈 -->
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="file" name="img" size="50">
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="file" name="img[]" multiple size="50"> <!-- 한 번에 여러개 들어올 수 있도록 -->
		</tr>

		<tr>
			<td colspan="2" align="center">
				<input type="button" value="이미지등록" id="imageboardWriteBtn">
				<input type="reset"  value="다시작성">
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/imageboard.js"></script>"



