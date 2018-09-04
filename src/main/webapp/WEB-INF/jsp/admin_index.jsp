<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>管理员主页</title>
    <link href="/admin/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/admin/css/admin_index.css" type="text/css" rel="stylesheet">
    <link href="/admin/css/public.css" type="text/css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="/admin/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/admin_index.js"></script>
  </head>
  
  <body>
    <form action="" method="post">
    	<nav class="navbar navbar-default">
    		<div class="navbar-header">admin，欢迎来到外卖服务后台管理！</div>
    		<div class="navbar-text">
			</div>
    	</nav>
    	<div class="container">
    		<div class="navLeft" style="height:104px;">
    			<table>
    				<tr align="center" class="active">
    					<td><a href="/adm/user/sel" target="manage" id="user_manage"><span id="userSpan">用户信息管理</span></a></td>
    				</tr>
    				<tr align="center">
    					<td><a href="/adm/mer/sel" target="manage">商家信息管理</a></td>
    				</tr>
    			</table>
    		</div>
    		<iframe class="navRight" src="/adm/user/sel" name="manage" style="height:800px;">
    			
    		</iframe>
    	</div>
    	<div class="f">
		    <span>美团外卖小组</span>
    	</div>
    </form>
  </body>
</html>
