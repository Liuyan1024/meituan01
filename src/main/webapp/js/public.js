
var btn_return = document.getElementById("btn_return");
var sidebar = document.getElementById("sidebar");

var sidebar_tabs = document.getElementById("sidebar_tabs");
sidebar_tabs.onclick = function() {
	sidebar.style.right = "0px";
}

btn_return.onclick = function() {
	sidebar.style.right = "-295px";
}

/*//显示加减按钮
var list_item = document.getElementsByClassName("list_item");
var quan_input = document.getElementsByClassName("quan_input");
var plus = document.getElementsByClassName("plus");
var sub = document.getElementsByClassName("sub");
var price = document.getElementsByClassName("price");
var len = quan_input.length;
var show_plus_btn = function() {
	for (let i = 0; i < len; i++) {
		$(list_item[i]).on("mouseover", function() {
			plus[i].style.opacity = "1";
			sub[i].style.opacity = "1";
		});
	}
}
show_plus_btn();
// 隐藏加减按钮

var hidden_plus_btn = function() {
	for (let i = 0; i < len; i++) {
		$(list_item[i]).on("mouseout", function() {
			plus[i].style.opacity = "0";
			sub[i].style.opacity = "0";
		});
	}
}
hidden_plus_btn();

// 加按钮

var plus_btn_click = function() {
	for (let i = 0; i < len; i++) {
		$(plus[i]).on("click", function() {
			// 计算单价
			var unit_price = parseInt(price[i].innerHTML)
					/ parseInt(quan_input[i].value);
			var new_value = parseInt(quan_input[i].value) + 1;
			quan_input[i].value = new_value;
			price[i].innerHTML = unit_price * new_value;
		});
	}
}
plus_btn_click();
// 减按钮
var list_center = document.getElementById("list_center");
var sub_btn_click = function() {
	for (let i = 0; i < len; i++) {
		$(sub[i]).on("click",function() {
			// 计算单价
			var old_value = parseInt(quan_input[i].value);
			// 若数量小于等于1，按减按钮清除,并重新获取相关元素
			if (old_value <= 1) {
				list_center.removeChild(list_item[i]);
				list_item = document.getElementsByClassName("list_item");
				quan_input = document.getElementsByClassName("quan_input");
				plus = document.getElementsByClassName("plus");
				sub = document.getElementsByClassName("sub");
				price = document.getElementsByClassName("price");
				len = quan_input.length;
				show_plus_btn();
				hidden_plus_btn();
				plus_btn_click();
				sub_btn_click();
				return;
			}

			var unit_price = parseInt(price[i].innerHTML) / old_value;
			var new_value = parseInt(quan_input[i].value) - 1;
			quan_input[i].value = new_value;
			price[i].innerHTML = unit_price * new_value;
		});
	}
}
sub_btn_click();

// 清空按钮
var sidebar_content = document.getElementById("sidebar_content");
var car_list = document.getElementById("car_list");
var car_delete = document.getElementById("delete");
var list_bottom = document.getElementById("list_bottom");
car_delete.onclick = function() {
	sidebar_content.removeChild(car_list);
	list_bottom.parentNode.removeChild(list_bottom);
}
// 如果没有订单，删除购物车顶部
if (!list_center.getElementsByTagName("li")[0]) {
	list_bottom.parentNode.removeChild(list_bottom);
}
*/
