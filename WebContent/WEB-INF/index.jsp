
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=0.95, user-scalable=no" />
<title>BookMark</title>


<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/demo.css" />

<!--必要样式-->
<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/component.css" />


<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/ue_grid.css" />
<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/style2.css" />
<link rel="stylesheet" type="text/css"
	href="/BookMark/index/css/default.css">
<link href='http://fonts.useso.com/css?family=PT+Sans' rel='stylesheet'
	type='text/css'>
<link rel="stylesheet" media="screen"
	href="/BookMark/index/css/main.css" />
<link rel="stylesheet" media="screen"
	href="/BookMark/index/css/bookmark_information.css" />
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!--[if IE]>
    <script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
  <![endif]-->
<script src="/BookMark/index/js/jquery.min.js"></script>
<script language="javascript"
	src="/BookMark/index/js/jquery.easing.min.js"></script>
<script language="javascript" src="/BookMark/index/js/custom.js"></script>
<script type="text/javascript" src="/BookMark/index/js/ajaxjs.js"></script>
</head>
<body>
	<!--大图片start-->
	<div class="container demo-1">
		<div id="large-header" class="large-header">
			<canvas id="demo-canvas"></canvas>
			<div class="content_text">
				<div class="text_head">我们相信BOOKMARK能够</div>
				<div class="text_list">
					<ul>
						<li>从混乱的世界里解放您的大脑</li>
						<li>让您充分体验互联网带来的方便</li>
						<li>成为您值得信赖的互联网书签管家</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<!--大图片end-->

	<script src="/BookMark/index/js/TweenLite.min.js"></script>
	<script src="/BookMark/index/js/EasePack.min.js"></script>
	<script src="/BookMark/index/js/rAF.js"></script>
	<script src="/BookMark/index/js/demo-1.js"></script>


	<div id="header">
		<div class="common">
			<div class="head_title"
				style="font-size: 30px; color: white; float: left; margin-top: 5px;">BookMark</div>
			<div class="login fr">
				<div class="loginmenu">
					<a title="登录或注册"></a>
				</div>
				<ul>
					<li class="openlogin"><a href="##" onclick="return false;">登录</a></li>
					<li class="reg"><a href="##" onclick="return false;">介绍</a></li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="loginmask"></div>
	<div id="loginalert">
		<div class="pd20 loginpd">
			<h3>
				<i class="closealert fr"></i>
				<div class="clear"></div>
			</h3>
			<div class="loginwrap">
				<div class="loginh">
					<div class="fl">用户登录</div>
					<div class="fr">
						还没有账号<a id="sigup_now"
							href="http://igouc.com/jsp/registered/registered.jsp"
							onclick="return confirm('BookMark及GC开发的所有系统都必须使用“GC博客注册账号”进行登陆，是否跳转到注册页面？');">立即注册</a>
					</div>
					<div class="clear"></div>
				</div>
				<h3>
					<span>BookMark可以使用“GC博客注册账号”进行登陆</span><span class="login_warning">用户名或密码错误</span>
					<div class="clear"></div>
				</h3>
				<div class="clear"></div>
				<div class="logininput">
					<input type="text" name="useremail" class="loginusername"
						value="邮箱（账号）" /> 
					<input type="text" name="userpass" class="loginuserpasswordt"
						value="密码" />
				    <input type="password" name="password"
						class="loginuserpasswordp" style="display: none" />
				</div>
				<div class="loginbtn">
					<div class="loginsubmit fl">
						<input type="button" class="button_login" value="登录" />
						<div class="loginsubmiting">
							<div class="loginsubmiting_inner"></div>
						</div>
					</div>
					<div class="logcheckbox fl">
						<input id="bcdl" type="checkbox" checked="true" /> 保持登录
					</div>
					<div class="fr">
						<a href="##">忘记密码?</a>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="thirdlogin">
			<div class="pd50">
				<div class="ajaxresult"></div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div id="reg_setp">
		<div class="back_setp">返回</div>
		<div class="blogo">
			<h1>
				<span class="bookmark_information_head">BookMark介绍</span>
			</h1>
		</div>
		<div id="setp_quicklogin">
			<span>您肯定会遇到的问题！</span>
			<ul class="bookmark_information_content">
				<li><p class="bookmark_information_contentp">&nbsp;&nbsp;&nbsp;&nbsp;在互联网飞速发展的现在，各种类型的网站和各大浏览器也随之出现。网站及浏览器的增加造成了这样一个现象：用户在浏览器A里发现了喜欢的网站并且加入了A的书签里，但是当用户换浏览器后却不能找到A里存的书签。或者当用户换电脑或换系统之后，都不能找到以前存在别的电脑/系统下的书签，由此就造成了很大的不方便</p></li>
				<li><p class="bookmark_information_contentp">&nbsp;&nbsp;&nbsp;&nbsp;很多门户网站都需要用户注册，这样每位用户所拥有的账号密码就不止一个，由于人们的大脑只记住重要的东西，像这些虽然不重要但是可能还会用到的账号密码大脑是不会记住的。一段时间之后，如果用户还想访问某个需要登陆的网站，就必须耐心的再注册一遍。</p></li>
			</ul>
			<span>BookMark是怎样解决的？</span>
			<ul class="bookmark_information_content">
				<li><p>&nbsp;&nbsp;&nbsp;&nbsp;BookMark
						可以帮助您记录您想要记录的网站，因为BookMark是以WebApp形式展现出来的，所以无论您在何处，用什么浏览器都能登陆BookMark找到您保存的“网站书签”。
						随时随地！</p></li>
				<li><p>&nbsp;&nbsp;&nbsp;&nbsp;BookMark
						能帮助您保存某个网站的账号密码并且是以绝密的方式。只有您的口令才能查看！否则包括开发者在内都不能查看您存储的账号密码。</p></li>
			</ul>
			<span>BookMark适合什么人？</span>
			<ul class="bookmark_information_content">
				<li><p>&nbsp;&nbsp;&nbsp;&nbsp;极简主义者</p></li>
				<li><p>&nbsp;&nbsp;&nbsp;&nbsp;想要释放自己大脑的人</p></li>
				<li><p>&nbsp;&nbsp;&nbsp;&nbsp;经常浏览网站的人（网剧控，网游控，新闻控，博客控
						......）</p></li>

			</ul>
		</div>
	</div>

</body>
</html>