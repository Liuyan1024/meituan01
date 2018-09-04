<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" type="text/css" href="/css/public.css">
  	<link rel="stylesheet" type="text/css" href="/css/shop.css">
  	<link rel="stylesheet" type="text/css" href="/css/order.css">
  	<link rel="stylesheet" type="text/css" href="/css/shop_manager.css">
  	<script src="/js/jquery-1.8.3.js"></script>
	<title>商家</title>
</head>
<body>
	<!-- 导航条 -->
	<div id="nav_bar" class="nav_bar">
		<!-- 导航条内容 -->
		<div class="nav_container">
			<!-- logo -->
			<a href="index.html" class="logo nav_bar_item"></a>
			<!-- 首页 -->
			<a href="index.html" class="index nav_bar_item nav_focus">首页</a>
			<!-- 加盟合作 -->
			<a href="shop_person_register.html" class="join nav_bar_item">加盟合作</a>
		</div>
	</div>
	<!-- 头部 -->
	<div class="shop_top">
		<div class="shop_top_contain">
			<div id="shop_info" class="shop_info">
				<img class="shop_info_image" src="${merchants.image }">
				<div class="shop_info_nama">${merchants.name }</div>
				<div id="shop_info_extra" class="shop_info_extra">
					<div class="shop_info_grade">
						<span>4.1分</span>
						<span>综合评价</span>
						<span>高于周边商家<em>31.4</em></span>
					</div>
					<div class="shop_info_desc">
					隆江猪脚饭是广东省著名的特色传统小吃，得名于原产地——揭阳市惠来县隆江镇。其鲜美的味道和弹嫩糯香的口感加以本店特色米饭组合成了隆江猪脚饭，肥而不腻，入口香爽，深受广大人民的喜爱。
					</div>
					<div class="shop_info_adress">
						<span>商家地址： 广州市越秀区八旗二马路72号之一至之三首层自编1号</span>
						<span>营业时间： 10:00-20:30</span>
					</div>
					<div class="shop_info_delivery">
						由<span>金牌隆江猪脚饭(八旗二马路店)</span>提供配送服务
					</div>
				</div>
			</div>
			<div class="shop_server">
				<span class="shop_server_price">
					<em>起送价</em>
					<em>20元</em>
				</span>
				<span class="shop_server_carrige">
					<em>配送费</em>
					<em>优惠配送费</em>
				</span>
				<span class="shop_server_time">
					<em>平均送达速度</em>
					<em>34分钟</em>
				</span>
			</div>
		</div>
	</div>
	<div class="shop_main">
		<div id="shop_main_left" class="shop_main_left">
			<ul class="shop_main_tab">
				<li class="shop_main_tab_item">商品</li>
				<li class="shop_main_tab_item">订单</li>
			</ul>
			<!-- 食物 -->
			<div class="shop_food shop_main_left_item" id="shop_food">
			<c:forEach items="${itemList}" var="item">
				<div class="shop_food_item">
					<img class="food_item_image" src="${item.image }">
					<div class="food_item_message">
						<span>${item.title }</span>
						<span>${item.price }</span>
					</div>
					<div class="food_item_button food_item_button_delete" data_id="${item.id}" >删除商品</div>
				</div>
			</c:forEach>	
				<!-- 添加商品 -->
				<div id="add_food">
					添加商品
				</div>
			</div>

			<div class="shop_orders shop_main_left_item">
				<!-- 所有订单 -->
				<div class="order_item order_box">
					<div class="order_box_top">店铺所有订单</div>
					<table class="order_box_con">
						<thead>
							<tr>
								<th>下单时间</th>
								<th class="thead_order_con">订单内容</th>
								<th></th>
								<th>支付金额(元)</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="order_content">
							
						</tbody>
					</table>
				</div>
			</div>


		</div>
	</div>
	<div id="zhezhao"></div>
	<!-- 添加新地址弹出层 -->
	<div id="adressdialog_add" class="adressdialog">
		<div class="adressdialog_top">添加新菜谱</div>
		<div class="adressform">
			<form name="form" action="/mer/add" method="post" enctype ="multipart/form-data">
			<div class="adressform_item">
				<label>菜名</label><input type="text" placeholder="请输入菜名" name="title">
			</div>
			<div class="adressform_item">
				<label>价格</label><input type="text" placeholder="请输入价格" name="price">
			</div>
			<div class="adressform_item">
				<label>数量</label><input type="text" placeholder="请输入数量" name="num">
			</div>
			<!-- <div class="adressform_item">
				<label>图片</label><input type="file" class="file" name="image">
			</div> -->
			
			</form>
			<div class="adressform_item">
				<label>图片</label><input type="file" class="file" >
			</div>
		</div>
		<div class="adressdialog_bottom">
			<span class="from_btn from_yes" id="from_btn from_yes">保存</span>
			<span class="from_btn from_no" id="modify_cancel_btn">取消</span>
		</div>
		<!-- 关闭按钮 -->
		<div id="add_close_btn">+</div>
	</div>
</body>
<script src="/js/iconfont.js"></script>
<script src="/js/shop_manager.js"></script>
<script src="/js/public.js"></script>
</html>