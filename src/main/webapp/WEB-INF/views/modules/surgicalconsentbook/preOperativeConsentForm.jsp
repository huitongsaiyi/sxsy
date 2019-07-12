<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>术前同意书管理</title>
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
	<li><a href="${ctx}/surgicalconsentbook/preOperativeConsent/">术前同意书列表</a></li>
	<li class="active"><a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?id=${preOperativeConsent.id}">术前同意书<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit">${not empty preOperativeConsent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="surgicalconsentbook:preOperativeConsent:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="preOperativeConsent" action="${ctx}/surgicalconsentbook/preOperativeConsent/save" method="post" class="form-horizontal">
	<form:hidden path="id" value="${preOperativeConsent.id}" name="itemId"/>
    <input type="hidden" name="acceId">
	<form:hidden path="surgicalConsentId" value="${preOperativeConsent.surgicalConsentId}"/>
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">手术类型：</label>
		<div class="controls">
			<form:select path="operationType" class="input-medium" style="text-align:center">
				<form:options items="${fns:getDictList('surgeryType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">见证时间：</label>
		<div class="controls">
			<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
				   value="<fmt:formatDate value="${preOperativeConsent.witnessTime}" pattern="yyyy-MM-dd HH:mm"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">见证地点：</label>
		<div class="controls">
			<form:input path="witnessLocations" htmlEscape="false" maxlength="60" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">患方人员：</label>
		<div class="controls">
			<form:input path="affectedParty" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">医方人员：</label>
		<div class="controls">
			<form:input path="medicalSide" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">是否投保：</label>
		<div class="controls">
			<form:select path="insured" class="input-medium" style="text-align:center;width:50px;">
				<form:options items="${fns:getDictList('Insured')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">保单号：</label>
		<div class="controls">
			<form:input path="policyNo" htmlEscape="false" maxlength="60" class="input-xlarge "/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">见证人：</label>
		<div class="controls">
			<form:input path="witness" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">记录人：</label>
		<div class="controls">
			<form:input path="recordMan" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">见证内容：</label>
		<div class="controls">
			<form:input path="witnessContent" htmlEscape="false" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">备注信息：</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		</div>
	</div>
	<div class="control-group">

        <table style="height: 150px;">
            <%--<th class="control-label" style="border: #0bbbee solid 1px; display: block;">附件：</th>--%>
            <th style="display: inline-block; margin-top: 38px; margin-left: 95px;" >医方附件：
            <input type="hidden"  name="fjtype1" value="1">
                    <td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">

				   <input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>
                        <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <sys:ckfinder input="files" type="files"  uploadPath="/surgicalconsentbook/PreOperativeConsent/Doctor" selectMultiple="true" />
                    </td>

            </th>
				<th style="display:block; margin-top:68px;margin-left: -380px; " >患方附件：
					<input type="hidden" name="fjtype2" value="2">
				<td style="width: 450px; margin-left:182px;  display:inline-block; height: 50px; margin-top: -123px;">
					<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge" value="${files2}" />
						<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
					<sys:ckfinder input="files1" type="files"  uploadPath="/surgicalconsentbook/PreOperativeConsent/AffectedParty" selectMultiple="true" />
				</td>

				</th>
        </table>
	</div>
	<div class="form-actions">
		<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>