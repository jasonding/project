<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加/修改权限页面</title>
		<link rel="stylesheet" type="text/css"
			href="${ctx }/css/jqueryUI/themes/default/easyui.css" />
		<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#privilege_privilegeType").change(function(){
					showOrHide(this);
				});
				showOrHide($("#privilege_privilegeType"))
			});
			function showOrHide(owner) {
				var menu = "<s:property value='@com.project.enumeration.PrivilegeType@MENU.name()'/>";
				var link = "<s:property value='@com.project.enumeration.PrivilegeType@LINK.name()'/>";
				if($(owner).val() == menu) {
					$("#privilege_orderView_tr").show();
					$("#privilege_nameSpace_tr").hide();
				}else if($(owner).val() == link) {
					$("#privilege_orderView_tr").show();
					$("#privilege_nameSpace_tr").show();
				}else {
					$("#privilege_orderView_tr").hide()
					$("#privilege_nameSpace_tr").hide();
				}
			}
		</script>
	</head>
	<body>
		<div align="center">
		<s:fielderror></s:fielderror>
			<s:form >
				<s:if test="params != null">
					<s:hidden name="params.parentPrivilege" value="%{params.parentPrivilege}"></s:hidden>
				</s:if>
				<s:select id="privilege_privilegeType" list="@com.project.enumeration.PrivilegeType@values()"
					name="privilege.privilegeType" 
					listKey="name()" 
					listValue="name"
					label="权限类型"></s:select>
				<s:select id="privilege_parentPrivilege" list="parentPrivilegeList" name="privilege.parentPrivilege"
					listValue="name" listKey="privilegePK" value="privilege.parentPrivilege.privilegePK"
				 	headerKey="-100"  headerValue="请选择..." label="父权限"
				></s:select>
				<s:if test="privilege != null && privilege.privilegePK != null">
					<s:textfield name="privilege.privilegePK.module" readonly="true" label="模块" />
					<s:textfield name="privilege.privilegePK.privilege" readonly="true" label="权限" />
				</s:if>
				<s:else>
					<s:textfield name="privilege.privilegePK.module" label="模块" />
					<s:textfield name="privilege.privilegePK.privilege" label="权限" />
				</s:else>
				<s:textfield name="privilege.name" label="名称" />
				<tr id="privilege_nameSpace_tr" style="display: none;">
					<td>命名空间:</td>
					<td><s:textfield  name="privilege.nameSpace"  theme="simple"/></td>
				</tr>
				<tr id="privilege_orderView_tr" style="display: none;">
					<td>排序:</td>
					<td><s:textfield  name="privilege.orderView"  theme="simple"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit action="manage" value="提交" align="center" theme="simple"/>
						<input type="button" value="返回" onclick="window.history.go(-1)" />
					</td>				
				</tr>
			</s:form>
		</div>
	</body>
</html>
