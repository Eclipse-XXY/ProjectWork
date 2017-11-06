<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="btn-group">
  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
	<i class="glyphicon glyphicon-user"></i> ${sessionScope.loginUser.username } <span class="caret"></span>
<!-- 这个地方需要改要么是会员要么是管理员需要判断一下 -->
  </button>
	  <ul class="dropdown-menu">
		<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
		<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
		<li class="divider"></li>
		<li><a href="${APP_PATH }/logout.do"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
	  </ul>
   </div>
