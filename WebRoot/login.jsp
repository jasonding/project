<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var verifyCodeUrl = "${ctx}/randomCode.servlet?";
		$(function() {
			$('#codeImg').attr('src',verifyCodeUrl + (new Date()).getTime());
			$('#codeImg').click(function() {
				$(this).attr('src',verifyCodeUrl + (new Date()).getTime());
			});
		});
	</script>
		
  </head>
  
  <body>
  	<div align="center">
		<s:form action="login" namespace="/" >
			<s:textfield name="j_username" label="用户名"></s:textfield>
			<s:password name="j_password" label="密码"></s:password>
			<s:textfield name="randomCode" label="验证码"/><s:fielderror></s:fielderror>
			<s:submit method="login" value="登录" align="center"/>
		</s:form>
		<s:property value="#session.SPRING_SECURITY_LAST_EXCEPTION.extraInformation"/>
		<s:debug></s:debug>
    <br>  
    <%
    
    Enumeration namesEnumeration = session.getAttributeNames();
    while(namesEnumeration.hasMoreElements()) {
    	//System.out.println(namesEnumeration.nextElement());
    	System.out.println(session.getAttribute(namesEnumeration.nextElement() + ""));
    }
    %>  
    <span><img id="codeImg" border=0>再换一张</span>   
		<s:property value="%{exception.message}"/>${message}
	</div>
  </body>
</html>
