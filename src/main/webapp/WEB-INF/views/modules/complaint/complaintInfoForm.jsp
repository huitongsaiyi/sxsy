<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
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
		<li><a href="${ctx}/complaint/complaintInfo/">投诉接待列表</a></li>
		<li class="active"><a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.id}">投诉接待<shiro:hasPermission name="complaint:complaintInfo:edit">${not empty complaintInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="complaint:complaintInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="complaintInfo" action="${ctx}/complaint/complaintInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">案件编号：</label>
			<div class="controls">
				<form:input path="caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访客姓名：</label>
			<div class="controls">
				<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访客电话：</label>
			<div class="controls">
				<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">与患者关系  字典维护：</label>
			<div class="controls">
				<form:input path="patientRelation" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者姓名：</label>
			<div class="controls">
				<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者性别：</label>
			<div class="controls">
				<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者年龄：</label>
			<div class="controls">
				<form:input path="patientAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来访人数：</label>
			<div class="controls">
				<form:input path="visitorNumber" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">涉及医院：</label>
			<div class="controls">
				<sys:treeselect id="involveHospital" name="involveHospital" value="${complaintInfo.involveHospital}" labelName="" labelValue="${complaintInfo.}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">涉及科室：</label>
			<div class="controls">
				<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="" labelValue="${complaintInfo.}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主键：</label>
			<div class="controls">
				<form:input path="complaintId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">涉及人员：</label>
			<div class="controls">
				<form:input path="involveEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投诉纠纷概要：</label>
			<div class="controls">
				<form:input path="summaryOfDisputes" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来访日期：</label>
			<div class="controls">
				<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投诉方式：</label>
			<div class="controls">
				<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否重大：</label>
			<div class="controls">
				<form:input path="isMajor" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">诉求：</label>
			<div class="controls">
				<form:input path="appeal" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接待人员：</label>
			<div class="controls">
				<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接待时间：</label>
			<div class="controls">
				<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下一处理环节：</label>
			<div class="controls">
				<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下一环节处理人：</label>
			<div class="controls">
				<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>