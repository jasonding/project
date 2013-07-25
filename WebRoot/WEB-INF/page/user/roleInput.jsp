<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加用户页面</title>
		<link rel="stylesheet" type="text/css"
			href="${ctx }/css/jqueryUI/themes/default/easyui.css" />
		<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
	</head>
	<body>
		<div align="center">
			<s:form namespace="/role" action="manage">
				<s:hidden name="role.id" />
				<s:textfield name="role.name" label="名称:" />
				<s:iterator value="privilegeList" var="privilege">
					<s:set value="false" var="checked"></s:set>
					<s:if test="role.privilegeSet != null">
						<s:iterator value="role.privilegeSet" var="rolePrivilege">
							<s:if test="#rolePrivilege == #privilege">
								<s:set value="true" var="checked"></s:set>
							</s:if>
						</s:iterator>
					</s:if>
					<s:checkbox name="role.privilegeSet" label="%{name}" 
						fieldValue="%{privilegePK}" value="#checked"></s:checkbox>
				</s:iterator>
				
				<s:submit value="提交" align="center" />
				<s:submit action="list" value="返回" align="center" />
			</s:form>
		</div>
	</body>
</html>
