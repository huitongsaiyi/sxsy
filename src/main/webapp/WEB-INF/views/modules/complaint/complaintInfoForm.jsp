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
		<form:hidden path="complaintId"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#visitor" data-toggle="tab">来访者信息</a>
			</li>
			<li>
				<a href="#patient" data-toggle="tab">患者信息</a>
			</li>
			<li>
				<a href="#hospital" data-toggle="tab">涉及医院信息</a>
			</li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="visitor">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px">访客姓名：</td>
						<td >
							<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
						</td>
						<td class="tit" width="180px">访客电话：</td>
						<td >
							<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
						</td>
					</tr>
					<tr >
						<td class="tit">与患者关系：</td>
						<td >
							<form:input path="patientRelation" htmlEscape="false" maxlength="1" class="input-xlarge "/>
						</td>
						<td class="tit">来访人数：</td>
						<td >
							<form:input path="visitorNumber" htmlEscape="false" maxlength="10" class="input-xlarge "/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="patient">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px">患者姓名：</td>
						<td >
							<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
						</td>
						<td class="tit" width="180px">患者性别：</td>
						<td >
							<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
						</td>
					</tr>
					<tr >
						<td class="tit">患者年龄：</td>
						<td >
							<form:input path="patientAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px">涉及医院：</td>
						<td >
							<sys:treeselect id="involveHospital" name="involveHospital" value="${complaintInfo.involveHospital}" labelName="" labelValue="${complaintInfo.involveHospital}"
											title="部门" url="/sys/office/treeData?type=2&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						</td>
						<td class="tit" width="210px">涉及科室：</td>
						<td >
							<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="" labelValue="${complaintInfo.involveDepartment}"
											title="部门" url="/sys/office/treeData?type=2&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
						</td>
					</tr>
					<tr >
						<td class="tit">涉及人员：</td>
						<td class="controls">
							<form:input path="involveEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	<table class="table-form">
		<tr >
			<td class="tit">案件编号：</td>
			<td >
				<form:input path="caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</td>
			<td class="tit">来访日期：</td>
			<td >
				<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit">投诉方式：</td>
			<td >
				<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</td>
			<td class="tit">是否重大：</td>
			<td >
				<form:input path="isMajor" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit">投诉纠纷概要：</td>
			<td colspan="3">
				<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit">诉求：</td>
			<td colspan="3">
				<form:textarea path="appeal" htmlEscape="false" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit">接待人员：</td>
			<td >
				<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</td>

			<td class="tit">接待时间：</>
			<td >
				<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit">下一处理环节：</td>
			<td >
				<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</td>
			<td class="tit">下一环节处理人：</td>
			<td >
				<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</td>
		</tr>
	</table>
		<div class="form-actions">
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>