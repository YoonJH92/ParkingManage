<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Login</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/floating-labels/">

    <!-- Bootstrap core CSS -->
<link href="../resources/bootstrap.css" rel="stylesheet">


     <style>
    @import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Yeon+Sung&display=swap');
    
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      #contain{
      	display:flex;
      	justify-content: center;
      	
      }
      
      
		body{
		background: #DAE2F8;  /* fallback for old browsers */
		background: -webkit-linear-gradient(to bottom, #D6A4A4, #DAE2F8);  /* Chrome 10-25, Safari 5.1-6 */
		background: linear-gradient(to bottom, #D6A4A4, #DAE2F8); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
		font-family: 'Yeon Sung', cursive;

	}
	

	

    </style>
    <!-- Custom styles for this template -->
    <link href="../resources/floating-labels.css" rel="stylesheet">
  </head>
  <body>
  <div class="container" id="contain"  >
  <div class="jumbotron "  style="width: 50%"   >
  
  	<form action="searchPassProc.jsp" method="post" class="form-signin" name="login">
	  <div class="text-center mb-4">
    
	    <h1 class="h3 mb-3 font-weight-normal">Password찾기</h1>
	    <p>가입하신 계정의 정보가 일치할 경우 이메일로 비밀번호를 보내드립니다.</p>
	    
	  </div>

	  <div class="mb-3">
        <label for="address">Name</label>
        <input type="text" name="name" class="form-control"  placeholder="이름을 입력해 주세요" required>
       
      </div>
      
      <div class="mb-3">
        <label for="address">ID</label>
        <input type="text" name="id" class="form-control"  placeholder="아이디를 입력해 주세요" required>
        
      </div>
      
      <div class="mb-3">
        <label for="email">Email </label>
        <input type="email" name="email" class="form-control" placeholder="이메일을 입력해주세요">
        
      </div>
      <div class="mb-3">
        <label for="address">Tel</label>
        <input type="tel" name="tel" class="form-control"  placeholder="전화번호를 입력해 주세요" required>
        
      </div>
      <br>
      <div style="display: flex;
      justify-content: flex-end;">
        <input style="margin-right: 5px;" class="btn btn-primary btn-lg"  type="submit" value="확인">
        <button type="button" class="btn btn-primary btn-lg" onclick = "location.href = 'login.jsp' " >취소</button>
      </div>

	 
	  
  
  
  
   
  
   
  
	</form>
	
	</div>
	</div>
</body>
</html>