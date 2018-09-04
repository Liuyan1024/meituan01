$(function(){
	$(".edit").click(function(){
		var data = {};
		data.userId = $(this).parent().siblings(":eq(0)").text();
		data.userName = $(this).parent().siblings(":eq(1)").text();
		data.password = $(this).parent().siblings(":eq(2)").text();
		data.babyAge = $(this).parent().siblings(":eq(3)").text();
		data.eamil = $(this).parent().siblings(":eq(4)").text();
		data.phone = $(this).parent().siblings(":eq(5)").text();
		var pa = $(top.document);
		pa.find("#editUser table tr td:eq(0) input").val(data.userId);
		pa.find("#editUser table tr td:eq(1)").text(data.userId);
		pa.find("#editUser table tr td:eq(3) input").val(data.userName);
		pa.find("#editUser table tr td:eq(5) input").val(data.password);
		pa.find("#editUser table tr td:eq(7) select").val(data.babyAge);
		pa.find("#editUser table tr td:eq(9) input").val(data.eamil);
		pa.find("#editUser table tr td:eq(11) input").val(data.phone);
		pa.find("#editUserButton").click();
	});
	
});

