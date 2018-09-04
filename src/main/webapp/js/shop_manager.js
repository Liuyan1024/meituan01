//显示店家信息框
var  shop_info = document.getElementById("shop_info");
var shop_info_extra = document.getElementById("shop_info_extra");
shop_info.onmouseover=function(){
	shop_info_extra.style.display = "block";
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


//食物，评价选项卡
var shop_main_tab_item = document.getElementsByClassName("shop_main_tab_item");
var shop_main_left_item = document.getElementsByClassName("shop_main_left_item");
for(let i=0,len=shop_main_tab_item.length;i<len;i++){
	shop_main_tab_item[i].onclick=function(){
		for(let j=0,len_2=shop_main_tab_item.length;j<len_2;j++){
			shop_main_left_item[j].style.display = "none";
		}
		if(i==1){
			$("#order_content").html("");
			var url = "/mer/order";
			$.ajax({
				"url":url,
				"type":"POST",
				"success":function(data){
//					alert(data);
//					console.log(data);
					for(var i=0;i<data.length;i++){
						var orderId=data[i].orderId;
						var payment=data[i].payment;
						var status=data[i].status;
						var createTime=new Date(data[i].createTime).Format("yyyy-MM-dd hh:mm:ss");
						var userId=data[i].userId;
						var statusinf;
						var content;
						if(status==2){
							statusinf="已付款";
							content="<tr class=\"order_tr\"><td class=\"order_time\">"+createTime+"</td><td class=\"order_shop_image\"><img src=\"/image/items/"+orderId+".jpg\"></td><td class=\"order_info\"><p class=\"order_info_food\"><p claa=\"order_no\">订单号: "+orderId+"</p></td><td class=\"order_amount\">"+payment+"</td><td class=\"order_status\">"+statusinf+"</td><td class=\"order_detail\"><a onclick=\"return modifyStatus(this,"+orderId+")\"><span class=\"order_detail_btn\">配送</span></a></td></tr>";
						}else if(status==3){
							statusinf="配送中";
							content="<tr class=\"order_tr\"><td class=\"order_time\">"+createTime+"</td><td class=\"order_shop_image\"><img src=\"/image/items/"+orderId+".jpg\"></td><td class=\"order_info\"><p class=\"order_info_food\"><p claa=\"order_no\">订单号: "+orderId+"</p></td><td class=\"order_amount\">"+payment+"</td><td class=\"order_status\">"+statusinf+"</td><td class=\"order_detail\"></td></tr>";
						}else if(status==4){
							statusinf="已完成";
							content="<tr class=\"order_tr\"><td class=\"order_time\">"+createTime+"</td><td class=\"order_shop_image\"><img src=\"/image/items/"+orderId+".jpg\"></td><td class=\"order_info\"><p class=\"order_info_food\"><p claa=\"order_no\">订单号: "+orderId+"</p></td><td class=\"order_amount\">"+payment+"</td><td class=\"order_status\">"+statusinf+"</td><td class=\"order_detail\"></td></tr>";
						}else if(status==5){
							statusinf="已评论";
							content="<tr class=\"order_tr\"><td class=\"order_time\">"+createTime+"</td><td class=\"order_shop_image\"><img src=\"/image/items/"+orderId+".jpg\"></td><td class=\"order_info\"><p class=\"order_info_food\"><p claa=\"order_no\">订单号: "+orderId+"</p></td><td class=\"order_amount\">"+payment+"</td><td class=\"order_status\">"+statusinf+"</td><td class=\"order_detail\"></td></tr>";
						}
//						console.log(orderId,payment,status,createTime,userId);
						$("#order_content").append(content);
					}
					
				},
				"error":function(){
					alert("服务器繁忙，请稍后重试");
				},
				"dataType":"json"
			});
		}
		shop_main_left_item[i].style.display = "block";
	}
}

function modifyStatus(obj,orderId){
	$.ajax({
		"url":"/mer/update/"+orderId,
		"type":"POST",
		"success":function(data){
			$(obj).parent().parent().children(":eq(4)").html("配送中").next().html("");
		},
		"error":function(){
			alert("服务器繁忙，请稍后重试");
		},
		"dataType":"text"
	});
	return false;
}

//隐藏店家信息框
shop_info.onmouseout=function(){
	shop_info_extra.style.display = "none";
}
var zhezhao = document.getElementById("zhezhao");
var add_btn = document.getElementById("add_food");
var adressdialog_add = document.getElementById("adressdialog_add");
add_btn.onclick  = function(){
	zhezhao.style.display = "block";
	adressdialog_add.style.display = "block";
}

//关闭添加地址弹出层
var add_close_btn = document.getElementById("add_close_btn");
function close_modify_fun(){
	adressdialog_add.style.display = "none";
	zhezhao.style.display = "none";
}

add_close_btn.onclick = close_modify_fun

var modify_cancel_btn = document.getElementById("modify_cancel_btn");
modify_cancel_btn.onclick = close_modify_fun;

var add_food_btn = document.getElementById("from_btn from_yes");
add_food_btn.onclick=function(){
	document.form.submit();
}

var delete_btn = document.getElementsByClassName("food_item_button_delete");
for(var i=0;i<delete_btn.length;i++){
	delete_btn[i].onclick=function(){
		var orderId=this.getAttribute("data_id");
		if(confirm("确认要删除吗？")){
			$.ajax({
				"url":"/mer/delete/"+orderId,
				"type":"POST",
				"success":function(data){
					location.reload();
					alert(data);
				},
				"error":function(){
					alert("服务器繁忙，请稍后重试");
				},
				"dataType":"text"
			});
		}
	}
	
}