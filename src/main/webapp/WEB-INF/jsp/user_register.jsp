<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8"></meta>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
  	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
  	<link rel="stylesheet" type="text/css" href="css/user_register.css"></link>
	<title>用户注册</title>
</head>
<body style="background:url(/imgs/login.jpg) no-repeat;background-size:100% auto;">
	<div class="box">
	 <form id="regForm_mod" name="regForm_mod" action="/user/register" method="post"  >
		<input name="username" type="text" class="box_in user" placeholder="用户名">
		<input name="password" type="password" class="box_in password" placeholder="密码">	
		<input name="email" class="box_in email" placeholder="email">
		<input name="phone" class="box_in phone" placeholder="手机">			
		<button  class="box_in btn" type="submit">注册</button>
	</form>
	</div>
	
</body>
</html>