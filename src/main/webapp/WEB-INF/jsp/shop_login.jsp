<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"></meta>
<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
<link rel="stylesheet" type="text/css" href="/css/shop_register.css"></link>
<title>商家注册</title>
</head>
<body
	style="background: url(/imgs/shop_login.png) no-repeat black; background-size: 100% auto;">
	<div class="box">
		<form name="form" action="/mer/login" method="post">
			<input class="box_item" placeholder="店铺名" name="username">
			<input class="box_item" placeholder="密码" type="password"
				name="password">
			<button class="btn">登录</button>
			<button class="btn" id="shop_register" style="margin-top:15px;">注册</button>
		</form>
	</div>
</body>
<script>
	var reg_btn = document.getElementById("shop_register");
	reg_btn.onclick=function(){
		location.href="/shop_regist";
		return false;
	}
</script>
</html>