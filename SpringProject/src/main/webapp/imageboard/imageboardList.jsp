<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
#currentPaging {
	color: red;
	text-decoration: underline;
	cursor: pointer;
	margin: 5px;
}

#paging {
	color: black;
	text-decoration: none;
	cursor: pointer;
	margin: 5px;
}
</style>

<form id="imageboardDeleteForm" method="get" action="imageboardDelete">
<input type="hidden" id="pg" value="${pg }">
<table id="imageboardListTable" border="1" width="700" cellpadding="5" frame="hsides" rules="rows">
	<tr> <%-- 제목 --%>
		<th width="100"><input type="checkbox" id="all">번호</th>
		<th width="100">이미지</th>
		<th width="200">상품명</th>
		<th width="100">단가</th>
		<th width="100">개수</th>
		<th width="100">합계</th>
	</tr>
	<%-- 여기에 동적으로 다 붙게 한다 --%>
</table>
<br>
<input type="button" style="float: left;" value="선택삭제" id="choiceDeleteBtn">	
	
<div style="width: 700px; text-align: center;" id="imageboardPagingDiv"></div>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$.ajax({
		type: 'post',
		url: '/spring/imageboard/getImageboardList',
		// data: 'pg='+$('#pg').val(), // hidden에서 받아온 값(hidden을 사용했을 때)
		data: 'pg=${pg}', //'pg='+'${pg}' // 컨트롤러로부터 넘어온 값(pg), 컨트롤러에게 바로 받아옴
		dataType: 'json',
		success: function(data){
			//console.log(JSON.stringify(data));
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>', {
					align: 'center',
					text: items.seq
				}).prepend($('<input/>',{
					type: 'checkbox',
					name:'check',
					value: items.seq
				}))
				).append($('<td/>', {
					align: 'center',
					//text: items.image1	
					}).append($('<img/>',{ //자식
						src:'/spring/storage/'+items.image1,
						style: 'width: 70px; height: 70px; cursor: pointer;',
						class: 'img_'+items.seq
					})) //servlet.context에서 리소스로 등록해줘야 한다 - 안그러면 컨트롤러로 간다, 컨트롤러로 가면 이 주소가 없다: 컨트롤러로 못가게 막음
				).append($('<td/>', {
					align: 'center',
					text: items.imageName
				})).append($('<td/>', {
					align: 'center',
					text: items.imagePrice.toLocaleString()
				})).append($('<td/>', {
					align: 'center',
					text: items.imageQty
				})).append($('<td/>', {
					align: 'center',
					text: (items.imagePrice * items.imageQty).toLocaleString()
				})).appendTo($('#imageboardListTable'));
				
				$('.img_'+items.seq).click(function(){
					location.href='/spring/imageboard/imageboardView?seq='+items.seq+'&pg=${pg}';
					
				});
				
			});//each
			
			//페이징 처리
			$('#imageboardPagingDiv').html(data.imageboardPaging.pagingHTML);
			//결과값이 imageboardPgingDTO의 pagingHTML에 누적된다
		},
		error: function(err){
			console.log(err);
		}
	});
	
});

function imageboardPaging(pg){
	location.href="imageboardList?pg="+pg;
}

//전체 선택 또는 해제
$('#all').click(function(){
	//alert($('#all').attr('checked')); //checked 속성이 없어서 undefined으로 나온다
	//alert($('#all').prop('checked')); //true 또는 false / 값을 들고 와야 한다
	
	if($('#all').prop('checked')) {
		$('input[name=check]').prop('checked', true);	
	}else{
		$('input[name=check]').prop('checked', false);
	}

});

//선택 삭제
$('#choiceDeleteBtn').click(function(){
	var count = $('input[name=check]:checked').length; //체크된 개수가 나온다
	/*
	var count = 0;
	
	for(var i=0; i<check.length; i++){
		if(check[i].checked) count++;
	}//for
	이거랑 같은 의미
	*/
	
	if(count == 0){
		alert("삭제할 항목을 선택하세요");
	}else{
		if(confirm("정말로 삭제??"))
			$('#imageboardDeleteForm').submit();
	}
});
</script>
