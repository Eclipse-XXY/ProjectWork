<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul style="padding-left:0px;" class="list-group">
<%-- <c:forEach items="${sessionScope.root_permission.children}" var="permission"> --%>
<c:forEach items="${sessionScope.root_permission.children}" var="permission">
<c:if test="${ empty permission.children}">
	<li class="list-group-item tree-closed" >
		<a href="${APP_PATH }/${permission.url}"><i class="${permission.icon}"></i>${permission.name}</a> 
	</li>
</c:if>
<c:if test="${not empty permission.children}">
     <li class="list-group-item tree-closed">
		<span><i class="glyphicon glyphicon glyphicon-tasks"></i> ${permission.name } <span class="badge" style="float:right">${fn:length(permission.children) }</span></span> 
		<ul style="margin-top:10px;display:none;">
		 <c:forEach items="${permission.children}" var="innerpermission" >
			<li style="height:30px;">
				<a href="${APP_PATH }/${innerpermission.url}"><i class="${innerpermission.icon}"></i> ${innerpermission.name}</a> 
			</li>
		</c:forEach>
		</ul>
	 </li>
</c:if>
</c:forEach>
</ul>
