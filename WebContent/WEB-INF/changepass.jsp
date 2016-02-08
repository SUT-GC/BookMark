<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>BookMark</title>

    <!-- Bootstrap -->
    <link href="/BookMark/userpage/css/bootstrap.min.css" rel="stylesheet">
	<link href="/BookMark/userpage/css/changepass.css" rel="stylesheet">
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
	    	<span>用户ID：</span><a href="#"><s:property value="#session.userid"/> </a>&nbsp;&nbsp;&nbsp;&nbsp;
	    	<span>用户昵称：</span><a href="#"><s:property value="#session.usernick"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
	    	<span>用户邮箱：</span><a href="#"><s:property value="#session.useremail"/> </a>
    	</div>
    	<div class="body">
    		<div class="editbutton">
    			<div class="row">
	    			<div class="col-md-3">
	    			</div>
	    			<div class="col-md-2">
	    				<button type="button " class="btn btn-default button_edit_pass">修改密码</button>
	    			</div>
	    			<div class="col-md-2">
	    				<button type="button " class="btn btn-default button_edit_key" >修改口令</button>
	    			</div>
	    			<div class="col-md-2">
	    				<button type="button " class="btn btn-default button_exit">BookMark</button>
	    			</div>
	    			<div class="col-md-3">
	    			</div>
	    		</div>
    		</div>
	    	<div class="div_edit_pass">
	    		<div class="div_oldpass">
		    		<div class="row">
		    			<div class="col-md-2 divlabel">
		    				请输入旧密码 :
		    			</div>
		    			<div class="col-md-6">
		    				<div class="form-group has-error has-feedback">
							  	<input type="password" class="form-control input_oldpass" id="inputSuccess2" aria-describedby="inputSuccess2Status">
							  	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
							  	<span id="inputSuccess2Status" class="sr-only">(success)</span>
						  	</div>
		    			</div>
		    			<div class="col-md-4 input_result input_oldpass_result">
		    				输入不能为空
		    			</div>
		    		</div>
		    	</div>
		    	<div class="div_newpass1">
		    		<div class="row">
		    			<div class="col-md-2 divlabel" >
		    				请输入新密码 :
		    			</div>
		    			<div class="col-md-6">
		    				<div class="  form-group has-error has-feedback">
							  	<input type="password"  readonly class=" form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
							  	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
							  	<span id="inputSuccess2Status" class="sr-only">(success)</span>
						  	</div>
		    			</div>
		    			<div class="col-md-4 input_result">
		    				输入不能为空
		    			</div>
		    		</div>
		    	</div>
		    	<div class="div_newpass2">
		    		<div class="row">
		    			<div class="col-md-2 divlabel">
		    				请再次输入新密码 :
		    			</div>
		    			<div class="col-md-6">
		    				<div class=" form-group has-error has-feedback">
							  	<input type="password" readonly class=" form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
							  	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
							  	<span id="inputSuccess2Status" class="sr-only">(success)</span>
						  	</div>
		    			</div>
		    			<div class="col-md-4 input_result">
		    				输入不能为空
		    			</div>
		    		</div>
		    	</div>
	    		<div class="row">
	    			<div class="col-md-2">
	    			</div>
	    			<div class="col-md-6">
	    				<button type="button" disabled class="btn btn-success button_okedit">确认修改</button>
	    				<button type="button" class="btn btn-primary button_close">取消修改</button>
	    			</div>
	    			<div class="col-md-4">
	    			</div>
	    		</div>
	    	</div>
	    	<div class="div_edit_pass_doing">
	    		<div class="row">
	    			<div class="col-md-4">
	    			</div>
	    			<div class="col-md-4">
		   				<span>正在修改中,请稍后...........</span>
	    			</div>
	    			<div class="col-md-4">
	    			</div>
	    		</div>
	    	</div>
	    	<div class="div_edit_key">
	    		<div class="row">
	    			<div class="col-md-2 divlabel">
	    				请输入旧口令 :
	    			</div>
	    			<div class="col-md-6">
	    				<div class="form-group has-error has-feedback">
						  	<input type="text" class="form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
						  	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
						  	<span id="inputSuccess2Status" class="sr-only">(success)</span>
					  	</div>
	    			</div>
	    			<div class="col-md-4 input_result">
	    				输入不能为空
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-md-2 divlabel" >
	    				请输入新口令 :
	    			</div>
	    			<div class="col-md-6">
	    				<div class="  form-group has-error has-feedback">
						  	<input type="text"  readonly class=" form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
						  	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
						  	<span id="inputSuccess2Status" class="sr-only">(success)</span>
					  	</div>
	    			</div>
	    			<div class="col-md-4 input_result">
	    				输入不能为空
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-md-2 divlabel">
	    				请再次输入新口令 :
	    			</div>
	    			<div class="col-md-6">
	    				<div class=" form-group has-error has-feedback">
						  	<input type="text" readonly class=" form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
						  	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
						  	<span id="inputSuccess2Status" class="sr-only">(success)</span>
					  	</div>
	    			</div>
	    			<div class="col-md-4 input_result">
	    				输入不能为空
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-md-2">
	    			</div>
	    			<div class="col-md-6">
	    				<button type="button" disabled class="btn btn-success button_okedit">确认修改</button>
	    				<button type="button"  class="btn btn-primary button_close">取消修改</button>
	    			</div>
	    			<div class="col-md-4">
	    			</div>
	    		</div>
	    	</div>
	    	<div class="div_edit_key_doing">
	    		<div class="row">
	    			<div class="col-md-4">
	    			</div>
	    			<div class="col-md-4">
		   				<span>正在修改中,请稍后...........</span>
	    			</div>
	    			<div class="col-md-4">
	    			</div>
	    		</div>
	    	</div>
    	</div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/BookMark/userpage/js/bootstrap.min.js"></script>
    <script src="/BookMark/userpage/js/changepass.js"></script>
    <script type="text/javascript" src="/BookMark/userpage/js/spin.min.js"></script>
    
     <script type="text/javascript">
        $(function(){
            var opts = {
                lines: 9, // The number of lines to draw
                length: 0, // The length of each line
                width: 10, // The line thickness
                radius: 15, // The radius of the inner circle
                corners: 1, // Corner roundness (0..1)
                rotate: 0, // The rotation offset
                color: '#000', // #rgb or #rrggbb
                speed: 1, // Rounds per second
                trail: 60, // Afterglow percentage
                shadow: false, // Whether to render a shadow
                hwaccel: false, // Whether to use hardware acceleration
                className: 'spinner', // The CSS class to assign to the spinner
                zIndex: 2e9, // The z-index (defaults to 2000000000)
                top: 'auto', // Top position relative to parent in px
                left: 'auto' // Left position relative to parent in px
            };
            var target = document.getElementById('foo');
            var spinner = new Spinner(opts).spin(target);
        })
    </script>
  </body>
</html>