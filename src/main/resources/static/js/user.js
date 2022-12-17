$(function () {
	$(".Js_closeBtn").click(function () {
	    $(".adduser,.f_delete,.searchuser,.modifyuser").fadeOut(200);
	});
	$(".Js_add").click(function () {
	    $(".adduser").fadeIn(200);
	});
	$(".Js_search").click(function () {
	    $(".searchuser").fadeIn(200);
	});
    $(".Js_edit").click(function () {
    	var user_tr = $(this).parent().parent();
    	$("#modi_id").val(user_tr.children(".userId").text());
    	$("#modi_username").val(user_tr.children(".userUsername").text());
    	$("#modi_password").val(user_tr.children(".userPassword").text());
    	$("#modi_money").val(user_tr.children(".userMoney").text());
    	$("#modi_address").val(user_tr.children(".userAddress").text());
    	$("#modi_tel").val(user_tr.children(".userTel").text());
    	$("#modi_type").val(user_tr.children(".userType").text());
        $(".modifyuser").fadeIn(200);
    });
    $(".Js_delete").click(function () {
    	var user_tr = $(this).parent().parent();
    	$("#delete_id").val(user_tr.children(".userId").text());
        $(".f_delete").fadeIn(200);
    });
});
