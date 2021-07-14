//글쓰기
$('#boardWriteBtn').click(function(){
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
			url: '/spring/board/boardWrite',
			data: {
				'subject': $('#subject').val(),
				'content': $('#content').val()
			},
			success: function(){
				alert('글쓰기 완료');
				location.href='/spring/board/boardList';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

//답글쓰기
$('#boardReplyBtn').click(function(){
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
			url: '/spring/board/boardReply',
			data: {
				'pseq': $('input[name=pseq]').val(),//원글 번호도 실어야 한다
				'subject': $('#subject').val(),
				'content': $('#content').val()
			},
			success: function(){
				alert('답글쓰기 완료');
				location.href='/spring/board/boardList?pg='+$('input[name=pg]').val();
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});
