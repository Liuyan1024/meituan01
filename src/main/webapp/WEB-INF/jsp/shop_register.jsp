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
<body style="background:url(/imgs/shop_login.png) no-repeat black;background-size:100% auto;">
	<div class="box">
		<form name="form" action="/mer/regist" method="post">
			<input class="box_item" placeholder="店铺名" name="name"> <input
				class="box_item" type="text" placeholder="手机" name="phone">
			<input class="box_item" placeholder="密码" type="password"
				name="estimate"> <input class="box_item" type="text"
				placeholder="地址" name="address"> <input class="box_item"
				type="text" placeholder="描述" name="description"> 选择商店图片：<!-- <input
				type="file" class="file" name="image"> -->
			<button class="btn">注册</button>
		</form>
	</div>
</body>

</html>