var receive_btn = document.getElementsByClassName("receive_btn");
for (var i = 0; i < receive_btn.length; i++) {
	receive_btn[i].onclick = function() {
		var orderId = this.getAttribute("data_id");
		$.ajax({
			url : "/receive/" + orderId,
			"type" : "POST",
			"success" : function(data) {
				alert(data);
				location.href = "/order";
			},
			"error" : function() {
				alert("服务器繁忙，请稍后重试");
			},
			"dataType" : "text"
		});
		return false;
	}
}

var comment_btn = document.getElementsByClassName("comment_btn");
for (var i = 0; i < comment_btn.length; i++) {
	comment_btn[i].onclick = function() {
		location.href = "/comment/" + this.getAttribute("data_id");
		return false;
	}
}