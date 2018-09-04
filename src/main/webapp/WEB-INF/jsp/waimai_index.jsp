<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8"></meta>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
  	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
  	<link rel="stylesheet" type="text/css" href="css/index.css"></link>
	<title>外卖首页</title>
</head>
<body>
	<!-- 导航条 -->
	<div id="nav_bar" class="nav_bar">
		<!-- 导航条内容 -->
		<div class="nav_container">
			
			<!-- 首页 -->
			<a href="index.html" class="index nav_bar_item">首页</a>
			<!-- 我的订单 -->
			<a href="/order" class="orders nav_bar_item">我的订单</a>
		</div>
	</div>
	<div class="main">
		<!-- 搜索 -->
		<div class="search">
			<div class="search_block">			
				<form>
					<input id="search_input" class="search_input" name="search" type="text" placeholder="搜索商家美食...">
				</form>
				<a href="#" class="search_icon">
					<svg class="icon_search" aria-hidden="true">
  					<use xlink:href="#icon-sousuo"></use>
				</svg>
				</a>
			</div>
		</div>
		
		<!-- 商家店铺盒子 -->
		<div class="shop_box">
			<c:forEach items="${list}" var="m">
				<a href="/item/${m.cid}" class="shop_item">
					<div class="shop_img_box"><img src="${m.image }"></div>
					<div class="shop_text_box">
						<span class="shop_name">${m.name}</span>
						<span class="carriage">配送费${m.deliverPrice}</span>		
					</div>				
					<div class="shop_detail"><!-- 细节展示 -->
						<span class="shop_name">${m.name }</span>
						<span class="shop_type">简餐</span>
						<span class="shop_type">烧腊饭</span>
						<div class="boundary"></div><!-- 分界线 -->
						<div class="tips">
							<span class="carriage">优惠配送费</span>
							<span class="time">平均42分钟送达</span>
						</div>
						<span class="remark">电话：${m.phone }</span>
						<!-- 三角形 -->		
						<div class="shop_detail_triangle"></div>
						<!-- 白色三角形 -->
						<div class="shop_detail_triangle_white"></div>
					</div>					
				</a>
			</c:forEach>		
		</div>
	</div>
</body>
<script src="js/iconfont.js"></script>
<script src="js/index.js"></script>
</html>