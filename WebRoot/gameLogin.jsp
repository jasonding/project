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
    
    <title>登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
	
	<script type="text/javascript">
	function regiest(){
		window.location.href="${ctx}/game/user/input.do";
	};
	</script>
	
  </head>
  
  <body>
  	<div id="body_id" align="center">
  		<s:if test="#session['com.project.game.user'] == null">
			<s:form id="form_login_id" action="gameLogin" namespace="/game" >
				<s:textfield name="username" label="用户名"></s:textfield>
				<s:password name="password" label="密码"></s:password>
				
				<tr><td><input id="registId" onclick="regiest()" type="button" value="注册"/></td><td><s:submit value="登录" theme="simple"/></td></tr>
			</s:form>
  		</s:if>
  		<s:else>
  			直接<a href="${ctx }/game/main.do">进入</a>
  			<script type="text/javascript">
				top.location.href = '${ctx }/game/main.do';
			</script>
  		</s:else>
	</div>
  </body>
</html>
