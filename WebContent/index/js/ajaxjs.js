/**
 *这里是对index中ajax验证的js
 */
$(document).ready(function(){
	/*
	 * 正则表达式验证邮箱函数
	 */
	var valid_email = function(email){
		 var patten = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/); 
		 return patten.test(email); 
	}
	
	$(".button_login").click(function(){
		var useremail = "";
		var userpass = "";
		useremail = $(".loginusername").val();
		userpass = $(".loginuserpasswordp").val();
		
		/*
		 * 登陆验证函数
		 */
		if(valid_email(useremail)){
			$.post("userlogin?useremail="+useremail+"&userpass="+userpass,function(data, textStatus, jqXHR){
				var stringResult = ""
				if(textStatus == "success"){
					if(data == "2"){
						stringResult = "恭喜您，登陆成功，页面正在跳转......";
						window.location.href="userinitpage";
					}else if(data == "1"){
						stringResult = "恭喜您，登陆成功，页面正在跳转......";
						window.location.href="user";
					}else if(data == "0"){
						stringResult = "对不起，该邮箱不存在！";
					}else if(data == "-1"){
						stringResult = "对不起，密码错误！";
					}else {
						stringResult = "警告，查询结果不唯一，系统错误，请告知管理员"
					}
				}else{
					stringResult = "服务器异常,错误码 ："+textStatus;
				}
				$(".ajaxresult").text(stringResult);
			} );
		}else{
			$(".ajaxresult").text("邮箱格式不正确");
		}
	});
});