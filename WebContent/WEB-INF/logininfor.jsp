<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>BookMark登陆信息</title>

    <!-- Bootstrap -->
    <link href="/BookMark/userpage/css/bootstrap.min.css" rel="stylesheet">
	<link href="/BookMark/userpage/css/logininfor.css" rel="stylesheet"> 
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	body{
    		background-image:
		url(/BookMark/userpage/image/bookmarkbackgroundimg.jpg);
    	}
    </style>
  </head>
  <body>
    <div class="all">
      <div class="head">
      <span>登陆记录</span>
      </div>
      <div class='body'>
        <div class="body_userinfor">
          <span>用户ID: </span><a href="#"><span class="userid">1</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <span>用户昵称：</span><a href="#"><span class="usernick">Gc</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <span>用户账号：</span><a href="#"><span class="useremail">sut_gc@foxmail.com</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <span>返回>></span><a href="user"><span class="useremail">BookMark</span></a>
        </div>
        <div class="body_logininfor">
          <table class="table table-hover">
            <thead class="thead_logininfor">
              <tr><td>#</td><td>登陆Ip</td><td>登陆主机</td><td>登陆时间</td></tr>
            </thead>
            <tbody class="tbody_logininfor">
	            <s:iterator value="list" status="st">
	            	<tr><td><s:property value="#st.index "/> </td><td><s:property value="list[size - #st.index - 1].ipAddress"/> </td><td><s:property value="list[size - #st.index - 1].hostname"/> </td><td><s:property value="list[size - #st.index - 1].logintime"/> </td></tr>
	            </s:iterator>
            </tbody>
          </table>
        </div>
      </t>
    </div>
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/BookMark/userpage/js/bootstrap.min.js"></script>
  </body>
</html>