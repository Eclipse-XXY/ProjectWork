<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
.seltype {
    position: absolute;
    margin-top: 70px;
    margin-left: 10px;
    color: red;
}
	</style>
  </head>
  <body>
 <div class="navbar-wrapper">
      <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			  <div class="container">
				<div class="navbar-header">
				  <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
				</div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">     
                <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> ${sessionScope.loginMember.username }<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="member.html"><i class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                    <li class="divider"></li>
                    <li><a href="${APP_PATH }/logout.do"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                  </ul>
                </li>
              </ul>
            </div>
			  </div>
			</nav>

      </div>
    </div>

    <div class="container theme-showcase" role="main">

      <div class="page-header">
        <h1>实名认证 - 账户类型选择</h1>
      </div>
	  <div style="padding-top:10px;">
		<div class="row">
      <div class="col-xs-6 col-md-3">
      
      <h2>商业公司</h2>
<!--     accttype  这个地方是自定义属性 -->
        <a href="#" class="thumbnail" accttype="0">
          
          <img alt="100%x180" src="img/services-box1.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>个体工商户</h2>
        <a href="#" class="thumbnail" accttype="1">
          <img alt="100%x180" src="img/services-box2.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>个人经营</h2>
        <a href="#" class="thumbnail" accttype="2">
          <img alt="100%x180" src="img/services-box3.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>政府及非营利组织</h2>
        <a href="#" class="thumbnail" accttype="3">
          <img alt="100%x180" src="img/services-box4.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
    </div>
	<button type="button" class="btn btn-danger btn-lg btn-block" id="updateAcctType">认证申请</button>
    </div> <!-- /container -->
      <!-- /END THE FEATURETTES -->


        <div class="container" style="margin-top:20px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div id="footer">
                        <div class="footerNav">
                             <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                        </div>
                        <div class="copyRight">
                            Copyright ?2017-2017atguigu.com 版权所有
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script>
	var accttype=0;
    $(".thumbnail").click(function(){
        $('.seltype').remove();
        $(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');
//       this.accttype注意这样取的话是undefined 因为dom对象没有该属性 必须转换成jQuery对象通过attr()来取
       accttype= $(this).attr("accttype");
      
    });

    $(".thumbnail img").each(function(i,n){
    	$(this).attr("src","${APP_PATH}/"+$(this).attr("src"));
    });
    $("#updateAcctType").click(function(){
    	var length= $('.seltype').length;
    	if(length==0){
    		layer.msg("请选择认证的账号类型!", {time:1000, icon:5, shift:6});
    	}
    	else{
    	$.ajax({
    		type:"post",
        	url:"${APP_PATH}/member/updateAcctType.do",
            data:{
        		"accttype":accttype
        		},
        	berforeSend:function(){
        		return true;
        	},
        	success:function(result){
        		if(result.success){
        			window.location.href="${APP_PATH}/member/basicinfo.htm";
        		}else{
        			layer.msg("账号认证失败!", {time:1000, icon:5, shift:6});
        		}
        	}
    	});
    	}
    });
    
	</script>
  </body>
</html>