<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Session信息过期提示页面</title>
    <style type="text/css">
      body{
      	background-image: url(/BookMark/initpage/image/bookmarkbackgroundimg.jpg);
        text-align: center;
      }
      .all{
        margin-left: auto;
        margin-right: auto;
        margin-top: 150px;
        font-size: 20px;
        line-height: 200%;
      }

    </style>
  </head>
  <body>
    <div class="all">
      <div class="head">
        <span>对不起，您的登陆信息已经过期，请重新登陆</span>
      </div>
      <div class="body">
        <div style="font-size:20px;" class="body_time">
          <span  style="color:red;" id="showtimes"></span> &nbsp;<span>秒之后自动跳转</span>
        </div>
        <div class="body_link">
          <span><a href="welcome">立即跳转</a></span>
        </div>
        <div class="body_infor">
          <span>登陆成功后，请不要清除您的cookie，否则20分钟后仍然需要重新登陆</span>
        </div>
      </div>
    </div>
    
    
    <!--javascript-->
    <script type="text/javascript">  
      //设定倒数秒数  
      var t = 10;  
      //显示倒数秒数  
      function showTime(){  
          t -= 1;  
          document.getElementById('showtimes').innerHTML= t;  
          if(t==0){  
              location.href='welcome';  
          }  
          //每秒执行一次,showTime()  
          setTimeout("showTime()",1000);  
      }  
      //执行showTime()  
      showTime();  
      </script>

  </body>

</html>