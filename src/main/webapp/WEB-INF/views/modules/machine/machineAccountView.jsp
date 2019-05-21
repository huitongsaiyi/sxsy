<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>台账信息展示管理</title>
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
		<li><a href="${ctx}/machine/machineAccount/">台账信息展示列表</a></li>
		<li class="active"><a href="${ctx}/machine/machineAccount/form?id=${machineAccount.id}">台账信息展示<shiro:hasPermission name="machine:machineAccount:edit">${not empty machineAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="machine:machineAccount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="machineAccount" action="${ctx}/machine/machineAccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">machine_account_id：</label>
			<div class="controls">
				<form:input path="machineAccountId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报案时间：</label>
			<div class="controls">
				<input name="reportingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${machineAccount.reportingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门主键：</label>
			<div class="controls">
				<sys:treeselect id="deptId" name="deptId" value="${machineAccount.deptId}" labelName="" labelValue="${machineAccount.}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调解员ID，关联人员表主键：</label>
			<div class="controls">
				<sys:treeselect id="mediatorId" name="mediatorId" value="${machineAccount.mediatorId}" labelName="" labelValue="${machineAccount.}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者名称：</label>
			<div class="controls">
				<form:input path="patientName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医院名称。通过主键进行关联：</label>
			<div class="controls">
				<form:input path="hospitalId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重大：</label>
			<div class="controls">
				<form:input path="major" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保险公司名称：</label>
			<div class="controls">
				<form:input path="insuranceCompany" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保单号：</label>
			<div class="controls">
				<form:input path="policyNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起保日期：</label>
			<div class="controls">
				<input name="startInsuranceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${machineAccount.startInsuranceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纠纷发生日期：</label>
			<div class="controls">
				<form:input path="disputesTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出险日期：</label>
			<div class="controls">
				<form:input path="riskTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纠纷概要：</label>
			<div class="controls">
				<form:input path="summaryOfDisputes" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否重大 1是 0否：</label>
			<div class="controls">
				<form:input path="isMajor" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">诊疗方式：</label>
			<div class="controls">
				<form:input path="treatmentMode" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">治疗结果：</label>
			<div class="controls">
				<form:input path="treatmentResult" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患方反映焦点：</label>
			<div class="controls">
				<form:input path="patientsReflectFocus" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">相关专业：</label>
			<div class="controls">
				<form:input path="relatedMajor" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评估时间：</label>
			<div class="controls">
				<form:input path="assessTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评估号：</label>
			<div class="controls">
				<form:input path="assessNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">责任比例：</label>
			<div class="controls">
				<form:input path="dutyRatio" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈时间：</label>
			<div class="controls">
				<form:input path="feedbackTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议号：</label>
			<div class="controls">
				<form:input path="agreementNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">签署协议/判决时间：</label>
			<div class="controls">
				<form:input path="ratifyAccord" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议盖章时间：</label>
			<div class="controls">
				<form:input path="agreementStampTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议金额：</label>
			<div class="controls">
				<form:input path="agreementAmount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保险金额：</label>
			<div class="controls">
				<form:input path="insuranceAmount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交理赔时间：</label>
			<div class="controls">
				<form:input path="claimSettlementTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">赔付时间：</label>
			<div class="controls">
				<form:input path="compensateTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流转天数：</label>
			<div class="controls">
				<form:input path="flowDays" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">移交人：</label>
			<div class="controls">
				<form:input path="handOver" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归档时间：</label>
			<div class="controls">
				<form:input path="archiveTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卷宗编号：</label>
			<div class="controls">
				<form:input path="fileNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="machine:machineAccount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>