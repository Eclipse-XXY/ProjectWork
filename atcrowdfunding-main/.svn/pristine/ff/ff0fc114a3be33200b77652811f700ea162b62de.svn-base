<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/main.css">
<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css"
	type="text/css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" param="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 系统参数维护</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;"><%@include
							file="/WEB-INF/jsp/common/userinfo.jsp"%>
					</li>
					<li style="margin-left: 10px; padding-top: 8px;">
						<button type="button" class="btn btn-default btn-danger">
							<span class="glyphicon glyphicon-question-sign"></span> 帮助
						</button>
					</li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="tree">
					<ul style="padding-left: 0px;" class="list-group">
						<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
					</ul>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 权限菜单列表
						</h3>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script type="text/javascript"
		src="${APP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
			// $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
	<script type="text/javascript">
		var setting = {
			view : {
				selectedMulti: false, // 多选
				//treeNode代表的就是JSON数据  这两个参数是zTree树提供过来的
				//treeId代表容器的id
			addDiyDom : function(treeId, treeNode) {
					var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
					if (treeNode.icon) {
						icoObj.removeClass("button ico_docu ico_open")
								.addClass(treeNode.icon).css(
										"background", "");
					}
				},
		addHoverDom: function(treeId, treeNode){
				var aObj = $("#" + treeNode.tId + "_a"); //获取连接
				aObj.attr("href", "javascript:;"); //点击超级链接,什么都不做;
				if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
				var s = '<span id="btnGroup'+treeNode.tId+'">';
				if ( treeNode.level == 0 ) { //根节点
				s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${PATH}/permission/add.htm?pid='+treeNode.id+'&level='+treeNode.level+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
				} else if ( treeNode.level == 1 ) {
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"onclick="window.location.href=\'${PATH}/permission/edit.htm?id='+treeNode.id+'&level='+treeNode.level+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
					if (treeNode.children.length == 0) { //当前节点没有子节点,可以存在增加的按钮.
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#"  onclick="deletePermission('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
					}
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${PATH}/permission/add.htm?pid='+treeNode.id+'&level='+treeNode.level+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
					} else if ( treeNode.level == 2 ) {
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'${PATH}/permission/edit.htm?id='+treeNode.id+'&level='+treeNode.level+'\'"title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
					}
					s += '</span>';
					aObj.after(s); //在节点之后增加按钮.
					},
					removeHoverDom: function(treeId, treeNode){
						$("#btnGroup"+treeNode.tId).remove();
					}
			},
		   async: {
		        enable: true,
		        //url:"${APP_PATH}/permission/loadData.do", //返回result对象,封装许可数据,对于同步方式允许,但是,异步加载树是不允许的.
		        url:"${APP_PATH}/permission/asyncLoadData.do", //异步加载许可树
		        //因为这个许可书没有复选框所以它不会显示复选框的回显状态
		        //从而实现两个界面使用一个共同的许可树
		     //   autoParam:["id", "name=n", "level=lv"] //将节点id属性name,level值传递给后台.如果参数名需要改变,可以使用"name=别名"来修改参数.
		   },

		};
		//异步加载树才能达到界面数清除数据的时候的刷新
		$.fn.zTree.init($("#treeDemo"), setting);
		   function deletePermission(id,name){
			    layer.confirm("删除["+name+"]许可,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
           		var loadingIndex = -1 ;
           		$.ajax({
           			type:"post",
           			url:"${APP_PATH}/permission/doDelete.do",
           			data:{
           				"id":id
           			},
           			beforeSend:function(){
           				loadingIndex = layer.msg("正在删除许可!", {time:1000, icon:6});
           				return true ;
           			},
           			success:function(result){
           				layer.close(loadingIndex);
           				if(result.success){
           					//删除成功后,刷新许可树
    						//读取当前树对象
    						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    						//刷新当前树对象的数据
    						//第一个参数如果为null,表示刷新整颗树,否则,可以设置节点对象,对该节点进行刷新
    						treeObj.reAsyncChildNodes(null, "refresh"); 

           				}else{
           					layer.msg("删除失败!", {time:1000, icon:5, shift:6});
           				}
           			},
           			error:function(){
           				layer.msg("删除失败!", {time:1000, icon:5, shift:6});
           			}
           			
           		});
           		
           		
   			    layer.close(cindex);
   			}, function(cindex){
   			    layer.close(cindex);
   			});
           	
			    }
	</script>
	<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>

</body>
</html>
