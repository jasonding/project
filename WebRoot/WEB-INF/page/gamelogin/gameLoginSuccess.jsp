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
	function login(){
		var data = $("#login_form_id").serialize();
		$.get("${ctx}/game/gameLogin.do", data,function(data){
			$("#form_login_div_id").html(data);
		});
	};
  </script>
  
<body>
	<s:include value="gameLoginSuccessWiget.jsp"></s:include>  
</body>
</html>
