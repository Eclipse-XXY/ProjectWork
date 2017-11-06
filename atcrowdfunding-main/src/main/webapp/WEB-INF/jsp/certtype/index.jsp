<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
    
    input[type=checkbox] {
        width:18px;
        height:18px;        
    }
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 分类管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<%@include file="/WEB-INF/jsp/common/userinfo.jsp" %>
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
				<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据矩阵</h3>
			  </div>
			  <div class="panel-body">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th>名称</th>
                  <th >商业公司</th>
                  <th >个体工商户</th>
                  <th >个人经营</th>
                  <th >政府及非营利组织</th>
                </tr>
              </thead>
              <tbody>
              
	              <c:forEach items="${certList }" var="cert">
	                <tr>
<!-- 	                添加的自定义属性 -->
	                  <td>${cert.name }</td>
	                  <td><input type="checkbox" certid="${cert.id }" accttype="0"></td>
	                  <td><input type="checkbox" certid="${cert.id }" accttype="1"></td>
	                  <td><input type="checkbox" certid="${cert.id }" accttype="2"></td>
	                  <td><input type="checkbox" certid="${cert.id }" accttype="3"></td>
	                </tr>
                  </c:forEach>
                  
                 
              </tbody>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	
        <script type="text/javascript">
	        <c:forEach items="${certAccttypeList }" var="certAccttype">
// 	        dom标签和jQuery标签的属性之间的转化
		   	   //$("input[certid='${certAccttype.certid }'][accttype='${certAccttype.accttype}']").attr("checked",true);
		   	   //$("input[certid='${certAccttype.certid }'][accttype='${certAccttype.accttype}']").attr("checked","checked");
		   	   $("input[certid='${certAccttype.certid }'][accttype='${certAccttype.accttype}']")[0].checked=true;
		    </c:forEach>
   
        
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
			    
			    
			    $("input:checkbox").click(function(){
			    	
			    	var checkedStatus = this.checked;
			    	
			    	
			    	var certid = $(this).attr("certid");
			    	var accttype = $(this).attr("accttype");

			    	if(checkedStatus){
			    		$.ajax({
			    			type : "post",
			    			data : {
			    				certid : certid ,
			    				accttype : accttype
			    			},
			    			url : "${APP_PATH}/certtype/saveCerttype.do",
			        		success : function(result){
			        			if(result.success){
			        				
			        			}else{
			        				layer.msg("操作失败!", {time:1000, icon:5, shift:6});
			        			}
			        		}
			    			
			    		}); 
			    	}else{
			    		$.ajax({
			    			type : "post",
			    			data : {
			    				certid : certid ,
			    				accttype : accttype
			    			},
			    			url : "${APP_PATH}/certtype/deleteCerttype.do",
			        		success : function(result){
			        			if(result.success){
			        				
			        			}else{
			        				layer.msg("操作失败!", {time:1000, icon:5, shift:6});
			        			}
			        		}
			    			
			    		});
			    	}
			    });
			    
			    
            });
        </script>
  </body>
</html>
    