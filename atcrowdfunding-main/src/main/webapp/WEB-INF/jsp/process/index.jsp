<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>流程管理</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${APP_PATH}/css/pagination.css">
<link rel="stylesheet"href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/main.css">
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
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 控制面板</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;"><jsp:include
							page="/common/userInfo.jsp"></jsp:include></li>
					<li style="margin-left: 10px; padding-top: 8px;">
						<button type="button" class="btn btn-default btn-danger">
							<span class="glyphicon glyphicon-question-sign"></span> 帮助
						</button>
					</li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="查询">
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
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="queryText" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" id="queryButton" onclick=""
								class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" id="deleteBatch" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button id="updateProcDef" type="button" class="btn btn-primary"
							style="float: right;">
							<i class="glyphicon glyphicon-upload"></i> 上传流程定义文件
						</button>

						<br>

						<hr style="clear: both;">
						<form id="proDefForm" method="post" action=""
							enctype="multipart/form-data">
							<div class="form-group">
							<input type="file"
									style="display: none;" class="form-control" id="upProDef"
									name="procDefFile" >
							</div>
						</form>
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">序号</th>
										<th width="30"><input id="checkboxAll" type="checkbox"></th>
										<th>流程名称</th>
										<th>流程KEY</th>
										<th>流程版本</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination">
												<!-- 这里显示分页 -->
											</div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script src="${APP_PATH}/jquery/jquery.pagination.js"></script>
		<script src="${APP_PATH }/jquery/jquery-form/jquery-form.min.js"></script>
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
			<c:if test="${not empty param.pageNo}">
			var pageNo = "${param.pageNo}";
			// 			    记住在jQuery代码中使用EL表达式用双引号括起来目前我也不知道为什么
			queryPage(pageNo);
			</c:if>
			<c:if test="${empty param.pageNo}">
			queryPage(0);
			</c:if>

		});

		$("#insertBtn").click(function() {
			window.location.href = "${APP_PATH}/user/toAdd.htm";
		});
		$("tbody .btn-success").click(function() {
			window.location.href = "assignRole.html";
		});
		$("tbody .btn-primary").click(function() {
			window.location.href = "edit.html";
		});
		var dataObj = {
			"pageNo" : 1,
			"pageSize" : 2,
		};
		$("#queryButton").click(function() {
			var queryText = $("#queryText").val();
			if ($.trim(queryText) == "") {
				layer.msg("请输入查询条件在做查询", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
					$("#queryText").focus();
				});
				return;
			}
			dataObj.queryText = $.trim(queryText);
			queryPage(0);
			//  				它默认差出来就放第一页，分页显示是有pageChange实现的
		});
		function pageChange(pageNo) {
			queryPage(pageNo);
		};
		function queryPage(pageNo) {
			dataObj.pageNo = pageNo + 1;
			var loadIndex = -1;
			$
					.ajax({
						type : "POST",
						url : "${APP_PATH }/process/doindex.do",
						data : dataObj,
						beforeSend : function() {
							//直接放行
							//var index = layer.load(0, {shade: false}); 
							var loadIndex = layer.load(1, {
								shade : [ 0.1, '#fff' ],
								time : 1000
							});
							return true;
						},
						success : function(result) {
							layer.close(loadIndex);
							if (result.success) {
								var page = result.page;
								var data = page.list;
								var content = '';
								$.each(
												data,
												function(index, processDef) {
													content += '<tr>';
													content += '  <td>'
															+ (index + 1)
															+ '</td>';

													content += '  <td><input type="checkbox"   class="checkboxClass"  value="'+processDef.id+'"></td>';
													content += '  <td>'
															+ processDef.name
															+ '</td>';
													content += '  <td>'
															+ processDef.key
															+ '</td>';
													content += '  <td>'
															+ processDef.version
															+ '</td>';
													content += '  <td>';
													content += '	  <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/process/showImg.htm?id='
															+ processDef.id
															+ '\'"><i class=" glyphicon glyphicon-check"></i></button>';
										
													content += '	  <button type="button" onclick="deleteProcess(\''
															+ processDef.deploymentId
															+ '\',\''
															+ processDef.name
															+ '\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
													content += '  </td>';
													content += '</tr>';
												});

								$("tbody").html(content);
								// 创建分页条
								$("#Pagination").pagination(page.totalSize, {
									num_edge_entries : 2, //边缘页数
									num_display_entries : 4, //主体页数
									callback : queryPage, //查询当前页的数据.
									items_per_page : page.pageSize, //每页显示1项
									current_page : (page.pageNo - 1), //当前页,索引从0开始
									prev_text : "上一页",
									next_text : "下一页"
								});
							} else {
								layer.msg("流程部署失败", {
									time : 1000,
									icon : 5,
									shift : 6
								});
							}

						},
						error : function() {

							layer.msg("流程部署失败", {
								time : 1000,
								icon : 5,
								shift : 6
							});
						}
					});
			return;
			// 				return的意思就是走到这一步了就可以停止函数的执行  如果是表单的话就是阻止表单的提交
			// 				就是和Java当中的意思一样遇到return就返回上一层
		};
		function deleteProcess(id, name) {
			layer.confirm("删除[" + name + "]用户,确认删除?", {
				icon : 3,
				title : '提示'
			}, function(cindex) {
				var loadingIndex = -1;
				$.ajax({
					type : "post",
					url : "${APP_PATH}/process/doDelete.do",
					data : {
						"id" : id
					},
					beforeSend : function() {
						loadingIndex = layer.msg("正在删除数据!", {
							time : 1000,
							icon : 6
						});
						return true;
					},
					success : function(result) {
						layer.close(loadingIndex);
						if (result.success) {
							queryPage(0);
						} else {
							layer.msg("删除失败!", {
								time : 1000,
								icon : 5,
								shift : 6
							});
						}
					},
					error : function() {
						layer.msg("删除失败!", {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}

				});

				layer.close(cindex);
			}, function(cindex) {
				layer.close(cindex);
			});

		}

		$("#updateProcDef").click(function(){
			$("#upProDef").click();
		});
		$("#upProDef").change(function(){
		var loadingIndex=-1
			var options={
					url:"${APP_PATH}/process/doProDef.do",
					berforeSend:function(){
						loadingIndex = layer.msg('数据正在部署中', {icon: 6});
               			return true ; //必须返回true,否则,请求终止.
					},
					success:function(result){
						layer.close(loadingIndex);
						$("#proDefForm")[0].reset();
						
						if(result.success){
							layer.msg("流程部署成功", {time:1000, icon:6} , function(){                					
                				queryPage(0);
            				}); 
						}else{
							layer.msg("流程部署失败", {time:1000, icon:5, shift:6});
						}
					}
			};
			$("#proDefForm").ajaxSubmit(options);
		});
	</script>
	<script type="text/javascript" src="${APP_PATH}/script/menu.js "></script>
</body>
</html>
