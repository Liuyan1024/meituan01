
//商家服务评价
var comment_icon = document.getElementById("comment_icon");
var comment_tips = document.getElementById("comment_tips");
var comment_icon_arr = comment_icon.getElementsByTagName("li");
//记录评价等级
var count = 0;
for(let i=0,len=comment_icon_arr.length;i<len;i++){
	comment_icon_arr[i].onclick = function comment_icon(){
		count = i+1;
		//清除样式
		for(let j=0,len2=comment_icon_arr.length;j<len2;j++){
			if(comment_icon_arr[j].classList.contains("com_icon_active")){
				comment_icon_arr[j].classList.remove("com_icon_active");			
			}
		}
		switch(i){
			case 0:comment_tips.innerHTML = "极差";break;
			case 1:comment_tips.innerHTML = "失望";break;
			case 2:comment_tips.innerHTML = "一般";
			       comment_tips.style.color="#f3b518";
			       break;
			case 3:comment_tips.innerHTML = "满意";
   				   comment_tips.style.color="#f18b1b";
				    break;
			case 4:comment_tips.innerHTML = "惊喜";
			       comment_tips.style.color="#eb6643";
					break;
		}
		for(let k=0;k<i+1;k++){
			comment_icon_arr[k].classList.add("com_icon_active");
		}
	}
}

var comment_btn = document.getElementById("comment_sub");
comment_btn.onclick=function(){
	var oid = this.getAttribute("data_oid");
	var mid = this.getAttribute("data_mid");
	var comment_text = $(".comment_text").children(":first").val();
//	console.log(count+" "+oid+" "+mid+" "+comment_text);
	$.ajax({
		url:"/comment/add",
		type:"POST",
		data:{"orderid":oid,"merid":mid,"star":count,"comment":comment_text},
		success:function(){
			location="/order";
		},
		error:function(){
			alert("服务器出错！");
		},
		dataType:"text"
	});
}


