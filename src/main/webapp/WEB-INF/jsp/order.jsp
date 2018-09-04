<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/public.css">
<link rel="stylesheet" type="text/css" href="css/order.css">
<script src="/js/jquery-1.8.3.js"></script>
<title>我的订单</title>
</head>
<body id="body">
	<!-- 导航条 -->
	<div id="nav_bar" class="nav_bar">
		<!-- 导航条内容 -->
		<div class="nav_container">
			<!-- 首页 -->
			<a href="/index" class="index nav_bar_item">首页</a>
			<!-- 我的订单 -->
			<a href="order.html" class="orders nav_bar_item">我的订单</a>
			<!-- 用户账号 -->
			<span class="user nav_bar_item">您好：${usercode.username }</span>
		</div>
	</div>
	<div class="main">
		<ul class="order_sidebar">
			<li class="order_sidebar_item"><svg class="order_sidebar_icon"
					aria-hidden="true"> <use xlink:href="#icon-single"></use></svg>
				我的订单</li>
		</ul>
		<!-- 所有订单 -->
		<div class="order_item order_box">
			<div class="order_box_top">我的所有订单</div>
			<table class="order_box_con">
				<thead>
					<tr>
						<th>下单时间</th>
						<th class="thead_order_con">订单内容</th>
						<th></th>
						<th>支付金额(元)</th>
						<th>状态</th>
						<th>操作</th>
						<th>查看</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tbOrderList}" var="order">
						<tr class="order_tr">
							<td class="order_time"><fmt:formatDate
									value="${order.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td class="order_shop_image"><img class=""
								src="image/items/${order.orderId }.jpg"></td>
							<td class="order_info">
								<p class="order_info_food">
									<span class="order_info_food_list">猪脚拼</span> <span
										class="food_quantity">3</span>个菜品
								</p>
								<p claa="order_no">订单号: ${order.orderId }</p>
							</td>
							<td class="order_amount">${order.payment }</td>
							<c:if test="${order.status==2 }">
							<td class="order_status">已付款</td>
							</c:if>
							<c:if test="${order.status==3 }">
							<td class="order_status">配送中</td>
							</c:if>
							<c:if test="${order.status==4 }">
							<td class="order_status">已完成</td>
							</c:if>
							<c:if test="${order.status==5 }">
							<td class="order_status">已评价</td>
							</c:if>
							<c:if test="${order.status==2}">
							<td class="order_detail"></td>
							</c:if>
							<c:if test="${order.status==3}">
							<td class="order_detail"><a href="javascript:;"><span
									class="order_detail_btn receive_btn" style="color:red;" data_id="${order.orderId}">收货</span></a></td>
							</c:if>
							<c:if test="${order.status==4}">
							<td class="order_detail"><a href="javascript:;"><span
									class="order_detail_btn comment_btn" style="color:red;" data_id="${order.orderId}">评价</span></a></td>
							</c:if>
							<c:if test="${order.status==5}">
							<td class="order_detail"></td>
							</c:if>
							<td class="order_detail"><a href="/order/${order.orderId }"><span
									class="order_detail_btn">订单详情</span></a></td>		
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
</body>
<script src="js/public.js"></script>
<script src="js/order.js"></script>
<script src="js/iconfont.js"></script>
</html>