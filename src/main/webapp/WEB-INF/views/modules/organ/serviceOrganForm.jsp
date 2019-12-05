<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务机构信息管理</title>
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
		<li><a href="${ctx}/organ/serviceOrgan/">服务机构信息列表</a></li>
		<li class="active"><a href="${ctx}/organ/serviceOrgan/form?id=${serviceOrgan.id}">服务机构信息<shiro:hasPermission name="organ:serviceOrgan:edit">${not empty serviceOrgan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="organ:serviceOrgan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="serviceOrgan" action="${ctx}/organ/serviceOrgan/save" method="post" class="form-horizontal">
		<form:hidden path="serviceOrganId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-xlarge phone"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="input-xlarge email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作时间：</label>
			<div class="controls">
				<form:input path="workTime" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务机构简介：</label>
			<div class="controls">
				<form:textarea path="introduce" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主要责任：</label>
			<div class="controls">
				<form:textarea path="duty" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务宗旨：</label>
			<div class="controls">
				<form:textarea path="serviceTenet" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">三个援助：</label>
			<div class="controls">
				<form:textarea path="threeAid" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调解员：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${serviceOrgan.user.id}" labelName="" labelValue="${serviceOrgan.userName}"
					title="用户" url="/sys/office/treeData?type=3&officeType=1" checked="true" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="organ:serviceOrgan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>