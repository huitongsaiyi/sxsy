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

			var value='${preOperativeConsent.insured}'
			if(value==''){
				value = 1;
			}
			next(value);
		});
		function next(value) {
			if(value==2){
				$("<td id='bao1' class='tit'>保单号</td>").insertBefore("#bao");
				$("#bao2").show();
			}else{
				$("#bao1").remove();
				$("#bao2").hide();
			}
		}
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
<ul id="myTab" class="nav nav-tabs">
	<li class="active">
		<a href="#consent" data-toggle="tab">术前同意书</a>
	</li>
	<li>
		<a href="#attachment" data-toggle="tab">附件</a>
	</li>
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="consent">
		<table class="table-form">
			<tr>
				<td class="tit" width="200px">见证时间</td>
				<td colspan="3">
					<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						   value="<fmt:formatDate value="${preOperativeConsent.witnessTime}" pattern="yyyy-MM-dd HH:mm"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
				</td>
			</tr>
			<tr>
				<td class="tit">见证地点</td>
				<td colspan="3">
					<form:input path="witnessLocations" htmlEscape="false" maxlength="60" class="input-xlarge required"/>
				</td>
			</tr>
			<tr>
				<td class="tit">手术类型</td>
				<td colspan="3">
					<form:select path="operationType" class="input-medium" style="text-align:center">
						<form:options items="${fns:getDictList('surgeryType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="tit">医方人员</td>
				<td colspan="3">
					<form:input path="medicalSide" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				</td>
			</tr>
			<tr>
				<td class="tit">患方人员</td>
				<td colspan="3">
					<form:input path="affectedParty" htmlEscape="false" maxlength="50" class="input-xlarge required" onchange="next(this.value)"/>
				</td>
			</tr>
			<tr>
				<td class="tit">是否投保</td>
				<td id="tou">
					<form:select path="insured" style='width:110px;text-align: center;' onchange="next(this.value)">
						<form:option value="1">否</form:option>
						<form:option value="2">是</form:option>
					</form:select>
				</td>

			</tr>
			<tr id="bao2">
				<td id="bao">
					<form:input path="policyNo" htmlEscape="false" maxlength="60" class="input-xlarge " id="bao2"/>
				</td>
			</tr>
			<tr>
				<td class="tit">见证人</td>
				<td colspan="3">
					<form:input path="witness" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				</td>
			</tr>
			<tr>
				<td class="tit">见证内容</td>
				<td colspan="3">
					<form:input path="witnessContent" htmlEscape="false" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit">记录人</td>
				<td colspan="3">
					<form:input path="recordMan" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				</td>
			</tr>
			<tr>
				<td class="tit">备注信息</td>
				<td colspan="3">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				</td>
			</tr>
		</table>
	</div>
	<div class="tab-pane fade" id="attachment">
		<table class="table-form">
			<tr>
				<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方附件</td>
					<input type="hidden"  name="fjtype1" value="1">
				<td style="width: 450px; ">
					<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
					<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
					<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/surgicalconsentbook/PreOperativeConsent/Doctor" selectMultiple="true" /></div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方附件</td>
				<input type="hidden" name="fjtype2" value="2">
				<td style="width: 450px; ">
					<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge" value="${files2}" />
					<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
					<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/surgicalconsentbook/PreOperativeConsent/AffectedParty" selectMultiple="true" /></div>
				</td>
			</tr>
		</table>
	</div>
</div>
	<div class="form-actions">
		<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>