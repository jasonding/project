<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>

 <script type="text/javascript">
	function regiest_submit(){
		var data = $("#regest_form_id").serialize();
		$.get(
				"${ctx}/game/user/manage.do", 
				data,
				function(data){
					$("#user_regest_form_id").html(data);
			    }
		);
	};
 </script>

<div id="user_regest_form_id">
<s:form namespace="/game/user" id="regest_form_id" action="manage">
	<s:hidden name="gameUser.id" />
	<s:date name="gameUser.createTime" format="yyyy-MM-dd HH:mm:ss" var="createTime"/>
	<s:hidden name="gameUser.createTime" value="%{#createTime}" />
	<s:textfield name="gameUser.name" label="用户名"></s:textfield>
	<s:if test="gameUser == null || gameUser.id == null">
		<s:password name="gameUser.password" label="密码"></s:password>
	</s:if>
	<tr><td> <input type="button" value="提交" onclick="regiest_submit()"/> </td><td></td></tr>
</s:form>
</div>
