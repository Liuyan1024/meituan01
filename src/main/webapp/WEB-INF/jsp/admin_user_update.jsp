<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/adm/user/update" method="post">
	用户id:${user.id }<br>
	<input type="hidden" name="id" value="${user.id }">
	用户姓名：<input type="text" name="username" value="${user.username }"><br>
	用户手机：<input type="text" name="phone" value="${user.phone }"><br>
	电子邮箱：<input type="text" name="email" value="${user.email }"><br>
	<button>修改</button>
</form>
</body>
</html>