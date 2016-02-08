/**
 * 
 */
$(document).ready(function(){
	$(".div_edit_key_doing").hide();
	$(".div_edit_key").hide();
	$(".div_edit_pass_doing").hide();
	$(".div_edit_pass").hide();
	$(".button_close").click(function(){
		$(".div_edit_key").slideUp(200);
		$(".div_edit_pass").slideUp(200);
	});
	$(".button_edit_pass").click(function(){
		$(".div_edit_key").hide(200);
		$(".div_edit_pass").slideDown(200);
	});
	$(".button_edit_key").click(function(){
		$(".div_edit_pass").hide(200);
		$(".div_edit_key").slideDown(200);
	});
	$(".button_exit").click(function(){
		window.location.href="user";
	});
	
	var changeIconToOk= function(divclass){
		$("."+divclass+" .has-feedback").removeClass("has-error");
		$("."+divclass+" .has-feedback").addClass("has-success");
		$("."+divclass+"  .form-control-feedback").removeClass("glyphicon-remove");
		$("."+divclass+" .form-control-feedback").addClass("glyphicon-ok");
	}
	var changeIconToError = function(divclass){
		$("."+divclass+" .has-feedback").removeClass("has-success");
		$("."+divclass+".has-feedback").addClass("has-error");
		$("."+divclass+" .form-control-feedback").removeClass("glyphicon-ok");
		$("."+divclass+" .form-control-feedback").addClass("glyphicon-remove");
	}
	var changerInputToUse = function(){
		$(".div_newpass1 input").removeAttr("readonly");
		$(".div_newpass2 input").removeAttr("readonly");
	}
	var changeInputToNotUse = function(){
		$(".div_newpass1 input").attr("readonly","readonly");
		$(".div_newpass2 input").attr("readonly","readonly");
	}
	$(".input_oldpass").on("keyup", function(){
		var oldpass = $(".input_oldpass").val();
		if(oldpass == ""){
			$(".div_edit_pass .input_oldpass_result").text("输入不能为空");
		}else{
			$(".div_edit_pass .input_oldpass_result").text("正在检测中....");
			$.post("checkpass?oldpass="+oldpass,function(data){
				var result = "与服务器链接失败";
				if(data == -1){
					changeInputToNotUse();
					result = "密码错误";
					changeIconToError("div_oldpass");
					$(".button_okedit").attr("disabled","disabled");
				}
				if(data == -2){
					changeInputToNotUse();
					window.location.href="session";
					$(".button_okedit").attr("disabled","disabled");
				}
				if(data == 1){
					changeIconToOk("div_oldpass");
					changerInputToUse();
					result = "验证成功！";
					
				}
				if(data == 0){
					changeInputToNotUse();
					result = "与服务器链接失败";
					changeIconToError("div_edit_pass");
					$(".button_okedit").attr("disabled","disabled");
				}
				$(".div_edit_pass .input_oldpass_result").text(result);
			});
		}
	});
	
	/*
	 * 如果存在空格，则返回fasle，如果不存在空格，则返回true
	 */
	var judge = function(str){
		if(str.match(" ") != null){
			return false;
		}else{
			return true;
		}
	}
	$(".div_newpass1 input").on("keyup", function(){
		var newpass1 = $(".div_newpass1 input").val();
		if(newpass1 == ""){
			$(".div_newpass1 .input_result").text("输入不能为空");
			changeIconToError("div_newpass1");
		}else if(!judge(newpass1)){
			$(".div_newpass1 .input_result").text("密码中不能存在空格");
			changeIconToError("div_newpass1");
		}else{
			$(".div_newpass1 .input_result").text("密码格式正确");
			changeIconToOk("div_newpass1");
		}
	})
	
	$(".div_newpass2 input").on("keyup", function(){
		var newpass1 = $(".div_newpass1 input").val();
		var newpass2 = $(".div_newpass2 input").val();
		if(newpass1 == newpass2){
			$(".div_newpass2 .input_result").text("密码格式正确");
			$(".button_okedit").removeAttr("disabled");
			changeIconToOk("div_newpass2");
		}else{
			$(".div_newpass2 .input_result").text("两次输入密码不一致");
			$(".button_okedit").attr("disabled","disabled");
			changeIconToError("div_newpass2");
		}
	});
	
	$(".button_okedit").click(function(){
		var newpass1 = $(".div_newpass1 input").val();
		var newpass2 = $(".div_newpass2 input").val();
		if(newpass1 != newpass2){
			alert("两次输入密码不一致，请进行修改...");
		}else{
			$(".div_edit_pass_doing").show();
			$(".div_edit_pass").hide();
			$.post("updatepass?newpass1="+newpass1+"&newpass2="+newpass2, function(data){
				if(data == 1){
					$(".div_edit_pass_doing span").text("修改成功！");
				}else if(data == -2){
					window.location.href="session";
				}else if(data == -1){
					$(".div_edit_pass_doing span").text("两次密码输入不一致！");
				}else{
					$(".div_edit_pass_doing span").text("服务器链接失败");
				}
			});
		}
	});
});