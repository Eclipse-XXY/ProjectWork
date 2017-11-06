.<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <title>角色维护</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
		<jsp:include page="/common/userInfo.jsp"></jsp:include>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
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
				<ul style="padding-left:0px;" class="list-group">
					<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input  id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button id="deleteBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button id="addBtn" onclick="window.location.href='${APP_PATH}/role/add.htm'" type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">序号</th>
				  <th width="30"><input id="checkAll" type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody >
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
							 </ul>
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
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    //表示默认显示第一行
			  
			    <c:if test="${empty param.pageNo}">
			     queryPageRole(1);
			    </c:if>
			    <c:if test="${not empty param.pageNo}">
			     var pageNo="${param.pageNo}";
			     queryPageRole(pageNo);
			    </c:if>
            });
            function pageChange(pageNo){
            	queryPageRole(pageNo);
            };
            var dataObj= {
		    		"pageNo":1,
		    		"pageSize" : 3 ,   
		   };
            function queryPageRole(pageNo){
            	dataObj.pageNo=pageNo;
            	var loadIndex=-1 ;
			    $.ajax({
			    	type : "POST",
			    	url : "${APP_PATH }/role/doindex.do",
			    	data :dataObj,
			    	beforeSend:function(){
			    		//直接放行
			    		//var index = layer.load(0, {shade: false}); 
			    	var loadIndex = layer.load(1, {shade: [0.1,'#fff'] ,time: 1000}); 
			    		return true;
			    	},
			    	success: function(result){
			    		layer.close(loadIndex);
			    		if(result.success){
			    			var page=result.page;
			    			var data=page.data;
			    			var content = '';
			    		$.each(data, function(index, role){
			    			content+='<tr>';
            				content+='  <td>'+(index+1)+'</td>';
            				content+='  <td><input type="checkbox"   class="checkboxClass"  value="'+role.id+'"></td>';
            				content+='  <td>'+role.name+'</td>';
            				
            				content+='  <td>';
            				content+='	  <button type="button" onclick="window.location.href=\'${APP_PATH}/role/assign.htm?roleId='+role.id+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
            				content+='	  <button type="button" onclick="window.location.href=\'${APP_PATH}/role/edit.htm?pageNo='+pageNo+'&id='+role.id+'\'"class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
            				content+='	  <button type="button" onclick="deleteRole('+role.id+',\''+role.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            				content+='  </td>';
            				content+='</tr>';                                                                                                    
			    			});
			    	
			    		   $("tbody").html(content);
			    		   var contentNavigater = '' ;
           				
           				if(page.pageNo == 1){
           					contentNavigater += '<li class="disabled"><a href="#">上一页</a></li>';
           				}else{
           					contentNavigater += '<li><a href="#" onclick="pageChange('+(page.pageNo-1)+')">上一页</a></li>';
           				}
           				
           				for(var i=1 ; i<=page.totalNo ; i++){
           					if(page.pageNo==i){
           						contentNavigater += '<li class="active"><a href="#" onclick="pageChange('+i+')">'+i+'</a></li>';
           					}else{
           						contentNavigater += '<li><a href="#" onclick="pageChange('+i+')">'+i+'</a></li>';
           					}            					
           				}
           				
           				if(page.pageNo == page.totalNo){
           					contentNavigater += '<li class="disabled"><a href="#">下一页</a></li>';
           				}else{
           					contentNavigater += '<li><a href="#" onclick="pageChange('+(page.pageNo+1)+')">下一页</a></li>';
           				}
           				
           				
           				$(".pagination").html(contentNavigater);
			    		}else{
			    			layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});	
			    		};
			    	
			    	},
			    	error:function(){
			    	
			    		layer.msg("系统异常", {time:1000, icon:5, shift:6});	
			    	}
			    });
				return ;
			    };
			    $("#queryBtn").click( function(){
	            	var queryText=$("#queryText").val();
	            	if($.trim(queryText)==""){
	  				   layer.msg("请输入查询条件在做查询", {time:1000, icon:5, shift:6},function(){
	           			$("#queryText").focus();
	           		});	
	  				  return;
	            	}  
	 				dataObj.queryText=$.trim(queryText);
	 				 queryPageRole(1);
//	  				它默认差出来就放第一页，分页显示是有pageChange实现的
	            });
         
			    function deleteRole(id,name){

	    			layer.confirm("是否要"+name+"删除角色?",  {icon: 3, title:'提示'}, function(cindex){
	    				
	    				$.ajax({
	    					url : "${APP_PATH}/role/deleteRole.do",
	    					type : "POST",
	    					data : {
	    						id:id
	    						},
	    					success : function(result){
	    						if(result.success){
	    							layer.msg("删除"+name+"角色成功!", {time:1000, icon:6}, function(){
	    								 queryPageRole(1);
	    							});
	    						}else{
	    							layer.msg("删除"+name+"角色失败!", {time:1000, icon:5, shift:6});
	    						}
	    					}
	    				});
	    				
	    			    layer.close(cindex);
	    			}, function(cindex){    				
	    			    layer.close(cindex);
	    			});
	
			    };
			   //将表头上的复选框的状态赋值给tbody里面的复选框状态的步骤
			   //第一步就是先把表头的复选框状态保存下来然后遍历给tbody里面的子标签进行赋值的操作
			  $(".table thead :checkbox").click(function(){
				  var checked=this.checked;
				  var checkList=$(".table tbody :checkbox");
				  $.each(checkList,function(index,n){
					  n.checked=checked;
				  });
				  
			   }); 
			
              $("#deleteBtn").click(function(){
            	  var checkedlist=$(".checkboxClass:checked");
            	  var dataObjs = {};
					$.each(checkedlist,function(i,n){
						
						dataObjs["datas["+i+"].id"]=n.value;
						dataObjs["datas["+i+"].name"]="111";
	       			});
			   if(checkedlist.length>0){
				   layer.confirm("确定要删除吗?",  {icon: 3, title:'提示'}, function(cindex){
       
           			$.ajax({
           				type : "POST",
           				url : "${APP_PATH}/role/batchDelete.do",
           				data : dataObjs ,
           				success : function(result){
           					if(result.success){
	            					layer.msg("删除角色成功!", {time:1000, icon:6}, function(){
	            						 queryPageRole(1);
	    							});
           					}else{
           						layer.msg("删除角色失败!", {time:1000, icon:5});
           					}
           				}
           			});
       			    layer.close(cindex);
       			}, function(cindex){
       			    layer.close(cindex);
       			});
      
			   }
			   }); 
            $("tbody .btn-success").click(function(){
                window.location.href = "assignPermission.html";
            });
        </script>
           <script type="text/javascript" src="${APP_PATH}/script/menu.js "></script>
  </body>
</html>
