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
				$("#resource_displayType").change(function(){
					showOrHide(this);
				});
				$("#resource_resourceType").change(function(){
					resourceTypeChange(this);
				});
				resourceTypeChange("resource_resourceType");
				
			});
			function resourceTypeChange(owner) {
				var menu = "<s:property value='@com.project.enumeration.ResourceType@MENU.name()'/>";
				if($(owner).val() == menu) {
					$("#resource_displayType_tr").hide();
					$("#resource_nameSpace_tr").hide();
					$("#resource_orderView_tr").show();
				}else {
					$("#resource_displayType_tr").show();
					$("#resource_nameSpace_tr").show();
					$("#resource_orderView_tr").hide();
				}					
			}
			function showOrHide(owner) {
				var show = "<s:property value='@com.project.enumeration.DisplayType@SHOW.name()'/>";
				var hidden = "<s:property value='@com.project.enumeration.DisplayType@HIDDEN.name()'/>";
				if($(owner).val() == show) {
					$("#resource_orderView_tr").show();
					$("#resource_nameSpace_tr").show();
				}else if($(owner).val() == hidden) {
					$("#resource_orderView_tr").hide();
					$("#resource_nameSpace_tr").show();
				}
			}
		</script>
	</head>
	<body>
		<div align="center">
		<s:fielderror></s:fielderror>
			<s:form action="manage" namespace="/resource">
				<s:hidden name="resource.id" value="%{resource.id}"></s:hidden>
				<s:select id="resource_resourceType" list="@com.project.enumeration.ResourceType@values()"
					name="resource.resourceType" 
					listKey="name()" 
					listValue="name"
					label="资源类型"></s:select>
				<tr id="resource_displayType_tr" style="display: none;">
					<td>显示到菜单</td>
					<td>
						<s:select id="resource_displayType" list="@com.project.enumeration.DisplayType@values()"
							name="resource.displayType" 
							listKey="name()" 
							listValue="name"
							theme="simple"></s:select>
					</td>
				</tr>
				<s:textfield name="resource.resourceName" label="名称" />
				<tr id="resource_nameSpace_tr" style="display: none;">
					<td>资源:</td>
					<td><s:textfield  name="resource.resource"  theme="simple"/></td>
				</tr>
				<tr id="resource_orderView_tr" style="display: none;">
					<td>排序:</td>
					<td><s:textfield  name="resource.orderView"  theme="simple"/></td>
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
