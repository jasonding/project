<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/default/easyui.css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
  </head>
  
  <body>
  	<div align="center">
		<s:form namespace="/game/user" action="manage">
			<s:hidden name="gameUser.id" />
			<s:date name="gameUser.createTime" format="yyyy-MM-dd HH:mm:ss" var="createTime"/>
			<s:hidden name="gameUser.createTime" value="%{#createTime}" />
			<s:textfield name="gameUser.name" label="用户名"></s:textfield>
			<s:if test="gameUser == null || gameUser.id == null">
				<s:password name="gameUser.password" label="密码"></s:password>
			</s:if>
			<tr><td><s:submit value="提交" theme="simple"/></td><td><input type="button" onclick=" window.history.go(-1)" value="返回"></input></td></tr>
		</s:form>
	</div>
  </body>
</html>
