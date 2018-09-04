<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8"></meta>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
  	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
  	<link rel="stylesheet" type="text/css" href="css/login.css"></link>
	<title>用户登录</title>
</head>
<body style="background:url(/imgs/logi.jpg) no-repeat;background-size:100% auto;">
	<div class="box">
	
	<form id="logForm_mod" name="logForm_mod" action="/adm/login" method="post"  >
		<input  name="username" class="user" placeholder="账号" >
		<input  name="password" type="password" class="password" placeholder="密码">
		<!-- 验证码图片 -->
		
		<button class="btn" type="submit">登录</button> 
		
	</div>
	</form>
</body>
</html>