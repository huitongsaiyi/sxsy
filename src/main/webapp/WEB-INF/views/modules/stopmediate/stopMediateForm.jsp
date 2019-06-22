<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终止调解管理</title>
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
		<li><a href="${ctx}/stopmediate/stopMediate/">终止调解列表</a></li>
		<li class="active"><a href="${ctx}/stopmediate/stopMediate/form?id=${stopMediate.id}">终止调解<shiro:hasPermission name="stopmediate:stopMediate:edit">${not empty stopMediate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="stopmediate:stopMediate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="stopMediate" action="${ctx}/stopmediate/stopMediate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active">
			<table class="table-form">
				<tr>
					<td class="tit">患者姓名</td>
					<td>
						<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
					</td>
					<td class="tit">涉及医院</td>
					<td>
						<sys:treeselect id="feedbackOffice" name="feedbackOffice" value="${stopMediate.involveHospital}" labelName="" labelValue="${stopMediate.complaintMain.hospital.name}"
										title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息"/>
					</td>
				</tr>
				<tr>
					<td>终止日期</td>
					<td>
						<input name="stopTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="${stopMediate.stopTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
					</td>
				</tr>
				<tr>
					<td>终止调解描述</td>
					<td colspan="3">
						<form:textarea path="stopDescribe" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
		<table class="table-form">
			<tr>
				<td class="tit">下一环节处理人</td>
				<td>
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${stopMediate.nextLinkMan}" labelName="" labelValue="${stopMediate.nextLinkMan}"
									title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息"/>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="stopmediate:stopMediate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="stopmediate:stopMediate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="stopmediate:stopMediate:edit"><input type = "button" class="btn btn-primary" value = "导出"/></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>