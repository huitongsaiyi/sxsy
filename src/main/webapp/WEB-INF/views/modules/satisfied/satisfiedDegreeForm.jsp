<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>满意度管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/satisfied/satisfiedDegree/">满意度列表</a></li>
		<li class="active"><a href="${ctx}/satisfied/satisfiedDegree/form?id=${satisfiedDegree.id}">满意度<shiro:hasPermission name="satisfied:satisfiedDegree:edit">${not empty satisfiedDegree.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="satisfied:satisfiedDegree:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="satisfiedDegree" action="${ctx}/satisfied/satisfiedDegree/save" method="post" class="form-horizontal">
		<form:hidden path="satisfiedId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">申请人姓名：</label>
			<div class="controls">
				<form:input path="satisfiedName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调解能力：</label>
			<div class="controls">
				<form:input path="ability" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务态度：</label>
			<div class="controls">
				<form:input path="attitude" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仪容仪表：</label>
			<div class="controls">
				<form:input path="meter" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价：</label>
			<div class="controls">
				<form:textarea path="assess" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建议：</label>
			<div class="controls">
				<form:textarea path="proposal" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="satisfied:satisfiedDegree:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>