<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include.jsp"%>
<center>
	<br/><br/>
  	<div id="form_login_div_id">
	  	<s:if test="#session['com.project.game.user'] == null">
	  		欢迎畅游游戏<br/>
	  		<input id="registId" onclick="regiest()" type="button" value="注册"/>
	  		<input id="loginId" onclick="loginUI()" type="button" value="登录"/>
	  	</s:if>
		<s:else>
			欢迎<s:property value="#session['com.project.game.user'].name"/>登录成功
			<br/>
			<br/>
			用户信息:
			用户名：<s:property value="#session['com.project.game.user'].name"/><br/>
			创建时间：<s:date name="#session['com.project.game.user'].createTime" format="yyyy-MM-dd HH:mm:ss" /><br/>
			<input id="registId" onclick="ajax_gameUser_manage()" type="button" value="修改用户信息"/><br/><a href="${ctx }/game/logout.do">退出</a>
		</s:else>  
  	</div>
</center>

<script type="text/javascript">
	function ajax_gameUser_manage(){
		var data = "gameUser.id=" + <s:property value="#session['com.project.game.user'].id"/>;
		$.get("${ctx}/game/user/input.do", data, function(data){
			$("#form_login_div_id").html(data);
		});
	};
  </script>
