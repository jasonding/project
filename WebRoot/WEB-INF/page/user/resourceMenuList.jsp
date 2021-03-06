<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>权限列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryUI/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
	<style>
		.toolbar{
			height:30px;
			padding:5px;
		}
	</style>
	<script>
	$(function(){
		$("tr.beover").mouseover(function() {
			$(this).addClass("over");
		}).mouseout(function() {
			$(this).removeClass("over");
		});
	});
	</script>
</head>
<body>
<div class="toolbar">
	<a iconcls="icon-add" class="easyui-linkbutton l-btn" 
		href="<s:url action="input" namespace="/resource" />" id="add">添加资源</a>
</div>

<div class="pagediv">菜单列表</div>
	<div>
		<table cellpadding="0" cellspacing="0" border="0" class="querytab">
			<thead>
				<tr class="font_bold">
					<td>排序值</td><td>菜单名称</td><td>操作</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="menuList">
					<tr class="beover">
						<td><s:property value="orderView"/></td>
						<td><s:property value="resourceName"/></td>
						<td>
							<s:url var="eidturl" action="input" namespace="/resource" escapeAmp="false" includeParams="all">
								<s:param name="resource.id" value="%{id}"></s:param>
							</s:url>
							<a href="<s:property value="#eidturl" escape="true"/>">修改</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>