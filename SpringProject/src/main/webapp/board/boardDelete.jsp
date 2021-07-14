<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript">

$(function(){	
	
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoard',
		data: 'seq='+$('input[name=seq]').val(),
		dataType: 'json',
		success: function(data){
			if(confirm('정말로 삭제하시겠습니까?')){ 
				$.ajax({
                type: 'post',
                url: '/spring/board/deleteBoard',
                data: 'seq='+$('input[name=seq]').val(),
                success: function(){
                   alert('게시글 삭제 완료');
                   location.href='/spring/board/boardList';
                },
                error:function(err){
        			console.log(err);
        		}
        	});
		}
	             
	});
	
});
	

</script>
