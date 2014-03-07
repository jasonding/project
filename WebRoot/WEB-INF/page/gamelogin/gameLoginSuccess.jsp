<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control"  content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
  </head>
  
  <body>
  <center>
	<br/><br/>
	欢迎<s:property value="#session['com.project.game.user'].name"/>登录成功
	<br/>
	<br/>
	用户信息:
	用户名：<s:property value="#session['com.project.game.user'].name"/><br/>
	创建时间：<s:date name="#session['com.project.game.user'].createTime" format="yyyy-MM-dd HH:mm:ss" /><br/>
	<a href="${ctx }/game/user/input.do?gameUser.id=<s:property value="#session['com.project.game.user'].id"/>" onclick="ajax_gameUser_manage()"/>修改用户信息</a> <br/><a href="${ctx }/game/logout.do">退出</a>
</center>
</body>
</html>
