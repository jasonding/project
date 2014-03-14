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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control"  content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
  </head>
  
  <script type="text/javascript">
	function regiest(){
		$.get("${ctx}/game/user/input.do", function(data){
			$("#form_login_div_id").html(data);
		});
	};

	function loginUI(){
		$.get("${ctx}/game/loginUI.do", function(data){
			$("#form_login_div_id").html(data);
		});
	};
	function login1(){
			var data = $("#login_form_id").serialize();
			$.get("${ctx}/game/gameLogin.do", data,function(data){
				$("#form_login_div_id").html(data);
				window.location.href="${ctx}/game/main.do";
			});
	};
  </script>
  <body>
  	<div id="form_login_div_id" align="center">
		<s:form id="login_form_id" action="gameLogin" namespace="/game" >
			<s:textfield name="username" label="用户名"></s:textfield>
			<s:password name="password" label="密码"></s:password>
			
			<tr><td></td><td><input type="button" value="登录" onclick="login1()"/></td></tr>
		</s:form>
	</div>
  </body>
</html>
