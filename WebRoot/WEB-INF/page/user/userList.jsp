<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
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
	<a href="<s:url action="input" namespace="/user" />" id="add"
		iconcls="icon-add" class="easyui-linkbutton l-btn">Add</a>
</div>
	<s:form id="queryForm" action="list" namespace="/user">
		用户名：<s:textfield name="params.userName"></s:textfield>
		<s:submit value="查询"></s:submit>
	</s:form>
	<div class="pagediv">用户列表</div>
	<div>
		<table cellpadding="0" cellspacing="0" border="0" class="querytab">
			<thead>
				<tr class="font_bold">
					<td>序号</td><td>用户名</td><td>性别</td><td>创建时间</td><td>更新时间</td><td>最后登录时间</td><td>操作</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="pageView.recordList">
					<tr class="beover">
						<td><s:property value="id"/></td>
						<td><s:property value="name"/></td>
						<td><s:property value="gender.name"/></td>
						<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> </td>
						<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:date name="lastLoginTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<s:url var="url" action="input" namespace="/user">
								<s:param name="user.id" value="id"></s:param>
							</s:url>
							<a href="<s:property value="#url"/>">修改</a>
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