<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>协议书管理</title>
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
		<li><a href="${ctx}/signtype/signTypeInfo/">协议书列表</a></li>
		<li class="active"><a href="${ctx}/signtype/signTypeInfo/form?id=${signTypeInfo.id}">协议书<shiro:hasPermission name="signtype:signTypeInfo:edit">${not empty signTypeInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="signtype:signTypeInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="signTypeInfo" action="${ctx}/signtype/signTypeInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类型主键：</label>
			<div class="controls">
				<form:input path="typeId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签署协议主键：</label>
			<div class="controls">
				<form:input path="signId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型名称：</label>
			<div class="controls">
				<form:input path="typeName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联模块 1.意见书中的&ldquo;分析意见&rdquo; 2.意见书中的&ldquo;结论&rdquo;  3 签署协议中的&ldquo;调解情况&rdquo; 4.签署协议中的&ldquo;协议约定事项&rdquo; 5..签署协议中的&ldquo;履行协议方式&rdquo; 6. .签署协议中的&ldquo;协议说明&rdquo;：</label>
			<div class="controls">
				<form:input path="relationModel" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="signtype:signTypeInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>