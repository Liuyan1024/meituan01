//点击搜索框，搜索框拉长
var search_input = document.getElementById("search_input");
search_input.onclick=function(){
	search_input.style.width = "306px";
}
//搜索框恢复
search_input.onmouseout=function(){
	search_input.style.width = "236px";
}
//鼠标悬浮向下图标时，显示下拉框
var btn_down = document.getElementById("btn_down");
var dropBox = document.getElementById("dropBox");
btn_down.onmouseenter=function(){
	dropBox.style.display = "block";
}
//鼠标离开下拉框，隐藏下拉框
dropBox.onmouseleave=function(){
	dropBox.style.display = "none";
}
//显示外卖店家细节
var shop_item = document.getElementsByClassName("shop_item");
var shop_detail = document.getElementsByClassName("shop_detail");
var shop_detail_triangle = document.getElementsByClassName("shop_detail_triangle");
var shop_detail_triangle_white = document.getElementsByClassName("shop_detail_triangle_white");
for(let i=0,shop_len=shop_item.length;i<shop_len;i++){
	shop_item[i].onmouseenter=function(){
		if(i%4==0){//外卖细节框的方向在右边
			shop_detail[i].classList.add("detail_right");
			shop_detail_triangle[i].classList.add("shop_detail_triangle_right");
			shop_detail_triangle_white[i].classList.add("shop_detail_triangle_white_right");
			
		}else{
			shop_detail[i].classList.add("detail_left");
			shop_detail_triangle[i].classList.add("shop_detail_triangle_left");
			shop_detail_triangle_white[i].classList.add("shop_detail_triangle_white_left");
		}
		shop_detail[i].style.display="block";
	}
}
//隐藏外卖细节
for(let i=0,shop_len=shop_item.length;i<shop_len;i++){
	shop_item[i].onmouseleave=function(){
		shop_detail[i].style.display="none";
	}
}
