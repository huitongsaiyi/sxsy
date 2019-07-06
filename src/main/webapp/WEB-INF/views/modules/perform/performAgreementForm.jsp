<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>履行协议管理</title>
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
		<li><a href="${ctx}/perform/performAgreement/">履行协议列表</a></li>
		<li class="active"><a href="${ctx}/perform/performAgreement/form?id=${performAgreement.id}">履行协议<shiro:hasPermission name="perform:performAgreement:edit">${not empty performAgreement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="perform:performAgreement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="performAgreement" action="${ctx}/perform/performAgreement/save" method="post" class="form-horizontal">
		<form:hidden path="performAgreementId"/>
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
				<a href="#patient" data-toggle="tab">履行协议情况</a>
			</li>
			<li>
				<a href="#annex" data-toggle="tab">附件</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="patient">
				<table class="table-form">
					<tr>
						<td class="tit">协议赔付金额</td>
						<td>
							<form:input path="agreementPayAmount" htmlEscape="false" class="input-xlarge required" maxlength="10"/>
						</td>
						<td class="tit">医院赔付金额</td>
						<td>
							<form:input path="hospitalPayAmount" htmlEscape="false" class="input-xlarge required" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td class="tit">医院赔付时间</td>
						<td>
							<input name="hospitalPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${performAgreement.hospitalPayTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
						</td>
						<td class="tit">保险公司赔付金额</td>
						<td>
							<form:input path="insurancePayAmount" htmlEscape="false" class="input-xlarge required" maxlength="10"/>
						</td>
					</tr>
					<tr>
						<td class="tit">保险公司赔付时间</td>
						<td>
							<input name="insurancePayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${performAgreement.insurancePayTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="annex">
				<table class="table-form">
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">转款凭证：</td>
						<input type="hidden"  name="fjtype1" value="14">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
							<input type="hidden" id="files1" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>
								<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
							<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/performAgreement/Transfer" selectMultiple="true" /></div>
						</td>

					</tr>
					<tr style="" >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">收款收据：</td>
						<input type="hidden" name="fjtype2" value="15">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
							<input type="hidden" id="files2" name="files1" htmlEscape="false" class="input-xlarge" value="${files1}" />
								<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
							<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/performAgreement/Receipt" selectMultiple="true" /></div>
						</td>

					</tr>
				</table>
			</div>
		</div>
		<table class="table-form">
			<tr>
				<%--<td class="tit">下一环节</td>--%>
				<%--<td>--%>
					<%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<%--</td>--%>
				<td class="tit">下一环节处理人</td>
				<td >
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty performAgreement.nextLinkMan?fns:getUser().id:performAgreement.nextLinkMan}" labelName="linkEmployee.name" labelValue="${empty performAgreement.linkEmployee.name?fns:getUser().name:performAgreement.linkEmployee.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息"/>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="perform:performAgreement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="perform:performAgreement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<act:histoicFlow procInsId="${performAgreement.complaintMain.procInsId}" />
	</form:form>
</body>
</html>