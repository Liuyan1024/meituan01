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
<link rel="stylesheet" type="text/css" href="/css/public.css">
<link rel="stylesheet" type="text/css" href="/css/order_detail.css">
<title>订单详情</title>
</head>
<body>
	<!-- 导航条 -->
	<div id="nav_bar" class="nav_bar">
		<!-- 导航条内容 -->
		<div class="nav_container">
			<!-- 首页 -->
			<a href="/index" class="index nav_bar_item">首页</a>
			<!-- 我的订单 -->
			<a href="/order" class="orders nav_bar_item">我的订单</a>
			<!-- 用户账号 -->
			<span class="user nav_bar_item">${usercode.username }</span>
		</div>
	</div>
	<div class="detail_main">
		<div class="detail_top">订单详情</div>
		<div class="detail_center">
			<div class="detail_mess">
				<span>订单已完成</span> <span>14:34确认送达</span>
			</div>

		</div>
		<!-- 食物信息 -->
		<table class="detail_food">
			<thead class="food_thead">
				<tr>
					<th><img src="/image/shop.png">
						<div>
							<span>美团外卖</span> <span>订单号：${oid }</span>
						</div></th>
					<th>商家电话:</th>
					<th>13533242014</th>
				</tr>
			</thead>
			<tbody>
				<tr class="food_tr">
					<td>菜品</td>
					<td>数量</td>
					<td>小计(元)</td>
				</tr>
				<c:forEach items="${orderItemList}" var="item">
					<tr class="food_tr">
						<td>${item.title }</td>
						<td>${item.num }</td>
						<td>${item.totalFee }</td>
					</tr>
				</c:forEach>
				<!--<tr class="food_tr">
					<td>豆干</td>
					<td>2</td>
					<td>1.80</td>
				</tr>
				<tr class="food_tr">
					<td>餐盒</td>
					<td></td>
					<td>1.00</td>
				</tr>
				<tr class="food_tr">
					<td>在线支付立减优惠</td>
					<td></td>
					<td>-14.00</td>
				</tr> -->
				<tr class="food_tr">
					<td>配送费</td>
					<td></td>
					<td>5.00</td>
				</tr>
				<tr class="food_tr">
					<td>商家代金券抵扣</td>
					<td></td>
					<td>0</td>
				</tr>
			</tbody>
		</table>
		<!-- 运送信息 -->
		<ul class="detail_carrige">
			<li class="detail_carrige_item">配送信息</li>
			<li class="detail_carrige_item">配送方式： 美团外卖提供配送服务</li>
			<li class="detail_carrige_item">送达时间： <fmt:formatDate
					value="${shipping.created }" pattern="HH:mm:ss" /></li>
			<li class="detail_carrige_item">联 系 人： ${shipping.receiverName }
			</li>
			<li class="detail_carrige_item">联系电话： ${shipping.receiverMobile}
			</li>
			<li class="detail_carrige_item">收货地址：
				${shipping.receiverAddress}</li>
			<li class="detail_carrige_item">发票信息： 无发票</li>
			<li class="detail_carrige_item">备 注： 无备注</li>
		</ul>
		<div id="comment_box">
				<span style="font-size:20px;">评价等级</span>
			<ul class="comment_icon_list">
			<c:forEach begin="0" end="${comment.star-1 }" var="i">
				<li class="heart-solid icon com_icon_active"></li>
			</c:forEach>
			</ul>
			<div style="height:140px;background:#f8f8f8;padding:20px;font-size:20px;line-height:30px;">
				<p>
				${comment.comment }
				</p>
			</div>
		</div>
	</div>
</body>
<script src="/js/iconfont.js"></script>
<script src="/js/public.js"></script>
</html>