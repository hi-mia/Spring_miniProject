//목록
$(function(){
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoardList',
		data: 'pg='+$('#pg').val(),
		dataType: 'json',
		success: function(data){
			//console.log(JSON.stringify(data));
		
			  $.each(data.list, function(index, items){
		            $('<tr/>').append($('<td/>',{
		               align:'center',
		               text: items.seq
		               
		            })).append($('<td/>',{
		               
		               }).append($('<a/>',{//제목 td의자식
		                  href: '#',
		                  text: items.subject,
		                  id: 'subjectA',
		                  class: 'subject_'+items.seq //왜? 아이디속성을 걍 서브젝트A라고 하면 모둔 제목들의 아뒤가 다 똑같아.. 그럼 글을 하나하나 구분할 수가 없다 ..각자의 클래스속성을 부여하기위해
		               })) 
		            ).append($('<td/>',{
		               align:'center',
		               text: items.id
		               
		            })).append($('<td/>',{
		               align:'center',
		               text: items.logtime
		               
		            })).append($('<td/>',{
		               align:'center',
		               text: items.hit
		               
		            })).appendTo($('#boardListTable'));
		            
		            //답글
		            for(var i=1; i<=items.lev; i++){
		               $('.subject_'+ items.seq).before('&emsp;')
		            }
		            
		            if(items.pseq != 0){ //(items.pseq == 0) 끝까지 0이다 = 원글이다
		               $('.subject_'+ items.seq).before($('<img/>',{
		                  src:'../image/reply.gif'
		               }));
		            }//if
				
				/*
				//로그인 여부 - 1번
				$('.subject_'+items.seq).click(function(){
					if(data.memId == null) {
						alert("먼저 로그인하세요");
					}else{
		                  location.href='/spring/board/boardView?seq='+items.seq+'&pg'+$('#pg').val();
					}
				});
				 */
	
			}); //each
			
			  
		  //로그인여부-2
			 $(document).on('click','#subjectA', function(){ //document:왕조상님
			//alert($(this).parent().prev().prop('tagName')); //아무튼 조상이 오면 된다
			if(data.memId == null)
				alert("먼저 로그인 하세요 ");
			else {
				var seq = $(this).parent().prev().text();
				location.href='/spring/board/boardView?seq='+seq+'&pg='+$('#pg').val();
				}
		            
			 });
			 
			 //페이징 처리
			 $('#boardPagingDiv').html(data.boardPaging.pagingHTML);
						   
		}, 
			error:function(err){
			console.log(err);
		}
	});
});

//검색
//1번인 경우
$('#boardSearchBtn').click(function(){

//2번인 경우
//$('#boardSearchBtn').click(function(event, str){
//	if(str != 'research') $('input[name=pg]').val(1); //직접 검색 버튼을 클릭했을 때
	
	if($('#keyword').val() == '') 
		alert('검색어를  입력하세요');
	else {
		$.ajax({
			type: 'post',
			url: '/spring/board/getBoardSearchList',
			data:$('#boardSearchForm').serialize(),
			dataType: 'json',
			success: function(data) {
				//alert(JSON.stringify(data));
				
				$('#boardListTable tr:gt(0)').remove(); //boardListTable 폼의 tr태그 중 0번째(제목)보다 큰 거 다 지워라
				
				 $.each(data.list, function(index, items){
			            $('<tr/>').append($('<td/>',{
			               align:'center',
			               text: items.seq
			               
			            })).append($('<td/>',{
			               
			               }).append($('<a/>',{//제목 td의자식
			                  href: '#',
			                  text: items.subject,
			                  id: 'subjectA',
			                  class: 'subject_'+items.seq
			               })) 
			            ).append($('<td/>',{
			               align:'center',
			               text: items.id
			               
			            })).append($('<td/>',{
			               align:'center',
			               text: items.logtime
			               
			            })).append($('<td/>',{
			               align:'center',
			               text: items.hit
			               
			            })).appendTo($('#boardListTable'));
			            
			            //답글
			            for(var i=1; i<=items.lev; i++){
			               $('.subject_'+ items.seq).before('&emsp;')
			            }
			            
			            if(items.pseq != 0){ //(items.pseq == 0) 끝까지 0이다 = 원글이다
			               $('.subject_'+ items.seq).before($('<img/>',{
			                  src:'../image/reply.gif'
			               }));
			            }//if
					
					//로그인 여부
					$('.subject_'+items.seq).click(function(){
						if(data.memId == null) {
							alert("먼저 로그인하세요");
						}else{
			                  location.href='/spring/board/boardView?seq='+items.seq+'&pg'+$('#pg').val();
						}
					});

				}); //each
				 
				 //페이징 처리
				 $('#boardPagingDiv').html(data.boardPaging.pagingHTML);
				
			},
			error:function(err){
				console.log(err);
			}
		});//ajax
	}//if
	
});