<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>权限列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
	<style>
		.toolbar{
			height:30px;
			padding:5px;
		}
	</style>
	<script>
	$(function(){
		$("tr.beover").mouseover(function() {
			$(this).addClass("over");
		}).mouseout(function() {
			$(this).removeClass("over");
		});
	});
	</script>
</head>
<body>
<div class="toolbar">
	<a iconcls="icon-add" class="easyui-linkbutton l-btn" 
		href="<s:url action="input" namespace="/resource" />" id="add">添加资源</a>
</div>

<div class="pagediv">权限列表</div>
	<div>
		<table cellpadding="0" cellspacing="0" border="0" class="querytab">
			<thead>
				<tr class="font_bold">
					<td>排序值</td><td>模块</td><td>权限</td><td>权限名称</td><td>权限类型</td><td>父权限名称</td><td>操作</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="pageView.recordList">
					<tr class="beover">
						<td><s:property value="orderView"/></td>
						<td><s:property value="privilegePK.module"/></td>
						<td><s:property value="privilegePK.privilege"/></td>
						<td><s:property value="name"/></td>
						<td><s:property value="privilegeType.name"/></td>
						<td><s:property value="parentPrivilege.name"/></td>
						<td>
							<s:url var="eidturl" action="input" namespace="/privilege" escapeAmp="false" includeParams="all">
								<s:param name="privilege.privilegePK" value="privilegePK.module + '_:_' + privilegePK.privilege"></s:param>
								<s:param name="params.parentPrivilege" value="params.parentPrivilege"></s:param>
							</s:url>
							<s:url var="delurl" action="delete" namespace="/privilege" escapeAmp="false" includeParams="all">
								<s:param name="privilege.privilegePK" value="privilegePK.module + '_:_' + privilegePK.privilege"></s:param>
							</s:url>
							<a href="<s:property value="#eidturl" escape="true"/>">修改权限</a>
							<s:if test="parentPrivilege == null">
								<s:url var="addChildUrl" action="input" namespace="/privilege" escapeAmp="false" includeParams="all">
									<s:param name="privilege.parentPrivilege.privilegePK" value="privilegePK.module + '_:_' + privilegePK.privilege"></s:param>
									<s:param name="params.parentPrivilege" value="params.parentPrivilege"></s:param>
								</s:url>
								<s:url var="listChildrenUrl" action="list" namespace="/privilege" escapeAmp="false" includeParams="all">
									<s:param name="params.parentPrivilege" value="privilegePK.module + '_:_' + privilegePK.privilege"></s:param>
								</s:url>
								 | <a href="<s:property value="#addChildUrl" escape="true"/>">添加子权限</a> |
								<a href="<s:property value="#listChildrenUrl" escape="true"/>">查看子权限</a>
							</s:if>
							 | <a href="<s:property value="#delurl" escape="true"/>">删除权限</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="orangepagediv" align="center"><%@include file="/WEB-INF/page/common/pageView.jspf" %></div>
	    <s:form id="queryConditionHiddenForm" action="list" namespace="/user">
	    	<s:hidden id="currentPage" name="currentPage" value="1" />
	    	<s:iterator value="params">
	    		<s:hidden name="params.%{key}" value="%{value}"></s:hidden>
	    	</s:iterator>
	    </s:form>
	</div>
</body>
</html>