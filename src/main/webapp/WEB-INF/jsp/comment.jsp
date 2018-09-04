<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/css/public.css">
	<link rel="stylesheet" type="text/css" href="/css/comment.css">
	<script src="/js/jquery-1.8.3.js"></script>
	<title>评价</title>
</head>
<body>
	<!-- 导航条 -->
	<div id="nav_bar" class="nav_bar">
		<!-- 导航条内容 -->
		<div class="nav_container">
			<!-- 首页 -->
			<a href="" class="index nav_bar_item nav_focus">首页</a>
			<!-- 我的订单 -->
			<a href="" class="orders nav_bar_item">我的订单</a>
			<!-- 用户账号 -->
			<span class="user nav_bar_item">${usercode.username}</span>
		</div>
	</div>
	<div class="comment_main">
		<div class="comment_top">待评价订单<button id="comment_sub" data_oid="${order.orderId }" data_mid="${merchants.cid}">提交评价</button></div>
		<div class="comment_center">
			<img src="/image/merchants/${merchants.cid}.jpg">
			<div class="commetn_shop">
				<span>${merchants.name }</span>
				<span>订单号：${order.orderId }</span>
			</div>
			<div class="comment_time">下单时间：<fmt:formatDate
									value="${order.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="comment_con">
			<div class="comment_serve">
				<span class="comment_title">评价等级</span>
				<ul class="comment_icon" id="comment_icon">
					<li class="heart-solid icon"></li>
					<li class="heart-solid icon"></li>
					<li class="heart-solid icon"></li>
					<li class="heart-solid icon"></li>
					<li class="heart-solid icon"></li>
				</ul>
				<span class="comment_tips" id="comment_tips">点击心心打分</span>
			</div>
		</div>
		<div class="comment_text">
			<textarea></textarea>
		</div>
	</div>

</body>
<script src="/js/iconfont.js"></script>
<script src="/js/public.js"></script>
<script src="/js/comment.js"></script>
</html>