<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估申请管理</title>
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
		<li><a href="${ctx}/assessapply/assessApply/">评估申请列表</a></li>
		<li class="active"><a href="${ctx}/assessapply/assessApply/form?id=${assessApply.id}">评估申请<shiro:hasPermission name="assessapply:assessApply:edit">${not empty assessApply.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="assessapply:assessApply:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="assessApply" action="${ctx}/assessapply/assessApply/save" method="post" class="form-horizontal">
		<form:hidden path="assessApplyId"/>
		<form:hidden path="createDate"/>
		<form:hidden path="createBy"/>
		<form:hidden path="complaintMainId"/>
		<form:hidden path="complaintMain.complaintMainId"/>
		<form:hidden path="complaintMain.act.taskId"/>
		<form:hidden path="complaintMain.act.taskName"/>
		<form:hidden path="complaintMain.act.taskDefKey"/>
		<form:hidden path="complaintMain.act.procInsId"/>
		<form:hidden path="complaintMain.act.procDefId"/>
		<form:hidden path="complaintMain.procInsId"/>
		<form:hidden id="flag" path="complaintMain.act.flag"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#patient" data-toggle="tab">申请表(患方)</a>
			</li>
			<li>
				<a href="#hospital" data-toggle="tab">申请表(医方)</a>
			</li>
			<li>
				<a href="#annex" data-toggle="tab">附件</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="patient">
				<table class="table-form">
					<tr>
						<td class="tit" width="180px"><font color="red">*</font>申请人：</td>
						<td width="429px">
							<form:input path="patientApplyer" htmlEscape="false" maxlength="32" class="input-xlarge "/>
						</td>
						<td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>
						<td width="476px">
							<form:select path="patientRelation" class="input-medium">
								<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<tr>

						<td class="tit" width="180px"><font color="red">*</font>电话：</td>
						<td width="429px">
							<form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
						</td>
						<td class="tit" width="199px">患者姓名：</td>
						<td width="522px">
							<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
						</td>
					</tr>
					<tr>

						<td class="tit" width="180px">性别：</td>
						<td >
								<%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
							<form:select path="complaintMain.patientSex" class="input-medium">
								<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
						<td class="tit">年龄：</td>
						<td >
							<form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
					</tr>
					<tr >
						<td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>
						<td width="522px">
							<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${reportRegistration.complaintMain.involveHospital}" labelName="" labelValue="${reportRegistration.complaintMain.hospital.name}"
											title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
						<td width="522px">
							<form:input path="patientApplyMatter" htmlEscape="false" maxlength="200" class="input-xlarge "/>
						</td>

					</tr>

				</table>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">

					<tr>
						<td class="tit" width="180px"><font color="red">*</font>申请医院：</td>
						<td width="429px">
							<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${reportRegistration.complaintMain.involveHospital}" labelName="" labelValue="${reportRegistration.complaintMain.hospital.name}"
											title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
						</td>
						<td class="tit" width="199px"><font color="red">*</font>代理人：</td>
						<td>
							<form:input path="agent" htmlEscape="false" maxlength="10" class="input-xlarge "/>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>电话：</td>
						<td class="controls">
							<form:input path="hospitalMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
						</td>
						<td class="tit"><font color="red">*</font>患者姓名：</td>
						<td class="controls">
							<form:input path="hospitalName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>性别：</td>
						<td class="controls">
							<form:input path="hospitalSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
						</td>
						<td class="tit"><font color="red">*</font>年龄：</td>
						<td class="controls">
							<form:input path="hospitalAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
						<td width="522px">
							<form:input path="hospitalApplyMatter" htmlEscape="false" maxlength="200" class="input-xlarge "/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="annex">
				<%--<input type="hidden"  name="fjtype" value="0">
				<td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">

					<input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"  value="${reportRegistration.files}"/>
					<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						&lt;%&ndash;<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />&ndash;%&gt;
					<sys:ckfinder input="files" type="images"  uploadPath="/reportReigsation/annex" selectMultiple="true" maxWidth="100" maxHeight="100"/>
				</td>--%>
			</div>
		</div>
	<table class="table-form">
		<tr>
			<td class="tit" width="170px">申请类型：</td>
			<td width="470px">
				<form:select path="applyType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">下一环节处理人：</td>
			<td >
				<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${reportRegistration.nextLinkMan}" labelName="" labelValue="${reportRegistration.linkEmployee.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true" checked="true"/>
			</td>
		</tr>

	</table>
		<div class="form-actions">
			<shiro:hasPermission name="assessapply:assessApply:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="assessapply:assessApply:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty assessApply.assessApplyId}">
			<act:histoicFlow procInsId="${assessApply.complaintMain.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>

<%--
<div class="control-group">
			<label class="control-label">下一处理环节：</label>
			<div class="controls">
				<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
<div class="control-group">
			<label class="control-label">处理人：</label>
			<div class="controls">
				<form:input path="handlePeople" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理日期：</label>
			<div class="controls">
				<form:input path="handleTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>--%>