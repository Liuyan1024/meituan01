$(function(){
	$(".navLeft table tr").click(function(){
		$(".navLeft table tr").removeClass("active");
		$(this).addClass("active");
	});
	$("#editForm").submit(function(){
		$("#editUser").modal("hide");
	});
	$("#uploadStoryForm").submit(function(){
		$("#uploadStory").modal("hide");
	});
	$("#uploadMusicForm").submit(function(){
		$("#uploadMusic").modal("hide");
	});
	$("#uploadStudyForm").submit(function(){
		$("#uploadStudy").modal("hide");
	});
	$("#addUserForm").submit(function(){
		$("#addUser").modal("hide");
	});
	$("#problemForm").submit(function(){
		$("#addproblem").modal("hide");
	});
	$("#editAdminForm").submit(function(){
		$("#editAdmin").modal("hide");
		$.post('http://localhost:8088/BabyPlan/userinfo?method=editAdmin',$(this).serialize(),function(r){
			if (r == "success") {
				$("#editAdmin").modal('hide');
				location.href="http://localhost:8088/BabyPlan/logout";
			}
		});
		return false;
	});
	$("#studyType").change(function(){
		var str = "";
		var $studyType = $("#studyType").val();
		var $studyStep = new Array();
		$studyStep["国学启蒙"] = new Array("三字经","学习唐诗");
		$studyStep["快乐拼音"] = new Array("认识声母","认识韵母","整体认读");
		$studyStep["轻松识字"] = new Array("中文识字卡","中文词组卡","英文单词卡");
		$studyStep["趣味英语"] = new Array("学英语","英文字母歌");
		$studyStep["少儿科普"] = new Array("奇趣自然","趣味科普","少儿数学");
		for(var j = 0; j < $studyStep[$studyType].length; j++){
			str += "<option value='"+ $studyStep[$studyType][j] +"'>"+$studyStep[$studyType][j]+"</option>";
		}
		$("#studyStep").html(str);
	});
	
});