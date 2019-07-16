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
			aa();
		});
		function aa() {
			var a = '${complaintMainDetail.typeName}';
			if(a=="其他"){
				$("#qitaanjian").show();
			}

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/complaintdetail/complaintMainDetail/">投诉接待列表</a></li>
		<li class="active"><a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">投诉接待<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit">${not empty complaintMainDetail.complaintMainDetailId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="complaintdetail:complaintMainDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="complaintMainDetail" action="${ctx}/complaintdetail/complaintMainDetail/save" method="post" class="form-horizontal">
		<form:hidden path="complaintMainDetailId"/>
		<form:hidden path="complaintMainId"/>
		<form:hidden path="complaintMain.complaintMainId"/>
		<form:hidden path="complaintMain.act.taskId"/>
		<form:hidden path="complaintMain.act.taskName"/>
		<form:hidden path="complaintMain.act.taskDefKey"/>
		<form:hidden path="complaintMain.act.procInsId"/>
		<form:hidden path="complaintMain.act.procDefId"/>
		<form:hidden id="flag" path="complaintMain.act.flag"/>
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
						<td class="tit" width="160px"><font color="red">*</font>来访者姓名：</td>
						<td width="476px">
							<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
						</td>
						<td class="tit" width="180px"><font color="red">*</font>联系电话：</td>
						<td >
							<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-xlarge required phone"/>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>与患者关系：</td>
						<td>
							<form:select path="patientRelation" class="input-medium required">
								<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
						<td class="tit"><font color="red">*</font>来访人数：</td>
						<td >
							<form:input path="visitorNumber" htmlEscape="false" maxlength="10" class="input-xlarge required digits"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="patient">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>患者姓名：</td>
						<td width="476px">
							<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
						</td>
						<td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
						<td >
								<%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
							<form:select path="complaintMain.patientSex" class="input-medium">
								<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>患者年龄：</td>
						<td >
							<form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="4" class="input-xlarge required digits"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>涉及医院：</td>
						<td class="controls" width="476px">
							<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${complaintMainDetail.complaintMain.involveHospital}" labelName="${complaintMainDetail.complaintMain.hospital.name}" labelValue="${complaintMainDetail.complaintMain.hospital.name}"
											title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" dataMsgRequired="请选择医院" allowClear="true" notAllowSelectParent="false" />
                            <span class="help-inline"> </span>
                        </td>
						<td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
						<td class="controls">
							<sys:treeselect id="involveDepartment" name="complaintMain.involveDepartment" value="${complaintMainDetail.complaintMain.involveDepartment}" labelName="${complaintMainDetail.complaintMain.department.name}" labelValue="${complaintMainDetail.complaintMain.department.name}"
                                            title="部门" url="/sys/office/treeData?type=2&officeType=2"
                                            pid="involveHospital" isAll="true" cssClass="required"
                                            dataMsgRequired="请选择科室" allowClear="true" notAllowSelectParent="true"
                                            disabled="true" />
                            <span class="help-inline"> </span>
                        </td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>涉及人员：</td>
						<td class="controls">
							<sys:treeselect id="involveEmployee" name="complaintMain.involveEmployee" value="${complaintMainDetail.complaintMain.involveEmployee}" labelName="${complaintMainDetail.complaintMain.employee.name}" labelValue="${complaintMainDetail.complaintMain.employee.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=2" isAll="true" pid="involveDepartment" cssClass="required" dataMsgRequired="请选择人员" allowClear="true" notAllowSelectParent="true"/>
                            <span class="help-inline"> </span>
                        </td>
					</tr>
				</table>
			</div>
		</div>
		<table class="table-form">
			<tr >
				<td class="tit" width="160px"><font color="red">*</font>案件编号：</td>
				<td width="476px">
					<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				</td>
				<td class="tit" width="180px"><font color="red">*</font>来访日期：</td>
				<td >
						<%--<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
					<input name="visitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						   value="${complaintMainDetail.visitorDate}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
				</td>
			</tr>
			<tr >
				<td class="tit"><font color="red">*</font>投诉方式：</td>
				<td >
						<%--<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
					<form:select path="complaintMode" style='width:110px;text-align: center;'>
						<form:option value="0">来电</form:option>
						<form:option value="1">来访</form:option>
						<form:option value="2">来信</form:option>
						<form:option value="3">其他</form:option>
					</form:select>
				</td>
				<td class="tit"><font color="red">*</font>案件分类：</td>
				<td >
					<sys:treeselect id="isMajor" name="isMajor" value="${complaintMainDetail.isMajor}" labelName="typeName" labelValue="${complaintMainDetail.typeName}" title="案件分类"
									url="/test/testTree/treeData" isAll="true" variable="qitaanjian" allowClear="true" notAllowSelectParent="true"/>
					<%--<form:select path="isMajor" style='width:110px;text-align: center;'>--%>
						<%--<form:option value="1">是</form:option>--%>
						<%--<form:option value="0">否</form:option>--%>
						<%--</form:select>--%>
				</td>
			</tr>
			<tr id="qitaanjian" class="hide">
				<td class="tit">其他案件分类：</td>
				<td>
					<form:input path="otherType" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>
			</tr>
			<tr >
				<td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
				<td colspan="3">
					<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>
				</td>
			</tr>
			<tr >
				<td class="tit"><font color="red">*</font>诉求：</td>
				<td colspan="3">
					<form:textarea path="appeal" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 939px; height: 24px;"/>
				</td>
			</tr>
			<tr >
				<td class="tit"><font color="red">*</font>接待人员：</td>
				<td >
						<%--<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
					<sys:treeselect id="receptionEmployee" name="receptionEmployee" value="${complaintMainDetail.receptionEmployee}" labelName="${complaintMainDetail.receptionEmployee}" labelValue="${complaintMainDetail.jdEmployee.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="input-big required" dataMsgRequired="请选择接待人" allowClear="true" notAllowSelectParent="true"/>
                </td>

				<td class="tit"><font color="red">*</font>接待时间：</td>
				<td >
						<%--<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
					<input name="receptionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						   value="${complaintMainDetail.receptionDate}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
				</td>
			</tr>
			<tr >
				<%--<td class="tit"><font color="red">*</font>下一处理环节：</td>
				<td >
					<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
				</td>--%>
				<td class="tit"><font color="red">*</font>下一环节处理人：</td>
				<td >
						<%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${complaintMainDetail.nextLinkMan}" labelName="${complaintMainDetail.linkEmployee.name}" labelValue="${complaintMainDetail.linkEmployee.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="input-big required"  dataMsgRequired="请选择处理人" allowClear="true" notAllowSelectParent="true"/>
				</td>
			</tr>
		</table>

		<div class="form-actions">
			<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<%--<c:if test="${not empty complaintMainDetail.complaintMainDetailId}">
			<act:histoicFlow procInsId="${complaintMainDetail.complaintMain.act.procInsId}" />
		</c:if>--%>
	</form:form>
</body>
</html>