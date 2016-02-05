<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>BookMark 用户初始化</title>

<!-- Bootstrap -->
<link href="/BookMark/initpage/css/bootstrap.min.css" rel="stylesheet">
<link href="/BookMark/initpage/css/initpage.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
body {
	background-image: url(/BookMark/initpage/image/bookmarkbackgroundimg.jpg);
}
</style>
</head>
<body>
	<div class="all_content">
		<div class="userinit_content_head">
			<p class="bg-primary">由于您是第一次登陆该系统，请初始化用户口令</p>
		</div>
		<div class="userinit_content_body">
			<div class="content_body_input">
				<div class="row  key_group">
					<div class="col-md-8">
						<div class="form-group">
							<input type="password" class="form-control input_key"
								id="exampleInputEmail1" placeholder="请输入您的口令">
						</div>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-3">
						<input style="float: right;" class="btn btn-primary toinit"
							type="button" value="请点击进行初始化">
					</div>
				</div>

				<div class="input_result">
					<span class="result_content">这是口令动态验证结果</span>
					<div class="ajaxresult"></div>
				</div>

				<div class="row button_initsuccess">
					<div class="col-md-12">
						<input class="btn btn-success button_begintouse" type="button"
							value="初始化成功，点击开始使用该系统">
					</div>
				</div>

			</div>
			<div class="content_body_introduce">
				<p class="introduce_head">&nbsp;什么是口令？</p>
				<p class="introduce_body">&nbsp;&nbsp;&nbsp;&nbsp;如果您在BookMark中保存了某个网站的账号与密码，口令则是您在BookMark中领取所存账号密码的依据，是您的账号与密码在BookMark中进行安全存储的一种保障。您所保存的账号与密码都是经过口令加密之后的结果，只有您的口令才会将这些保存的信息进行正确解密。BookMark不会存储您的口令，只会存储口令经过MD5（不可逆加密）之后的数据，这样可以保证您的口令不会被泄露，除了您之外不会有任何人能窥视您所输入的口令（包括系统开发人员）。虽然这样保证了您信息的安全性（因为系统中没有保存您所输入的口令），但也因此您不能找回您的口令（根据旧的口令可以更换口令，如果丢失旧的口令，则没有办法弥补，所有有已经存的账号密码都不能被正确解密出来），
				
				<h2 style="color: red;">切记一定要记住您的口令</h2>
				</p>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/BookMark/initpage/js/jquery.min.js"></script>
	<script src="/BookMark/initpage/js/bootstrap.min.js"></script>
	<script src="/BookMark/initpage/js/initpage.js"></script>
</body>
</html>