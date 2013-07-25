<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>添加用户页面</title>	
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/default/easyui.css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
  </head>
  
  <body>
  	<div align="center">
		<s:form>
			<s:hidden name="user.id" />
			<s:date name="user.createTime" format="yyyy-MM-dd HH:mm:ss" var="createTime"/>
			<s:hidden name="user.createTime" value="%{#createTime}" />
			<s:textfield name="user.name" label="用户名"></s:textfield>
			<s:password name="user.password" label="密码"></s:password>
			<s:radio name="user.gender" list="@com.project.enumeration.Gender@values()" listKey="name()" listValue="name" label="性别"></s:radio>
			<s:checkboxlist name="roleIds" list="roleList" listKey="id" listValue="name" label="角色"></s:checkboxlist>
			<s:submit action="manage" method="manage" value="提交" /> <s:submit onclick=" window.history.go(-1)" value="返回"></s:submit>
		</s:form>
	</div>
  </body>
</html>
