<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/css/public.css">
<link rel="stylesheet" type="text/css" href="/css/shop.css">
<title>商家</title>
<script src="/js/jquery-1.8.3.js"></script>
</head>
<body>
	<!-- 导航条 -->
	<div id="nav_bar" class="nav_bar">
		<!-- 导航条内容 -->
		<div class="nav_container">
			<!-- logo -->
			<a href="index.html" class="logo nav_bar_item"></a>
			<!-- 首页 -->
			<a href="/index" class="index nav_bar_item">首页</a>
			<!-- 我的订单 -->
			<a href="/order" class="orders nav_bar_item">我的订单</a>
			<!-- 用户账号 -->
			<span class="user nav_bar_item">您好：${sessionScope.usercode.username}</span>
		</div>
	</div>
	<!-- 头部 -->
	<div class="shop_top">
		<div class="shop_top_contain">
			<div id="shop_info" class="shop_info">
				<img class="shop_info_image" src="${merchant.image }">
				<div class="shop_info_nama">${merchant.name }</div>
				<div id="shop_info_extra" class="shop_info_extra">
					<div class="shop_info_grade">
						<span>4.1分</span> <span>综合评价</span> <span>高于周边商家<em>31.4</em></span>
					</div>
					<div class="shop_info_desc">
						广东省著名的特色传统小吃，得名于原产地——揭阳市惠来县隆江镇。其鲜美的味道和弹嫩糯香的口感加以本店特色米饭组合成了隆江猪脚饭，肥而不腻，入口香爽，深受广大人民的喜爱。
					</div>
					<div class="shop_info_adress">
						<span>商家地址： ${merchant.address }</span> <span>营业时间：
							10:00-20:30</span>
					</div>
					<div class="shop_info_delivery">
						由<span>金牌隆江猪脚饭(八旗二马路店)</span>提供配送服务
					</div>
				</div>
			</div>

			<div class="shop_server">
				<span class="shop_server_price"> <em>起送价</em> <em>${merchant.startPrice }</em>
				</span> <span class="shop_server_carrige"> <em>配送费</em> <em>${merchant.deliverPrice }</em>
				</span> <span class="shop_server_time"> <em>月销量</em> <em>${merchant.saleNum }</em>
				</span>
			</div>
		</div>
	</div>
	<div class="shop_main">
		<div id="shop_main_left" class="shop_main_left">
			<ul class="shop_main_tab">
				<li class="shop_main_tab_item">所有商品</li>
				<li class="shop_main_tab_item">评价</li>
			</ul>
			<!-- 食物 -->
			<div class="shop_food shop_main_left_item">
				<c:forEach items="${itemList}" var="item">
					<div class="shop_food_item">
						<img class="food_item_image" src="${item.image }">
						<div class="food_item_message">
							<span>${item.title }</span> <span>${item.price }</span>
						</div>
						<div class="food_item_button" data_id="${item.id}">
							<%-- <a href="/cart/add/${item.id}/1" class="shop_item"> --%>
							加入购物车
							<!-- </a> -->
						</div>
						<div class="food_item_choose">
							<!-- <span class="food_item_choose_sub">-</span>
							<input class="food_item_choose_inp" type="rext" name="" value="1">
							<span class="food_item_choose_plus">+</span> -->
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- 评价 -->
			<div class="shop_comment shop_main_left_item">
				<ul class="shop_comment_tab">
					<li class="shop_comment_tab_item">全部</li>
				</ul>
				<ul class="shop_comment_list comment_all">
				<c:if test="${!empty comments}">
					<c:forEach items="${comments}" var="com">
						<li class="shop_comment_list_item">
							<div class="comment_list_item_top">
								<img class="comment_image" src="/image/me.png">
								<ul class="comment_mess">
									<li>匿**户</li>
									<li>
										<ul class="comment_icon_list">
											<c:forEach begin="0" end="${com.star-1 }" var="i">
												<li class="heart-solid icon com_icon_active"></li>
											</c:forEach>
										</ul><span style="margin-left:100px;">50分钟送达</span>
									</li>
									<li></li>
								</ul>
								<span class="comment_time">2018-08-11 12:00:00</span>
							</div>
							<ul class="comment_food_list">
								<li class="comment_food_list_item">
									<ul class="comment_food_icon_list">
										${com.comment } 
									</ul>
								</li>
							</ul>
						</li>
					</c:forEach>
					</c:if>
				</ul>

			</div>
		</div>
		<div class="shop_main_right">
			<div class="shop_main_right_title">商家公告</div>
			<div class="shop_main_right_ps">请要配小辣椒的朋友在店里可以点！谢谢！</div>
			<div class="shop_main_right_carrige">
				<span>电话</span> <span>${merchant.phone }</span>
			</div>
			<div class="shop_main_right_repor">举报商家</div>


		</div>
	</div>

	<!-- 侧边栏 -->
	<div id="sidebar" class="sidebar">
		<div id="sidebar_tabs" class="sidebar_tabs">
			<!-- 侧边条 -->
			<div class="my_order">
				<!-- 我的订单 -->
				<svg class="icon_single" aria-hidden="true"> <use
					xlink:href="#icon-single"></use> </svg>
			</div>
			<div class="shop_car" id="shop_car">
				<!-- 购物车 -->
				<svg class="icon_car" aria-hidden="true"> <use
					xlink:href="#icon-gouwuchekong"></use> </svg>
				购物车
			</div>
		</div>
		<div id="sidebar_content" class="sidebar_content">
			<!-- 侧边栏内容 -->
			<div id="btn_return" class="btn_return">
				<!-- 返回按钮 -->
				<span>购物车</span> <span> <svg class="icon_right"
						aria-hidden="true"> <use xlink:href="#icon-shuangjiantouyou"></use>
					</svg>
				</span>
			</div>
			<div id="car_list" class="car_list">
				<div class="list_top" id="list_top">
					<span id="delete" class="delete">[清空]</span>
				</div>
				<ul id="list_center" class="list_center">
					<!--	<li class="list_item"><span class="food_name">柱侯牛腩汤面（捞）</span>
						<span class="quantity"> <span class="sub">-</span> <input
							class="quan_input" type="text" value="1"> <span
							class="plus">+</span>
					</span> <span class="price">18</span></li>
					<li class="list_item"><span class="food_name">柱侯牛腩汤面（捞）</span>
						<span class="quantity"> <span class="sub">-</span> <input
							class="quan_input" type="text" value="1"> <span
							class="plus">+</span>
					</span> <span class="price">18</span></li>-->
				</ul>
			</div>
		</div>
		<div class="list_bottom" id="list_bottom">
			<!-- 购物车底部 -->
			<div class="message">
				<!-- <span>共<span id="bottom_quantity">??</span>份
				</span>
				数量 -->
				<span>总计<span id="bottom_count">0</span>元
				</span>
				<!-- 总价格 -->
			</div>
			<button class="balance" id="creat_crat">去结算</button>
		</div>
	</div>
</body>



<script src="/js/iconfont.js"></script>
<script src="/js/shop.js"></script>
<script src="/js/public.js"></script>
</html>