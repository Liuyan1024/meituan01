<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/adm/mer/update" method="post">
	店家id:${merchants.cid }<br>
	<input type="hidden" name="cid" value="${merchants.cid }">
	店家名字：<input type="text" name="name" value="${merchants.name }"><br>
	店家手机：<input type="text" name="phone" value="${merchants.phone }"><br>
	店家邮箱：<input type="text" name="address" value="${merchants.address }"><br>
	<button>修改</button>
</form>
</body>
</html>