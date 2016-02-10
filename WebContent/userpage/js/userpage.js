/**
 * 这里是userpage相关的javascript
 */

$(document).ready(function(){
	/*
	 * 点击按浏览次数按钮排序所执行的函数
	 */
	var showbybit = function(){
		$.post("selectwebinforsbytime?type=0",function(data){
			$(".tbody_showwebinforbybit").empty();
			if(data == -2){
				window.location.href="session";
			}else if(data == -1){
				$(".tbody_showwebinforbybit").append("<td></td><td><span style='color:red; font-size:20px;' >您还未添加任何记录</span></td>");
			}else{
					jsonarray = JSON.parse(data);
					for(var index in jsonarray){
						$(".tbody_showwebinforbybit").append(""
								+"<tr>"
									+"<td>"+index+"</td>"
									+"<td class='webinforid' style='display:none'>"+jsonarray[index].webinforid+"</td>"
									+"<td><a href='"+jsonarray[index].webinforlink+"'target='_blank'>"+jsonarray[index].webinforname.replace(/</g, "&lt")+"</a></td>"
									+"<td class='webinfornum'>"+jsonarray[index].webinfornum+"</td>"
									+"<td>"
										+"<button class='show_button'><span class='glyphicon glyphicon-eye-open 'aria-hidden='true'></span></button>"
									+"</td>"
									+"<td>"
										+"<button class='edit_button'>"
											+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
										+"</button>"
									+"</td>"
									+"<td>"
										+"<button class='del_button'>"
											+"<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
										+"</button>"
									+"</td>"
								+"</tr>");
					}
			}
		});
	}
	/*
	 * 点击按创建时间按钮排序所执行的函数
	 */
	var showbytime = function(){
		$.post("selectwebinforsbytime?type=1",function(data){
			$(".tbody_showwebinforbytime").empty();
			if(data == -2){
				window.location.href="session";
			}else if(data == -1){
				$(".tbody_showwebinforbytime").append("<td></td><td><span style='color:red; font-size:20px;' >您还未添加任何记录</span></td>");
			}else{
					jsonarray = JSON.parse(data);
					for(var index in jsonarray){
						$(".tbody_showwebinforbytime").append(""
								+"<tr>"
									+"<td>"+index+"</td>"
									+"<td class='webinforid' style='display:none'>"+jsonarray[index].webinforid+"</td>"
									+"<td><a href='"+jsonarray[index].webinforlink+"'target='_blank'>"+jsonarray[index].webinforname.replace(/</g,"&lt")+"</a></td>"
									+"<td class='webinfornum'>"+jsonarray[index].webinforcreatetime+"</td>"
									+"<td>"
										+"<button class='show_button'><span class='glyphicon glyphicon-eye-open 'aria-hidden='true'></span></button>"
									+"</td>"
									+"<td>"
										+"<button class='edit_button'>"
											+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
										+"</button>"
									+"</td>"
									+"<td>"
										+"<button class='del_button'>"
											+"<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
										+"</button>"
									+"</td>"
								+"</tr>");
					}
			}
		});
	}
	/*
	 * 初始化显示网站记录
	 */
	showbybit();
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
	$(".button_addnewlabelok").click(function(){
		var labelname = $(".input_labelname").val();
		if(labelname.length == 0){
			alert("输入内容不能为空！");
		}else if(!judge(labelname)){
			alert("输入内容中不能存在空格！");
		}else{
			var iscontinue = true;
			if(labelname.length > 10){
				iscontinue =  confirm("输入内容文字较多，可能会影响显示效果，您是否要继续？");
			}
			if(iscontinue == true){
				$.post("addnewlabel?newlabelname="+labelname, function(data){
					if(data == -1){
						alert("对不起，标签名称不能为空！");
					}else if(data == -2){
						window.location.href="session";
					}else{
						alert("恭喜您，添加成功！");
						labelname = labelname.replace(/</g, "&lt");
						$(".user_body").show();
						$(".cloud_div_add").slideUp(100);
						$(".cloud_baseli").before("<li><div  style='display:none;' class='labelid'>"+data+"</div><a href='#' class='button_selectwbs  labelname'>"+labelname+"</a></li>");
					}
				})
			}
		}
	});
	
	/*
	 * 根据labelid， newlabelname更新label
	 */
	$(".button_oktoeditlabel").click(function(){
		var newlabelname = $(".input_newlabelname").val();
		var labelid = $(".cloud_div_body_id").find("span a").text();
		if(newlabelname.length == 0){
			alert("输入内容不能为空！");
		}else if(!judge(newlabelname)){
			alert("输入内容中不能存在空格！");
		}else{
			var iscontinue = true;
			if(newlabelname.length > 10){
				iscontinue =  confirm("输入内容文字较多，可能会影响显示效果，您是否要继续？");
			}
			iscontinue = confirm("您确定要提交本次修改么？");
			if(iscontinue == true){
				$.post("updatelabel?newlabelname="+newlabelname+"&labelid="+labelid, function(data){
					if(data == -1){
						alert("修改失败，数据库操作异常，请联系管理员！");
					}else if(data == -2){
						window.location.href="session";
					}else if(data == 1){
						
						$(".editlabel_tbody tr").each(function(){
							if($(this).find(".labelid").text() == labelid){
								$(this).find(".labelname").text(newlabelname);
							}
						});
						
						var isreflush = alert("修改成功！");
						$(".user_body").show();
						$(".cloud_div_edit").slideUp(100);
					}else{
						alert(data);
						alert("链接数据库与服务器失败！");
					}
				});
			}
		}
	});
	
	/*
	 * 根据labelid删除label
	 * 删除该标签
	 */
	$(".editlabel_tbody").on("click",".cloudedit_del_button",function(){
		if(confirm("您确定要删除这个标签吗？")){
			var parent = $(this).parent();
			var parentt= parent.parent();
			var labelid = parentt.find(".labelid").text();
			$.post("deletelabel?labelid="+labelid, function(data){
				if(data == 1){
					alert("删除成功！");
					parentt.remove();
				}else if(data == -2){
					window.location.href="session";
				}else if(data == -1){
					alert("删除异常，数据库操作错误，请联系管理员！");
				}else{
					alert("服务器与数据库链接失败！");
				}
			})
		}
	});
	
	/*
	 * 根据labelname和userid查询labels
	 */
	$(".button_selectlabelname").click(function(){
		var labelname = $(".input_selectlabelname").val();
		$.post("selectlabelsbyuseridandlabelname?labelname="+labelname,function(data){
			$(".cloud_ul").empty();
			if(data == -1){
				$(".cloud_ul").html("<div style='color:red'>对不起，没有搜索到相标签</div>");
				$(".input_selectlabelname").val("");
			}else if(data == 0){
				$(".cloud_ul").html("<div style='color:red'>对不起，链接服务器失败</div>");
				$(".input_selectlabelname").val("");
			}else if(data == -2){
				window.location.href="session";
			}else{
				$(".cloud_ul").append("<li class='cloud_baseli'></li>");
				var jsonarray = JSON.parse(data);
				for(var index in jsonarray){
					$(".cloud_baseli").before("<li><div  style='display:none;' class='labelid'>"+jsonarray[index].labelid+"</div><a href='#' class='button_selectwbs labelname'>"+jsonarray[index].labelname+"</a></li>");
					$(".input_selectlabelname").val("");
				}
			}
		});
	});
	
	
	
	
	/*
	 * 0:数据库未链接 
	 * -1:口令错误
	 * 1:添加成功 
	 * -2:必填项目不能为空
	 */
	$(".button_submitwebinfor").click(function(){
		if($(".input_webname").val() == ""){
			alert("网站名条不能为空");
		}else if($(".input_weblink").val() == ""){
			alert("网站网址不能为空");
		}else if(($(".input_webaccount").val() != "" || $(".input_webpassword").val() != "") && $(".input_password").val() == ""){
			alert("账号或密码填写之后，必须填写口令才可提交")
		}else{
			$(".buttons_addwebinfor").hide();
			$(".isaddwebinforing").show();
			infordata = $(".form_inputweb").serialize()
			$.post("addwebinfor",infordata, function(data){
				$(".isaddwebinforing").hide();
				$(".buttons_addwebinfor").show();
				if(data == -1){
					alert("口令错误，请重新输入口令");
				}else if(data == -2){
					alert("填写格式不正确，请检查必填项目与某些内容的填写规定");
				}else if(data == 0){
					alert("与服务器链接失败");
				}else{
					alert("添加成功 ");
					$(".user_body").show(300);
					$(".tol_add").slideUp(200);
					showbytime();
					showbybit();
				}
			});
		}
	});
	
	
	$(".selectshowbytime a").click(function(){
		showbytime();
	});
	$(".selectshowbybits a").click(function(){
		showbybit();
	});
	
	$(".cloud_ul").on("click", ".button_selectwbs", function(){
		var parent = $(this).parent();
		var labelid = parent.find(".labelid").text();
		var type = 0;
		if($(".selectshowbytime").hasClass("active")){
			type = 1;
		}
		if($(".selectshowbybit").hasClass("active")){
			type = 0;
		}
		$.post("selectwebinforsbylabelid?labelid="+labelid+"&type="+type, function(data){
			if(data == 0){
				if(type == 1){
					$(".tbody_showwebinforbytime").empty();
					$(".tbody_showwebinforbytime").prepend("<td></td><td><span style='color:red; font-size:20px;'>与数据库链接异常</span></td>");
				}else{
					$(".tbody_showwebinforbybit").empty();
					$(".tbody_showwebinforbybit").prepend("<td></td><td><span style='color:red; font-size:20px;'>与数据库链接异常</span></td>");
				}
			}else if (data == -2){
				window.location.href="session";
			}else if (data == -1){
				if(type == 1){
					$(".tbody_showwebinforbytime").empty();
					$(".tbody_showwebinforbytime").prepend("<td></td><td><span style='color:red; font-size:20px;'>该标签下没有记录</span></td>");
				}else{
					$(".tbody_showwebinforbybit").empty();
					$(".tbody_showwebinforbybit").prepend("<td></td><td><span style='color:red; font-size:20px'>该标签下没有记录</span></td>");
				}
			}else{
				if(type == 1){
					$(".tbody_showwebinforbytime").empty();
					jsonarray = JSON.parse(data);
					for(var index in jsonarray){
						$(".tbody_showwebinforbytime").append(""
								+"<tr>"
									+"<td>"+index+"</td>"
									+"<td class='webinforid' style='display:none'>"+jsonarray[index].webinforid+"</td>"
									+"<td><a href='"+jsonarray[index].webinforlink+"'target='_blank'>"+jsonarray[index].webinforname+"</a></td>"
									+"<td class='webinforcreatetime'>"+jsonarray[index].webinforcreatetime+"</td>"
									+"<td>"
										+"<button class='show_button'><span class='glyphicon glyphicon-eye-open 'aria-hidden='true'></span></button>"
									+"</td>"
									+"<td>"
										+"<button class='edit_button'>"
											+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
										+"</button>"
									+"</td>"
									+"<td>"
										+"<button class='del_button'>"
											+"<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
										+"</button>"
									+"</td>"
								+"</tr>");
					}
				}else{
					$(".tbody_showwebinforbybit").empty();
					jsonarray = JSON.parse(data);
					for(var index in jsonarray){
						$(".tbody_showwebinforbybit").append(""
								+"<tr>"
									+"<td>"+index+"</td>"
									+"<td class='webinforid' style='display:none'>"+jsonarray[index].webinforid+"</td>"
									+"<td><a href='"+jsonarray[index].webinforlink+"'target='_blank'>"+jsonarray[index].webinforname+"</a></td>"
									+"<td class='webinfornum'>"+jsonarray[index].webinfornum+"</td>"
									+"<td>"
										+"<button class='show_button'><span class='glyphicon glyphicon-eye-open 'aria-hidden='true'></span></button>"
									+"</td>"
									+"<td>"
										+"<button class='edit_button'>"
											+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
										+"</button>"
									+"</td>"
									+"<td>"
										+"<button class='del_button'>"
											+"<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
										+"</button>"
									+"</td>"
								+"</tr>");
					}
				}
			}
		});
		
	})
	$(".tbody_showwebinforbytime").on("click", ".del_button", function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		var deltr = $(this).parent().parent();
		if(confirm("您确定确定要删除该记录么？")){
			$.post("deletewebinforbyid?webinforid="+webinforid, function(data){
				if(data == 0){
					alert("链接数据库失败");
				}else if(data == -2){
					window.location.href="session";
				}else if(data == 1){
					alert("删除成功");
					deltr.hide();
				}
			});
		}
	})
	$(".tbody_showwebinforbybit").on("click", ".del_button", function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		var deltr = $(this).parent().parent();
		if(confirm("您确定确定要删除该记录么？")){
			$.post("deletewebinforbyid?webinforid="+webinforid, function(data){
				if(data == 0){
					alert("链接数据库失败");
				}else if(data == -2){
					window.location.href="session";
				}else if(data == 1){
					alert("删除成功");
					deltr.hide();
				}
			});
		}
	})
	/*
	 * 显示webinfor信息
	 */
	var showwebinforbywebinforid = function(webinforid){
		$.post("selectwebinforbywebinforid?webinforid="+webinforid, function(data){
			webinfor = JSON.parse(data);
			var webname = webinfor[0].webname;
			var weblink = webinfor[0].weblink;
			var webdes = webinfor[0].webdescribe;
			var weblabels = webinfor[1];
			$(".tol_show_name").empty();
			$(".tol_show_name").append("<span>网站名条:</span>")
			$(".tol_show_name").append("<div class='selecttowebinforid' style='display:none;'>"+webinforid+"</div>");
			$(".tol_show_name").append("<pre>"+webname+"</pre>")
			$(".tol_show_link pre").html("<a href="+weblink+" target='_blank'>"+weblink+"</a>");
			$(".tol_show_dis pre").text(webdes);
			$(".show_label_each pre").empty();
			if(weblabels.length == 0){
				$(".show_label_each pre").text("没有关联标签");
			}else{
				for(index in weblabels){
					$(".show_label_each pre").append("<span class='glyphicon glyphicon-tag' aria-hidden='true'>"+weblabels[index].labelname+"</span>");
				}
			}
		});
	}
	$(".tbody_showwebinforbybit").on("click",".show_button",function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		$(".user_body").hide(300);
		$(".tol_show").slideDown(300);
		showwebinforbywebinforid(webinforid);
	})
	$(".tbody_showwebinforbytime").on("click",".show_button",function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		$(".user_body").hide(300);
		$(".tol_show").slideDown(300);
		showwebinforbywebinforid(webinforid);
	})
	
	/*
	 * 输入口令查看账号密码
	 * 0:数据库链接失败
	 * -1:口令不正确
	 * -2:session过期
	 * 2:没有保存账号密码
	 */
	$(".button_submitkey").click(function(){
		var key = $(".input_submitkey").val();
		var webinforid = $(".selecttowebinforid").text();
		if(key.length == 0){
			alert("输入的口令不能为空");
		}else{
			$(".show_pass_div").html("<span style='color:red;font-size:20px'>正在查询...</span>");
			$.post("selectpassandacount?webinforid="+webinforid+"&key="+key, function(data){
				if(data == 0){
					$(".show_pass_div").html("<span style='color:red;font-size:20px'>链接数据库失败</span>");
				}else if(data == -2){
					window.location.href="session";
				}else if(data == -1){
					$(".show_pass_div").html("<span style='color:red;font-size:20px'>口令不正确</span>");
				}else if(data == 2){
					$(".show_pass_div").html("<span style='color:red;font-size:20px'>没有保存账号密码</span>");
				}else{
					var accpass = data.split("_BookMark_");
					var acc = accpass[0];
					var pass = accpass[1];
					acc = acc.replace(/</g, "&lt");
					pass = pass.replace(/</g, "&lt");
					$(".pre_show_acc").text(acc);
					$(".pre_show_pass").text(pass);
					$(".show_pass_div").html("<span>您的账号为:</span><pre>"+acc+"</pre><span>您的密码为:</span><pre>"+pass+"</pre>");
				}
				
			});
		}
	});
	$(".tol_showpass").click(function(){
		$(".show_pass_div").empty();
	});
	
	/*
	 * 编辑网站记录
	 */
	var findlabelid = function(labelid,labels){
		for (index in labels){
			if(labels[index].labelid == labelid){
				return true;
			} 
		}
		return false;
	}
	var editwebinfor = function(webinforid){
		$.post("selectwebinforbywebinforid?webinforid="+webinforid, function(data){
			webinfor = JSON.parse(data);
			var webname = webinfor[0].webname;
			var weblink = webinfor[0].weblink;
			var webdes = webinfor[0].webdescribe;
			var weblabels = webinfor[1];
			$(".edit_webinforid").text(webinforid);
			$(".input_editwebinforname").val(webname);
			$(".input_editwebinforlink").val(weblink);
			if(webdes == "您没有填写相关描述"){
				$(".input_editwebinfordes").val("");
			}else{
				$(".input_editwebinfordes").val(webdes);
			}
			$(".edit_alllabels").empty();
			$(".cloud_ul").find("li").not(".cloud_baseli").each(function(){
				var labelid = $(this).find(".labelid").text();
				var labelname = $(this).find(".labelname").text();
				if(findlabelid(labelid,weblabels)){
					$(".edit_alllabels").append("<label><div class='labelid' style='display:none'>"+labelid+"</div><input class='checkbox_selectlabel' type='checkbox' checked='checked'>"+labelname+"&nbsp;&nbsp;</label>");
				}else{
					$(".edit_alllabels").append("<label><div class='labelid' style='display:none'>"+labelid+"</div><input class='checkbox_selectlabel' type='checkbox'>"+labelname+"&nbsp;&nbsp;</label>");
				}
			});
		});
	}
	$(".tbody_showwebinforbybit").on("click",".edit_button",function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		editwebinfor(webinforid);
		$(".input_editwebinforacc").val("");
		$(".input_editwebinforpass").val("");
		$(".input_editwebinforpassword").val("");
		$(".user_body").hide(300);
		$(".tol_edit").slideDown(300);
	});
	$(".tbody_showwebinforbytime").on("click",".edit_button",function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		editwebinfor(webinforid);
		$(".user_body").hide(300);
		$(".tol_edit").slideDown(300);
	});
	/*
	 * 关联/除去关联标签
	 */
	$(".edit_alllabels").on("change",".checkbox_selectlabel",function(){
		var type;
		var count = false;
		if($(this).is(":checked")){
			type = 1;
			count = (confirm("你确定要关联这个标签么？"));
			if(!count){
				$(this).prop("checked",false);
			}
		}else{
			type = 0;
			count = (confirm("你确定要取消关联这个标签么？"));
			if(!count){
				$(this).prop("checked",true);
			}
		}
		var webinforid = $(".tol_edit_body_all .edit_webinforid").text();
		var labelid = $(this).parent().find(".labelid").text();

		if(count == true){
			$.post("deleteoraddlabelandwebinfor?webinforid="+webinforid+"&labelid="+labelid+"&type="+type, function(data){
				if(data == -2){
					window.location.href="session";
				}else if(data == 1){
					
				}else{
					alert("数据库链接失败");
				}
			});
		}
	});
	
	/*
	 * 提交修改
	 */
	$(".button_submiteditwebinfor").click(function(){
		var webname = $(".input_editwebinforname").val();
		var weblink = $(".input_editwebinforlink").val();
		var webdes = $(".input_editwebinfordes").val();
		var webid = $(".edit_webinforid").text();
		var webacc = $(".input_editwebinforacc").val();
		var webpass = $(".input_editwebinforpass").val();
		var webkey = $(".input_editwebinforpassword").val();
		if(webname.length == 0){
			alert("网站名条不能为空");
		}else if(weblink.length == 0){
			alert("网站链接不能为空");
		}else if((webpass != "" || webacc != "") && webkey == ""){
			alert("如果修改网站账号或者密码，必须输入口令");
		}else{
			$.post("updatawebinfor?webinforname="+webname+"&webinforlink="+weblink+"&webinfordes="+webdes+"&webinforid="+webid+"&webinforacc="+webacc+"&webinforpass="+webpass+"&webinforkey="+webkey, function(data){
				if(data == -2){
					window.location.href="session";
				}else if(data == -1){
					alert("口令错误");
				}else if(data == 1){
					alert("修改成功！");
					$(".user_body").show(300);
					$(".tol_edit").slideUp(300);
				}else{
					alert("与服务器链接异常，请联系管理员");
				}
			});
		}
	});
	
	/*
	 * 点击链接添加点击数 
	 */
	$(".tbody_showwebinforbybit").on("click","a",function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		var webinfornum = $(this).parent().parent().find(".webinfornum").text();
		webinfornum ++;
		$(this).parent().parent().find(".webinfornum").text((webinfornum));
		$.post("updatawebinfornum?webinforid="+webinforid);
	})
	/*
	 * 点击链接添加点击数 
	 */
	$(".tbody_showwebinforbytime").on("click","a",function(){
		var webinforid = $(this).parent().parent().find(".webinforid").text();
		var webinfornum = $(this).parent().parent().find(".webinfornum").text();
		$.post("updatawebinfornum?webinforid="+webinforid, function(data){
			
		});
	})
		
	/*
	 * 模糊搜索网站名称
	 */
	var selectwebinforlikenameorderbybit = function(webinforname){
		$(".tbody_showwebinforbybit").empty();
		$.post("selectwebinforlikename?webinforname="+webinforname+"&type=0", function(data){
			if(data == -2){
				window.location.href="session";
			}else if(data == -1){
				$(".tbody_showwebinforbybit").append("<td></td><td><span style='color:red; font-size:20px;' >没有查询能到结果</span></td>");
			}else if(data == 0){
				$(".tbody_showwebinforbybit").append("<td></td><td><span style='color:red; font-size:20px;' >与数据库链接异常</span></td>");
			}else{
				jsonarray = JSON.parse(data);
				for(var index in jsonarray){
					$(".tbody_showwebinforbybit").append(""
							+"<tr>"
								+"<td>"+index+"</td>"
								+"<td class='webinforid' style='display:none'>"+jsonarray[index].webinforid+"</td>"
								+"<td><a href='"+jsonarray[index].webinforlink+"'target='_blank'>"+jsonarray[index].webinforname+"</a></td>"
								+"<td class='webinfornum'>"+jsonarray[index].webinfornum+"</td>"
								+"<td>"
									+"<button class='show_button'><span class='glyphicon glyphicon-eye-open 'aria-hidden='true'></span></button>"
								+"</td>"
								+"<td>"
									+"<button class='edit_button'>"
										+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
									+"</button>"
								+"</td>"
								+"<td>"
									+"<button class='del_button'>"
										+"<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
									+"</button>"
								+"</td>"
							+"</tr>");
				}
				$(".input_selectwebinforname").val("");
			}
		});
	};
	
	var selectwebinforlikenameorderbytime = function(webinforname){
		$(".tbody_showwebinforbytime").empty();
		$.post("selectwebinforlikename?webinforname="+webinforname+"&type=1", function(data){
			if(data == -2){
				window.location.href="session";
			}else if(data == -1){
				$(".tbody_showwebinforbytime").append("<td></td><td><span style='color:red; font-size:20px;' >没有查询能到结果</span></td>");
			}else if(data == 0){
				$(".tbody_showwebinforbytime").append("<td></td><td><span style='color:red; font-size:20px;' >与数据库链接异常</span></td>");
			}else{
				jsonarray = JSON.parse(data);
				for(var index in jsonarray){
					$(".tbody_showwebinforbytime").append(""
							+"<tr>"
								+"<td>"+index+"</td>"
								+"<td class='webinforid' style='display:none'>"+jsonarray[index].webinforid+"</td>"
								+"<td><a href='"+jsonarray[index].webinforlink+"'target='_blank'>"+jsonarray[index].webinforname+"</a></td>"
								+"<td class='webinforcreatetime'>"+jsonarray[index].webinforcreatetime+"</td>"
								+"<td>"
									+"<button class='show_button'><span class='glyphicon glyphicon-eye-open 'aria-hidden='true'></span></button>"
								+"</td>"
								+"<td>"
									+"<button class='edit_button'>"
										+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
									+"</button>"
								+"</td>"
								+"<td>"
									+"<button class='del_button'>"
										+"<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
									+"</button>"
								+"</td>"
							+"</tr>");
				}
				$(".input_selectwebinforname").val("");
			}
		});
	};
	$(".button_selectwebinfor").click(function(){
			var webinforname = $(".input_selectwebinforname").val();
			var type = 0;
			if($(".selectshowbytime").hasClass("active")){
				type = 1;
				selectwebinforlikenameorderbytime(webinforname);
			}
			if($(".selectshowbybits").hasClass("active")){
				type = 0;	
				selectwebinforlikenameorderbybit(webinforname);
				
			}

		});
});


