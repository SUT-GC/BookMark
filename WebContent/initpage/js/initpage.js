$(document).ready(function(){
	$(".button_initsuccess").hide();	
	var resultvar = "";
	var resultint = 0;
	var checkinput  = function(){
		var password = $(".input_key").val();	
		var pwdArray = new Array();
    		var securityLevelFlag = 0;
		if(password.length == 0){
			resultvar = "口令必须输入！";
			resultint = 0;
		}else if(password.length < 6){
			resultvar ="不建议口令长度 < 6，安全性较低！";
			resultint = 1;
		}else{
		        var securityLevelFlagArray = new Array(0, 0, 0, 0);
		        for (var i = 0; i < password.length; i++) {
		            var asciiNumber = password.substr(i, 1).charCodeAt();
		            if (asciiNumber >= 48 && asciiNumber <= 57) {
		                securityLevelFlagArray[0] = 1;  //digital
		            }
		            else if (asciiNumber >= 97 && asciiNumber <= 122) {
		                securityLevelFlagArray[1] = 1;  //lowercase
		            }
		            else if (asciiNumber >= 65 && asciiNumber <= 90) {
		                securityLevelFlagArray[2] = 1;  //uppercase
		            }
		            else {
		                securityLevelFlagArray[3] = 1;  //specialcase
		            }
		        }

		        for (var i = 0; i < securityLevelFlagArray.length; i++) {
		            if (securityLevelFlagArray[i] == 1) {
		                securityLevelFlag++;
		            }
		        }
		        
		        if(securityLevelFlag == 1){
		        	resultvar ="安全性较低";
		        	resultint = 1;
		        }else if(securityLevelFlag == 4){
		        	resultvar = "安全性较高";
		        	resultint = 1;
		        }else{
		        	resultvar = "安全性中级";
		        	resultint = 1;
		        }
		}

		$(".result_content").text(resultvar);
	}

	$(".input_key").bind("blur keyup",function(){
		checkinput();
	});

	$(".toinit").click(function(){
		checkinput();
		if(resultint == 1){
			if(confirm("您确定要初始化这个口令么？")){
				$.post("userinit?keymd5="+$(".input_key").val(), function(data){
					if(data == -1){
						alert("用户口令不能为空！");
					}else if(data == 0){
						alert("系统异常，请联系管理员")
					}else if(data == 1){
						alert("初始化成功！");
						$(".result_content").text("恭喜您，初始化成功！");
						$(".button_initsuccess").show();
						$(".key_group").hide();	
						$(".button_begintouse").click(function(){
							window.location.href="user";
						});
						
					}else{
						alert("网络异常");
					}
				})
			};
		}else{
			confirm("对不起，您不能初始化这个口令！");
		}
	});
});