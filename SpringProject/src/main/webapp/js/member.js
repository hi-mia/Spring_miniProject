//로그인
$('#loginBtn').click(function(){
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
	if($('#id').val() == ''){
		$('#idDiv').text('아이디 입력');
		$('#idDiv').css('color', 'yellow');
		$('#idDiv').css('font-size', '8pt');
		$('#idDiv').css('font-weight', 'bold');
		
	}else if($('#pwd').val() == ''){
		$('#pwdDiv').text('비밀번호 입력');
		$('#pwdDiv').css('color', 'yellow');
		$('#pwdDiv').css('font-size', '8pt');
		$('#pwdDiv').css('font-weight', 'bold');
		
	}else{
		$.ajax({
			type : 'post',
			url: '/spring/member/login',
			data: {
				'id': $('#id').val(),
				'pwd': $('#pwd').val()
			},
			dataType: 'text',
			success: function(data){
				if(data == 'success'){
					location.href = '/spring/index.jsp';
				}else{
					alert('로그인 실패');
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

$('#loginBtn').click(function(){
	   $('#idDiv').empty();
	   $('#pwdDiv').empty();

	   if($('#id').val() == ''){
	      $('#idDiv').text('아이디를 입력하세요');
	      $('#idDiv').css('color','yellow');
	      $('#idDiv').css('font-size','8pt');
	      $('#idDiv').css('font-weight','bold');

	   }else if ($('#pwd').val() == ''){
	      $('#pwdDiv').text('비밀번호를 입력하세요');
	      $('#pwdDiv').css('color','yellow');
	      $('#pwdDiv').css('font-size','8pt');
	      $('#pwdDiv').css('font-weight','bold');

	   }else{
	      $.ajax({
	         type:'post',
	         url:'/spring/member/login',
	         data:{
	            'id':$('#id').val(),
	            'pwd':$('#pwd').val() 
	         },
	         dataType:'text',
	         success:function(data){
	            //alert(data);
	            if(data == 'success'){
	               location.href='/spring/index.jsp';

	            }else {
	               alert('로그인 실패');

	            }
	            
	         },
	         error:function(err){
	            console.log(err);
	         }
	            
	      });
	   }
	});

//아이디 중복체크
$('#checkId').click(function(){ //포커스 아웃써도 된다
//	$('#writeForm #idDiv').focusout(function(){
	$('#writeForm #idDiv').empty();
	
	if($('#writeForm #id').val() == ''){
		$('#writeForm #idDiv').text('아이디 입력');
		$('#writeForm #idDiv').css('color', 'yellow');
		$('#writeForm #idDiv').css('font-size', '8pt');
		$('#writeForm #idDiv').css('font-weight', 'bold');
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/member/checkId',
			data: 'id=' + $('#writeForm #id').val(),
			dataType: 'text',
			success: function(data){
				if(data=='exist') {
					$('#writeForm #idDiv').text('사용 불가능');
					$('#writeForm #idDiv').css('color', 'red');
					$('#writeForm #idDiv').css('font-size', '8pt');
					$('#writeForm #idDiv').css('font-weight', 'bold');
				}else if(data=='non_exist') {
					$('#writeForm #idDiv').text('사용 가능');
					$('#writeForm #idDiv').css('color', 'blue');
					$('#writeForm #idDiv').css('font-size', '8pt');
					$('#writeForm #idDiv').css('font-weight', 'bold');
					
					$('input[name=check]').val($('#writeForm #id').val()); //중복체크 확인
				}
				
			},
			error: function(err){
				console.log(err);
			}
		});
	}//if
});

//우편번호
$('#checkPostBtn').click(function(){
	window.open("/spring/member/checkPost","zipcode", "width=700 height=500 left=700 top=200 scrollbars=yes"); //zipcode 넣어줘야 창이 계속 열리는 거 막을 수 있음
});

$('#checkPostSearchBtn').click(function(){
	$.ajax({
		type: 'post',
		url: '/spring/member/checkPostSearch',
		data: $('#checkPostForm').serialize(),
		dataType: 'json',
		success: function(data) {
			//alert(JSON.stringify(data));
			$('#checkPostTable tr:gt(2)').remove(); //2행보다 큰 애들은 지워라
			
			$.each(data.list, function(index, items){ //반복문
				var address = items.sido+' '
							+ items.sigungu+ ' '
							+ items.yubmyundong+ ' '
							+ items.ri+ ' '
							+ items.roadname+ ' '
							+ items.buildingname;
				
				address = address.replace(/null/g, ''); //g: 전체에서 / null을 찾아달라 + 이 null을 ''로 덮어 쓴다
				
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.zipcode
				})).append($('<td/>',{
					colspan: '3',
					
				}).append($('<a/>',{
					id: 'addressA',
					text: address,
				}))
				).appendTo($('#checkPostTable'));
				
			});//each
			
			$('a').click(function(){
				//alert($(this).prop('tagName'));
				//alert($(this).parent().prev().text());
				
				$('#zipcode', opener.document).val($(this).parent().prev().text());
				$('#addr1', opener.document).val($(this).text());
				$('#addr2', opener.document).focus();
				window.close();
				
			});
		},
		error: function(err){
			console.log(err);
		}
	});
});

//회원가입
$('#writeBtn').click(function(){
	$('#writeForm #nameDiv').empty();
	$('#writeForm #idDiv').empty();
	$('#writeForm #pwdDiv').empty();
	
	if($('#writeForm #name').val() == ''){
		$('#writeForm #nameDiv').text('이름 입력');
		$('#writeForm #nameDiv').css('color', 'yellow');
		$('#writeForm #nameDiv').css('font-size', '8pt');
		$('#writeForm #nameDiv').css('font-weight', 'bold');
	}else if($('#writeForm #id').val() == ''){
		$('#writeForm #idDiv').text('아이디 입력');
		$('#writeForm #idDiv').css('color', 'yellow');
		$('#writeForm #idDiv').css('font-size', '8pt');
		$('#writeForm #idDiv').css('font-weight', 'bold');
	}else if($('#writeForm #pwd').val() == ''){
		$('#writeForm #pwdDiv').text('비밀번호 입력');
		$('#writeForm #pwdDiv').css('color', 'yellow');
		$('#writeForm #pwdDiv').css('font-size', '8pt');
		$('#writeForm #pwdDiv').css('font-weight', 'bold');
	}else if($('#writeForm #pwd').val() != $('#writeForm #repwd').val()){
		$('#writeForm #pwdDiv').text('비밀번호가 맞지 않습니다');
		$('#writeForm #pwdDiv').css('color', 'yellow');
		$('#writeForm #pwdDiv').css('font-size', '8pt');
		$('#writeForm #pwdDiv').css('font-weight', 'bold');
	}else if($('input[name=check]').val() != $('#writeForm #id').val()) {
		$('#writeForm #idDiv').text('중복체크 하세요');
		$('#writeForm #idDiv').css('color', 'yellow');
		$('#writeForm #idDiv').css('font-size', '8pt');
		$('#writeForm #idDiv').css('font-weight', 'bold');
	}else{
		$.ajax({
			type:'post',
			url:'/spring/member/write',
			data: $('#writeForm').serialize(),
			success: function(){
				alert('회원가입을 축하합니다');
				location.href='/spring/index.jsp';
				
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

//회원정보수정
$('#modifyBtn').click(function(){
   $('#nameDiv').empty();
   $('#pwdDiv').empty();
   
   if($('#name').val() == ''){
      $('#nameDiv').html("이름 입력");
      $('#nameDiv').css('color', 'red');
      $('#nameDiv').css('font-size', '8pt');
      $('#nameDiv').css('font-weight', 'bold');
      
   }else if($('#pwd').val() == ''){
      $('#pwdDiv').html("비밀번호 입력");
      $('#pwdDiv').css('color', 'red');
      $('#pwdDiv').css('font-size', '8pt');
      $('#pwdDiv').css('font-weight', 'bold');
      
   }else if($('#repwd').val() == ''){
      $('#pwdDiv').html("재확인 비밀번호 입력");
      $('#pwdDiv').css('color', 'red');
      $('#pwdDiv').css('font-size', '8pt');
      $('#pwdDiv').css('font-weight', 'bold');
      
   }else if($('#pwd').val() != $('#repwd').val()){
      $('#pwdDiv').html("비밀번호가 틀렸습니다");
      $('#pwdDiv').css('color', 'red');
      $('#pwdDiv').css('font-size', '8pt');
      $('#pwdDiv').css('font-weight', 'bold');
      
   }else{
      $('form[name=modifyForm]').submit();
   }
   
});