<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>欢迎页面</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/jqueryUI/themes/default/easyui.css"></link>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/jqueryUI/themes/icon.css" ></link>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css" ></link>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$.messager.show({
					title:'Title',
					msg:'欢迎进去project',
					timeout:3000,
					showType:'slide'
				});
			});
		</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height:60px;background:#B3DFDA;"><h2>PROJECT</h2></div>
	<div region="west" split="true" title="West" style="width:150px;padding:10px;">
		<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
			<s:iterator value="menuList">
			<li state="closed">
				<span><s:property value="privilegePK.module"/></span>
				<s:iterator value="childPrivilegeSet">
					<ul>
						<li>
						<span>
							<s:url var="privilegeUrl" namespace="%{nameSpace}" action="list" method="list"></s:url>
							<a href="<s:property value="#privilegeUrl"/>" target="center_data"
								title="<s:property value="privilegePK.module"/>"><s:property value="privilegePK.module"/></a>
						</span>
						</li>
					</ul>
				</s:iterator>
			</li>			
			</s:iterator>
		</ul>
		<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
			<li state="closed">
				<span>系统管理</span>
				<ul>
					<li><span><a href="<s:url namespace="/user" action="list" method="list"></s:url>" target="center_data" title="用户列表">用户列表</a></span></li>
					<li><span><a href="<s:url namespace="/role" action="list" method="list"></s:url>" target="center_data" title="角色列表">角色列表</a></span></li>
					<li><span><a href="<s:url namespace="/privilege" action="list" method="list"></s:url>" target="center_data" title="权限列表">权限列表</a></span></li>
				</ul>
			</li>
		</ul>
	</div>
	<div id="center" region="center" title="&nbsp;您好，<span class='red'><s:property value="#session['com.project.user'].name"/></span>！
	欢迎登录！ 你的角色是：<span class='red'>
							<s:iterator value="#session['com.project.user'].roleSet">
								<s:property value="name"/>&nbsp;&nbsp;
							</s:iterator>
						</span>
		&nbsp;&nbsp;&nbsp;&nbsp; <a href='<s:url namespace="/" action="logout" method="logout"></s:url>'>退出</a>
	">
			<iframe id="center_data" name="center_data" src="${ctx}/index.jsp" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>