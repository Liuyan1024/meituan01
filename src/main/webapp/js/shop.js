//显示店家信息框
var  shop_info = document.getElementById("shop_info");
var shop_info_extra = document.getElementById("shop_info_extra");
shop_info.onmouseover=function(){
	shop_info_extra.style.display = "block";
}
//隐藏店家信息框
shop_info.onmouseout=function(){
	shop_info_extra.style.display = "none";
}
//食物，评价选项卡
var shop_main_tab_item = document.getElementsByClassName("shop_main_tab_item");
var shop_main_left_item = document.getElementsByClassName("shop_main_left_item");
for(let i=0,len=shop_main_tab_item.length;i<len;i++){
	shop_main_tab_item[i].onclick=function(){
		for(let j=0,len_2=shop_main_tab_item.length;j<len_2;j++){
			shop_main_left_item[j].style.display = "none";
		}
		shop_main_left_item[i].style.display = "block";
	}
}

var food_item_button = document.getElementsByClassName("food_item_button");
for(var i=0;i<food_item_button.length;i++){
	$(food_item_button[i]).on("click",function(){
		var id = this.getAttribute("data_id");
		var url = "/cart/add/"+ id+"/1";
		$.ajax({
			"url":url,
			"type":"POST",
			"success":function(data){
				alert(data);
				addCar();
			},
			"error":function(){
				alert("服务器繁忙，请稍后重试");
			},
			"dataType":"text"
		});
	});
}


var shop_car = document.getElementById("shop_car");
function addCar(){
	$("#list_center").html("");
	var url = "/cart/cart";
	$.ajax({
		"url":url,
		"type":"POST",
		"success":function(data){
			console.log(data);
			for(var i in data){
				var id = data[i].id;
				var title = data[i].title;
				var price = data[i].price;
				var num = data[i].num;
				$("#list_center").append("<li class=\"list_item\"><span class=\"food_name\">"+title+"</span><span class=\"quantity\"><span class=\"sub\">-</span><input class=\"quan_input\" type=\"text\" data_id=\""+id+"\" value=\""+num+"\" readonly><span class=\"plus\">+</span></span><span class=\"price\">"+num*price+"</span></li>");
			}
			Count();
			//显示加减按钮
			var list_item = document.getElementsByClassName("list_item");
			var quan_input = document.getElementsByClassName("quan_input");
			var plus = document.getElementsByClassName("plus");
			var sub = document.getElementsByClassName("sub");
			var price = document.getElementsByClassName("price");
			var len = quan_input.length;
			var show_plus_btn =	function(){
				for(let i=0;i<len;i++){
				list_item[i].onmouseover=function(){
					plus[i].style.opacity = "1";
					sub[i].style.opacity = "1";
				}
				}
			}
			show_plus_btn();
			//隐藏加减按钮

			var hidden_plus_btn = function(){
				for(let i=0;i<len;i++){
				list_item[i].onmouseout=function(){
					plus[i].style.opacity = "0";
					sub[i].style.opacity = "0";
				}
				}
			} 
			hidden_plus_btn();
			
			//修改数量
			function modifyNum(id,num){
				var url = "/cart/update/"+id+"/"+num;
				$.ajax({
					"url":url,
					"type":"POST",
					"success":function(data){
						console.log(data);
					},
					"error":function(){
						alert("服务器繁忙，请稍后重试");
					},
					"dataType":"text"
				});
			}
			
			//删除cookie
			function deleteCookie(id){
				var url = "/cart/delete/"+id;
				$.ajax({
					"url":url,
					"type":"POST",
					"success":function(data){
						console.log(data);
					},
					"error":function(){
						alert("服务器繁忙，请稍后重试");
					},
					"dataType":"text"
				});
			} 
			
			//计算总价
			function Count(){
				var sum = 0;
				price = document.getElementsByClassName("price");
				for(var i=0;i<price.length;i++){
					sum+=parseInt(price[i].innerText);
				}
				$("#bottom_count").html(sum);
			}
			
			
			
			//加按钮
			var plus_btn_click = function(){
				for(let i=0;i<len;i++){
				plus[i].onclick=function(){
					//计算单价
					var unit_price = parseInt(price[i].innerHTML)/parseInt(quan_input[i].value);
					var new_value = parseInt(quan_input[i].value)+1;
					quan_input[i].value = new_value;
					price[i].innerHTML = unit_price*new_value;
					modifyNum(quan_input[i].getAttribute("data_id"),new_value);
					Count();
				}
				}
			} 
			plus_btn_click();
			
			//减按钮
			var list_center = document.getElementById("list_center");
			var sub_btn_click = function(){
				for(let i=0;i<len;i++){
					sub[i].onclick=function(){
						//计算单价
						var old_value = parseInt(quan_input[i].value);
						//若数量小于等于1，按减按钮清除,并重新获取相关元素
						if(old_value<=1){			
							deleteCookie(quan_input[i].getAttribute("data_id"));
							list_center.removeChild(list_item[i]);
							Count();
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

						var unit_price = parseInt(price[i].innerHTML)/old_value;
						var new_value = parseInt(quan_input[i].value)-1;
						quan_input[i].value = new_value;
						price[i].innerHTML = unit_price*new_value;
						modifyNum(quan_input[i].getAttribute("data_id"),new_value);
						Count();
					}
				}
			}
			sub_btn_click();
			
			//清空按钮
			var sidebar_content = document.getElementById("sidebar_content");
			var car_list = document.getElementById("car_list");
			var car_delete = document.getElementById("delete");
			var list_bottom = document.getElementById("list_bottom");
			car_delete.onclick=function(){
				sidebar_content.removeChild(car_list);
				list_bottom.parentNode.removeChild(list_bottom);
				
				var url = "/cart/clear";
				$.ajax({
					"url":url,
					"type":"POST",
					"success":function(data){
						alert(data);
					},
					"error":function(){
						alert("服务器繁忙，请稍后重试");
					},
					"dataType":"text"
				});
			}
			
			
		},
		"error":function(){
			alert("服务器繁忙，请稍后重试");
		},
		"dataType":"json"
	});
}

$(shop_car).on("click",addCar);

$("#creat_crat").click(function(){
	window.location.href="/order/order-cart";
});


//评价选项卡
var shop_comment_tab_item = document.getElementsByClassName("shop_comment_tab_item");
var shop_comment_list = document.getElementsByClassName("shop_comment_list");
for(let i=0,len=shop_comment_tab_item.length;i<len;i++){
	shop_comment_tab_item[i].onclick=function(){
		for(let j=0,len_2=shop_comment_tab_item.length;j<len_2;j++){
			shop_comment_list[j].style.display = "none";
		}
		shop_comment_list[i].style.display = "block";
	}
}
