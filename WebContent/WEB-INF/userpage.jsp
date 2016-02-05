<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>BookMark</title>

<!-- Bootstrap -->
<link href="/BookMark/userpage/css/bootstrap.min.css" rel="stylesheet">
<link href="/BookMark/userpage/css/bookmarkuser.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
body {
	background-color: #FFFFFF;
	background-image:
		url(/BookMark/userpage/image/bookmarkbackgroundimg.jpg);
	background-size: 200px 133px;
}
/*去掉row的右边空白*/
.row {
	margin-right: 0px;
}
</style>
</head>
<body>
	<nav class="navbar  navbar-inverse">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="margin-left: 10%; font-size: 30px;"
				href="#">BookMark</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">用户ID: <s:property
							value="#session.userid" /> &nbsp;&nbsp;&nbsp;用户邮箱: <s:property
							value="#session.useremail" /> &nbsp;&nbsp;&nbsp; 用户昵称: <s:property
							value="#session.usernick" /><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">用户中心</a></li>
						<li><a href="logininfor">登陆记录</a></li>
						<li><a href="outlogin">用户注销</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="user_body">
		<div class="row">
			<div class="col-md-1"></div>
			<!--总部局左面栅格start-->
			<div class="col-md-3">
				<div class="user_left">
					<!--标签域start-->
					<div class="left_label_head">
						<a>标签云</a>
					</div>
					<hr class="label_hr" />
					<div class="left_label">
						<!--标签栏搜索框start-->
						<div class="left_label_search">
							<div class="row">
								<!--搜索框start-->
								<div class="col-md-12">
									<div class="col-lg-12">
										<div class="input-group">
											<input type="text" class="form-control input_selectlabelname"
												placeholder="输入要搜索的标签名" value=""> <span
												class="input-group-btn">
												<button class="btn btn-primary button_selectlabelname" type="button">Go!</button>
											</span>
										</div>
										<!-- /input-group -->
									</div>
									<!-- /.col-lg-12 -->
								</div>
							</div>
						</div>
						<!--标签栏搜索框end-->
						<!--标签栏ADDstart-->
						<div class="left_label_addbutton">
							<div class="row">
								<!--addstart-->
								<div class="col-md-12">
									<button type="button"
										class="btn btn-warning label_addbutton button_editlabel">编辑标签</button>
									<button type="button"
										class="btn btn-danger label_addbutton button_appendeditlabel">确认编辑</button>
									<button type="button"
										class="btn btn-success label_addbutton button_addcloud">添加标签</button>
								</div>
							</div>
						</div>
						<!--标签栏ADDend-->
						<hr />
						<!--标签start-->
						<div class="cloud">
							<ul class="cloud_ul">
								<s:iterator value="labels" status="st">
									<li><div style='display: none;' class='labelid'><s:property value="labels[#st.index][0]" /></div>
										<a href="#" class="labelname button_selectwbs"><s:property value="labels[#st.index][1]" /> </a></li>
								</s:iterator>
								<li class="cloud_baseli"></li>
							</ul>
						</div>
						<!--标签end-->
						<!--标签编辑table start-->
						<div class="cloudedit">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class="cloudedit_th_name">标签名称</th>
										<th class="cloudedit_th_edit">编辑</th>
										<th class="cloudedit_th_del">删除</th>
									</tr>
								</thead>
								<tbody class="editlabel_tbody">
								</tbody>
							</table>
						</div>
						<!--标签编辑table end-->
					</div>
				</div>
			</div>
			<!--总部局左面栅格end-->
			<!--右面7格栅兰start-->
			<div class="col-md-7">
				<div class="user_right">
					<!--右面的头部start-->
					<div class="right_top">
						<div class="row">
							<!--搜索提示字start-->
							<div class="col-md-6">
								<span class="right_search_label">对收藏的网站名称进行关键字搜索：</span>
							</div>
							<!--搜索提示字end->
            <!--搜索框start-->
							<div class="col-md-6">
								<div class="col-lg-12">
									<div class="input-group">
										<input type="text" class="form-control input_selectwebinforname" placeholder="请输入搜索的网站名条">
										<span class="input-group-btn">
											<button class="btn btn-default button_selectwebinfor" type="button">Go!</button>
										</span>
									</div>
									<!-- /input-group -->
								</div>
								<!-- /.col-lg-12 -->
							</div>
							<!--搜索框end-->
						</div>
					</div>
					<!--右面的头部end-->
					<hr />
					<!--显示内容的标签页面start-->
					<div>
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active selectshowbybits hasclassactive"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab"
								data-placement="top" title="浏览次数最多的记录排在最前面">按浏览次数排序</a></li>
							<li class="selectshowbytime hasclassactive" role="presentation"><a href="#profile"
								aria-controls="profile" role="tab" data-toggle="tab"
								data-placement="top" title="最新创建的会排在最前面">按创建时间排序</a></li>
							<li class="add_mark_button"><button
									class="btn btn-success add_record" type="submit">添加记录</button></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="home">
								<table class="table table-hover">
									<thead>
										<tr>
											<th class="table_id">#</th>
											<th>网站名称</th>
											<th class="table_num">点击数</th>
											<th class="table_show">显示</th>
											<th class="table_edit">编辑</th>
											<th class="table_del">删除</th>
										</tr>
									</thead >
									<tbody class="tbody_showwebinforbybit">
									</tbody>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane" id="profile">
								<table class="table table-hover">
									<thead>
										<tr>
											<th class="table_id">#</th>
											<th>网站名称</th>
											<th class="table_createtime">创建时间</th>
											<th class="table_show">显示</th>
											<th class="table_edit">编辑</th>
											<th class="table_del">删除</th>
										</tr>
									</thead>
									<tbody class="tbody_showwebinforbytime">
										<!--一条记录start-->
										<tr>
											<td>1</td>
											<td><a href="https://www.baidu.com" target="_blank">百度</a></td>
											<td>2016-1-28 23:14:32</td>
											<td><button class="show_button">
													<span class="glyphicon glyphicon-eye-open "
														aria-hidden="true"></span>
												</button></td>
											<td><button class="edit_button">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true">
												</button></td>
											<td><button class="del_button">
													<span class="glyphicon glyphicon-remove" aria-hidden="true">
											</button></td>
										<tr />
										<!--一条记录end-->
									</tbody>
								</table>
							</div>
						</div>

					</div>
					<!--显示内容的标签页面end-->
				</div>
				<!--右面7格栅栏end-->
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<div class="tol_show">
		<div class="tol_show_head">
			<span>网站信息显示窗口</span>
			<div>记录创建日期: 2015-12-14</div>
		</div>
		<div class="tol_show_body">
			<div class="tol_show_body_all">
				<div class="tol_show_name">
					<span>网站名条：</span>
					<pre>百度</pre>
				</div>
				<div class="tol_show_link">
					<span>网站连接：</span>
					<pre>
						<a href="https://www.baidu.com" target="_blank">https://www.baidu.com</a>
					</pre>
				</div>
				<div class="tol_show_dis">
					<span>网站描述：</span>
					<pre>无</pre>
				</div>
				<div class="tol_show_label">
					<span>属于标签：</span><br />
					<div class="show_label_each">
						<pre></pre>
					</div>
				</div>
				<hr />
				<div class="tol_show_pass row">
					<div class="col-md-12">
						<button type="button" class="btn btn-success tol_showpass">显示账号密码</button>
					</div>
				</div>
			</div>
			<!--显示密码div start-->
			<div class="show_pass">
				<div class="show_pass_inputcom">
					<span>请输入口令</span>
					<div class="row">
						<div class="col-lg-12">
							<div class="input-group">
								<input type="password" class="form-control input_submitkey" placeholder="输入您的口令">
								<span class="input-group-btn"> <input
									class="btn btn-default button_submitkey" type="button" value="提交口令" />
								</span>
							</div>
							<!-- /input-group -->
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<div class="show_pass_div">
				</div>
			</div>
			<!--显示密码div end-->

			<div class="tol_show_close row">
				<div class="col-md-12">
					<button type="button" class="btn btn-primary tol_close">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--添加记录按钮start-->
	<div class="tol_add">
		<div class="tol_add_head">
			<span>添加网站记录窗口</span>
		</div>
		<div class="tol_add_body">
			<div class="tol_add_body_all">
				<!--网站信息表单start Bootstrap-->
				<form class="form-horizontal form_inputweb" name="form_inputnewweb">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站名条:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_webname" name="input_webname" id="inputEmail3"
								placeholder="必须填写">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站网址:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_weblink" name="input_weblink" value=""
								placeholder="必须填写">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站描述:</label>
						<div class="col-sm-10">
							<textarea class="form-control input_webdescribe" name="webdescribe" rows="3" placeholder="可以不填写"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">可选标签:</label>
							<div class="checkbox checkbox_alllabels col-sm-10">
								<label> <input type="checkbox" name="labels" value="77"> Remember me</label> 
								<label> <input type="checkbox" name="labels" value="78s"> Remember me
							</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站账号:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_webaccount" name="input_webaccount" value=""
								placeholder="可以不填写">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站密码:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_webpassword" name="input_webpassword" value=""
								placeholder="可以不填写">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">您的口令:</label>
						<div class="col-sm-10">
							<input type="password" class="form-control input_password" name="input_password" value=""
								placeholder="如果您填写了账号密码，则需要填写口令-->进行加密">
							<span style="color:red">如果您填写了账号密码，则需要填写口令-->进行加密</span><br/>
							<span style="color:red">如果您不填写了账号密码，则不需要填写口令</span>
						</div>
					</div>
				</form>
				<!--网站信息表单end Bootstrap-->
				<hr />
			</div>
			<div class="tol_add_close row">
				<div class="isaddwebinforing" style="color:red; font-size:20px;">请不要刷新页面，正在添加中......</div>
				<div class="col-md-12 buttons_addwebinfor">
					<button type="button" class="btn btn-success button_submitwebinfor tol_submit">提交</button>
					<button type="button" class="btn btn-primary tol_close">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--添加记录按钮end-->
	<!--修改网站信息按钮start-->
	<div class="tol_edit">
		<div class="tol_edit_head">
			<span>网站信息编辑窗口</span>
			<div>记录创建日期: 2015-12-14</div>
		</div>
		<div class="tol_edit_body">
			<div class="tol_edit_body_all">
				<!--网站信息表单start Bootstrap-->
				<form class="form-horizontal">
					<div class="edit_webinforid" style='display:none;'></div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站名条:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_editwebinforname"  id="inputEmail3"
								placeholder="必须填写">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站网址:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_editwebinforlink" id="inputEmail3"
								value="https://www.baidu.com" placeholder="必须填写">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站描述:</label>
						<div class="col-sm-10">
							<textarea class="form-control input_editwebinfordes" rows="3" placeholder="可以不填写"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox edit_alllabels">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label ">网站账号:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_editwebinforacc" id="inputEmail3" value=""
								placeholder="账号与密码都不填写，则不会修改原来的账号密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">网站密码:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control input_editwebinforpass" id="inputEmail3"
								value="" placeholder="账号与密码都不填写，则不会修改原来的账号密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">您的口令:</label>
						<div class="col-sm-10">
							<input type="password" class="form-control input_editwebinforpassword" name="input_password" value=""
								placeholder="如果您填写了账号密码，则需要填写口令-->进行加密">
							<span style="color:red">如果账号密码&nbsp;<span style='font-size:22px;'>都</span>&nbsp;不填写，则不会修改原来的账号密码</span><br/>
							<span style="color:red">否则会以新的账号密码覆盖原来的账号密码</span>
						</div>
					</div>
				</form>
				<!--网站信息表单end Bootstrap-->
				<hr />
			</div>
			<div class="tol_edit_close row">
				<div class="col-md-12">
					<button type="button" class="btn btn-success button_submiteditwebinfor">提交</button>
					<button type="button" class="btn btn-primary tol_close">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--修改网站信息按钮end-->
	<!--标签修改div start-->
	<div class="cloud_div_edit">
		<div class="cloud_div_head">
			<span>修改标签名称</span>
		</div>
		<div class="cloud_div_body">
			<div class="cloud_div_body_id">
				<label>标签Id：</label><span><a></a></span>
			</div>
			<div class="cloud_div_body_oldname">
				<label>标签旧名：</label><span><a></a></span>
			</div>
			<div class="cloud_div_body_newname">
				<label>标签新名：</label> <input type="text" class="form-control input_newlabelname"
					placeholder="请填写标签新名" value=""/>
			</div>
			<hr />
			<div class="cloud_div_body_submitbuttom">
				<button class="btn btn-primary body_submitbuttom">取消修改</button>
				<button class="btn btn-success button_oktoeditlabel">确认修改</button>
			</div>
		</div>
	</div>
	<!--标签修改div end-->
	<!--添加标签start-->
	<div class="cloud_div_add">
		<div class="cloud_add_head">
			<span>添加新的标签</span>
		</div>
		<div class="cloud_add_body">
			<div class="cloud_add_body_name">
				<label>标签名：</label> <input type="text"
					class="form-control input_labelname" id="inputEmail3"
					name="newlabelname" placeholder="请填写标签名">
			</div>
			<hr />
			<div class="cloud_add_body_submitbuttom">
				<button class="btn btn-primary add_body_submitbuttom">取消添加</button>
				<button class="btn btn-success button_addnewlabelok">确认添加</button>
			</div>
		</div>
	</div>
	<!--添加标签end-->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/BookMark/userpage/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/BookMark/userpage/js/bootstrap.min.js"></script>
	<script src="/BookMark/userpage/js/bookmark.js"></script>
	<script src="/BookMark/userpage/js/userpage.js"></script>
</body>
</html>