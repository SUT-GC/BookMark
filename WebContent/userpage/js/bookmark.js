$(document)
		.ready(
				function() {
					$(".tol_show").hide();
					$(".show_pass").hide();
					$(".tol_edit").hide();
					$(".button_appendeditlabel").hide();
					$(".cloudedit").hide();
					$(".cloud_div_edit").hide();
					$(".tol_add").hide();
					$(".cloud_div_add").hide();
					$(".button_addcloud").attr("disabled", false);
					$(".button_selectlabelname").attr("disabled", false);
					$(".isaddwebinforing").hide()

					$(".tol_close").click(function() {
						$(".user_body").show(300);
						$(".tol_show").slideUp(200);
						$(".tol_show_body_all").show();
						$(".show_pass").hide();
					});
					$(".tol_showpass").click(function() {
						$(".show_pass").show();
						$(".tol_show_body_all").hide();
					});
					/*
					 * 修改标签按钮功能
					 */
					$(".button_editlabel")
							.click(
									function() {
										$(".button_appendeditlabel").show();
										$(this).hide();
										$(".cloud").hide();
										$(".editlabel_tbody").empty();
										var labels = new Array();
										var i = 0;
										$(".cloud_ul li").each(
												function() {
													labels[i] = new Array();
													labels[i][0] = $(this)
															.find(".labelid")
															.text();
													labels[i][1] = $(this)
															.find(".labelname")
															.text().replace(/</g, "&lt");;
													i++;
												});
										for (var j = 0; j < i - 1; j++) {
											$(".editlabel_tbody")
													.prepend(
															"<tr>"
																+"<td>"
																	+"<div style='display:none' class='labelid'>"+ labels[j][0]+ "</div><div class='button_selectwbs labelname'>"+ labels[j][1]+"</div>"
																+ "</td>"
																+"<td>"
																	+"<button class='cloudedit_edit_button'>"
																		+ "<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
																	+"</button>"
																+"</td>"
																+ "<td>"
																	+"<button class='cloudedit_del_button'>"
																		+ "<span class='glyphicon glyphicon-remove' aria-hidden='true'>"
																	+ "</button>"
																+ "</td>"
															+ "</tr>");
										}
										$(".cloudedit").slideDown(200);
										$(".button_addcloud").attr("disabled",
												true);
										$(".button_selectlabelname").attr("disabled",
												true);
									});
					

					
					$(".button_appendeditlabel").click(function() {
						$(".button_appendeditlabel").hide();
						$(".button_editlabel").show();
						$(".cloud").slideDown(200);
						$(".cloudedit").hide();
						
						var labels = new Array();
						var i = 0;
						$(".editlabel_tbody tr").each(function(){
							labels[i] = new Array();
							labels[i][0] =  $(this).find(".labelid").text();
							labels[i][1] =  $(this).find(".labelname").text().replace(/</g,"&lt");
							i++;
						});
						$(".button_addcloud").attr("disabled", false);
						$(".button_selectlabelname").attr("disabled", false);
						$(".cloud_ul").empty();
						for(var j = 0; j < i; j++){
							$(".cloud_ul").prepend("<li><div  style='display:none;' class='labelid'>"+labels[j][0]+"</div><a href='#' class='button_selectwbs labelname'>"+labels[j][1]+"</a></li>");
						}
						$(".cloud_ul").append("<li class='cloud_baseli'></li>");
					});
					
					$(".tol_close").click(function() {
						$(".user_body").show(300);
						$(".tol_edit").slideUp(300);
						$(".tol_add").slideUp(200);
					});
					/*
					 * cloudedit_edit_button修改标签的button
					 */
					$(".editlabel_tbody").on("click",".cloudedit_edit_button",function() {
						$(".user_body").hide();
						$(".cloud_div_edit").slideDown(100);
						var parentt = $(this).parent().parent();
						var labelid = parentt.find(".labelid").text();
						var labelname = parentt.find(".labelname").text();
						$(".cloud_div_body_id").find("span a").text(labelid);
						$(".cloud_div_body_oldname").find("span a").text(labelname);
					});
					$(".body_submitbuttom").click(function() {
						$(".user_body").show();
						$(".cloud_div_edit").slideUp(100);
					});

					/*
					 * 添加记录按钮时间
					 */
					$(".add_record").click(function() {
						$(".user_body").hide(300);
						$(".checkbox_alllabels").empty();
						$(".checkbox_alllabels").text("正在查询可选标签...");
						$(".tol_add").slideDown(200);
						$(".input_webname").val("");
						$(".input_weblink").val("");
						$(".input_webdescribe").val("");
						$.post("selectlabelsbyuseridandlabelname?labelname=", function(data){
							$(".checkbox_alllabels").empty();
							if(data == 0){
								$(".checkbox_alllabels").append("<span style='color:red;'>数据库链接失败</span>");
							}else if(data == -2){
								window.location.href="session";
							}else if(data == -1){
								$(".checkbox_alllabels").append("<span style='color:red;'>您没有可以选择的标签</span>");
							}else{
								var jsonarray = JSON.parse(data);
								for(var index in jsonarray){
									$(".checkbox_alllabels").append("<label><input type='checkbox' name='labels' value='"+jsonarray[index].labelid+"'>"+jsonarray[index].labelname+"</label>");
								}
							}
							$(".input_webaccount").val("");
							$(".input_webpassword").val("");
							$(".input_password").val("");						
						});
					});

					/*
					 * 添加标签按钮
					 */
					$(".button_addcloud").click(function() {
						$(".user_body").hide();
						$(".cloud_div_add").slideDown(100);

					});
					$(".add_body_submitbuttom").click(function() {
						$(".user_body").show();
						$(".cloud_div_add").slideUp(100);
					});
				});