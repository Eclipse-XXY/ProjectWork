<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet"href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/login.css">
<style>
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<h4>${errorMessage}</h4>
		<form class="form-signin" action="${APP_PATH}/doLogin.do"
			method="post">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户登录
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="floginacct"
					name="loginacct" value="${param.loginacct }" placeholder="请输入登录账号"
					autofocus> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="password" class="form-control" id="fuserpswd"
					name="userpswd" value="${param.userpswd }" placeholder="请输入登录密码"
					style="margin-top: 10px;"> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select class="form-control" id="fusertype" name="usertype">
					<option value="member">会员</option>
					<option value="user">管理</option>
				</select>
			</div>
			<div class="checkbox">
				<label> <input id="remmberMe" type="checkbox" value="remember-me">
					记住我
				</label> <br> <label> 忘记密码 </label> <label style="float: right">
					<a href="reg.html">我要注册</a>
				</label>
			</div>
			<a class="btn btn-lg btn-success btn-block" onclick="dologin()">
				登录</a>
		</form>
	</div>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script>
//     :selected是css的一个选择器表示获取被选中的元素   详细看jQuery的参考手册
    function dologin() {
				 var floginacct=$("#floginacct");
				  if($.trim(floginacct.val())== ""){
				//alert("登录账号不能为空,请重新输入!");//alert,confirm方法会暂停UI线程
				 layer.msg("登录账号不能为空,请重新输入!", {time:2000, icon:5, shift:6}, function(){
				 floginacct.focus();
				//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
				 });
				 return ;//单击事件函数返回
				  }//弹出时间，图标，特效
				 var fuserpswd=$("#fuserpswd");
				 if($.trim(fuserpswd.val())==""){
					//alert("登录账号不能为空,请重新输入!");//alert,confirm方法会暂停UI线程
					 layer.msg("密码名不能为空,请重新输入!", {time:2000, icon:5, shift:6}, function(){
					 fuserpswd.focus();
					//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
					 });
					 return ;//单击事件函数返回
					  }//弹出时间，图标，特效
					  //将jQuery对象转换成dom对象在进行属性值的判断
					  var flag=$("#remmberMe")[0].checked;//用于记住记住我的状态
					  var url="";
					  if($("#fusertype").val()=="member"){
						  url="${APP_PATH}/doMemberLogin.do";
					  }else if($("#fusertype").val()=="user"){
						  url="${APP_PATH}/doLogin.do";
					  }else{
						  return;
					  }
					  
					
			 $.ajax({
				   type: "POST",
				   url:url ,
				   data: {
					   "loginacct":floginacct.val(),
					   "userpswd":fuserpswd.val(),
					   "usertype":$("#fusertype").val(),
					   "flag":flag?"1":"0"
				   },
				   beforeSend: function(){
			// 		 现在这一块我没有具体的实现这一块主要是提交表单前的数据校验工作
			//   return true表示直接放行不做表单的数据合法性的验证
				   return  true;
				   },
				   success: function(result){
					   //result是服务端传过来的JSON格式的字符串转化成JSON然后通过js解析
				      if(result.success){
					       if($("#fusertype").val()=="member"){
						   window.location.href="${APP_PATH}/member.htm";
					   		}else if($("#fusertype").val()=="user"){
								   window.location.href="${APP_PATH}/main.htm";
							  		 }
					  			 }
				                else{
			  			 				alert(result.errorMessage);
			  		                }
				  			 }
			 });
			 
			//  $("form").submit();
	        }
    </script>
</body>
</html>