<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<s:form id="login_form_id">
	<s:textfield name="username" label="用户名"></s:textfield>
	<s:password name="password" label="密码"></s:password>
	
	<tr><td></td><td><input type="button" value="登录" onclick="login()"/></td></tr>
</s:form>
