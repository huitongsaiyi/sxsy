<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成功管理</title>
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
		<li><a href="${ctx}/nestigateeividence/investigateEvidence/">列表</a></li>
		<li class="active"><a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.id}" id="c">成功<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit">${not empty investigateEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="nestigateeividence:investigateEvidence:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="investigateEvidence" action="${ctx}/nestigateeividence/investigateEvidence/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#visitor" data-toggle="tab">患方调查笔录</a>
			</li>
			<li>
				<a href="#patient" data-toggle="tab">医方调查笔录</a>
			</li>
			<li>
				<a href="#hospital" data-toggle="tab">附件</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="visitor">
				<table class="table-form">
					<tr >
						<td class="tit" width="140px" style="border: hidden; border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查时间：</td>
						<td style="width: 105px;">
							<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="<fmt:formatDate value="${investigateEvidence.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 250px;border: hidden;height: 25px;"/>
							<span class="help-inline" style="width: 10px;"><font color="red" style="width: 10px;">*</font> </span>
						</td>
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>结束时间：</td>

						<td width="195px;">
							<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="<fmt:formatDate value="${investigateEvidence.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 250px; border: hidden;height: 25px;"/>
							<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查地点：</td>
						<td style="width: 105px;">
							<form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge " cssStyle="width:480px;border: hidden;"/>
						</td>
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>调查事由：</td>

						<td width="195px;">
							<form:input path="cause" htmlEscape="false" maxlength="500" class="input-xlarge " cssStyle="width:480px;border:hidden;" />
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查人：</td>
						<td style="width: 105px;">
							<form:input path="investigator" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="border: hidden;"/>
						</td>
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>记录人：</td>

						<td width="195px;">
							<form:input path="noteTaker" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="border: hidden;"/>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>反应焦点：</td>
						<td style="width: 105px;">
							<form:input path="focus" htmlEscape="false" maxlength="500" class="input-xlarge " cssStyle="border: hidden; width: 350px;"/>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid; " ><font color="red">*</font>笔录内容：</td>
						<td style="width: 105px;">
							<form:textarea path="content" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
						</td>
					</tr>
				</table>
				<ul  class="nav nav-tabs">
					<li class="active">
						<a href="#investigation1" data-toggle="tab">被调查人1</a>
					</li>
					<li>
						<a href="#investigation2" data-toggle="tab">被调查人2</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="investigation1">
						<table class="table-form">
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>被调查人身份:</td>
								<td style="display: inline-block; width: 900px; border: hidden;" >
									<form:radiobuttons path="respondentInfo.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
								</td>
							</tr>
							<tr >
								<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>姓名：</td>
								<td style="display: inline-block; width: 115px;border: hidden;">
									<form:input path="respondentInfo.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle="width:100px;"/>
								</td>
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 10px;width: 80px;"><font color="red">*</font>性别：</td>
								<td style=" display: inline-block;margin-left: -1px; width: 60px; border: hidden;">
									<form:select path="respondentInfo.respondentSex" class="input-medium" style="text-align:center;width: 60px;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid; display: inline-block;margin-left:1px;width: 80px;"><font color="red">*</font>年龄：</td>
								<td style="display: inline-block;margin-left: -1px; width: 65px;border: hidden;">
									<form:input path="respondentInfo.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;"/>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>联系方式：</td>
								<td style="display: inline-block;margin-left: -1px; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
							</tr>
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>工作单位:</td>
								<td style="display: inline-block; width: 445px; border: hidden;">
									<form:input path="respondentInfo.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 435px;"/>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>职务:</td>
								<td style="display: inline-block; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="margin-left: 5px;"/>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane fade in active" id="investigation2">
						<table class="table-form">
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>被调查人身份:</td>
								<td style="display: inline-block; width: 900px; border: hidden;" >
									<form:radiobuttons path="respondentInfo.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
								</td>
							</tr>
							<tr >
								<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>姓名：</td>
								<td style="display: inline-block; width: 115px;border: hidden;">
									<form:input path="respondentInfo.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle="width:100px;"/>
								</td>
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 10px;width: 80px;"><font color="red">*</font>性别：</td>
								<td style=" display: inline-block;margin-left: -1px; width: 60px; border: hidden;">
									<form:select path="respondentInfo.respondentSex" class="input-medium" style="text-align:center;width: 60px;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid; display: inline-block;margin-left:1px;width: 80px;"><font color="red">*</font>年龄：</td>
								<td style="display: inline-block;margin-left: -1px; width: 65px;border: hidden;">
									<form:input path="respondentInfo.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;"/>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>联系方式：</td>
								<td style="display: inline-block;margin-left: -1px; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
							</tr>
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>工作单位:</td>
								<td style="display: inline-block; width: 445px; border: hidden;">
									<form:input path="respondentInfo.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 435px;"/>
								</td>

								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>职务:</td>
								<td style="display: inline-block; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="margin-left: 5px;"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="patient">
				<table class="table-form">
					<tr >
						<td class="tit" width="140px" style="border: hidden; border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查时间：</td>
						<td style="width: 105px;">
							<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="<fmt:formatDate value="${investigateEvidence.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 250px;border: hidden;height: 25px;"/>
							<span class="help-inline" style="width: 10px;"><font color="red" style="width: 10px;">*</font> </span>
						</td>
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>结束时间：</td>

						<td width="195px;">
							<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="<fmt:formatDate value="${investigateEvidence.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 250px; border: hidden;height: 25px;"/>
							<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查地点：</td>
						<td style="width: 105px;">
							<form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge " cssStyle="width:480px;border: hidden;"/>
							<input type="hidden" value="${investigateEvidence.address}"/>
						</td>
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>调查事由：</td>

						<td width="195px;">
							<form:input path="cause" htmlEscape="false" maxlength="500" class="input-xlarge " cssStyle="width:480px;border:hidden;" />
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查人：</td>
						<td style="width: 105px;">
							<form:input path="investigator" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="border: hidden;"/>
						</td>
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>记录人：</td>

						<td width="195px;">
							<form:input path="noteTaker" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="border: hidden;"/>
						</td>
					</tr>
					<%--<tr >--%>
						<%--<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>反应焦点：</td>--%>
						<%--<td style="width: 105px;">--%>
							<%--<form:input path="focus" htmlEscape="false" maxlength="500" class="input-xlarge " cssStyle="border: hidden; width: 350px;"/>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr >
						<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid; " ><font color="red">*</font>笔录内容：</td>
						<td style="width: 105px;">
							<form:textarea path="content" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
						</td>
					</tr>
				</table>
				<ul  class="nav nav-tabs">
					<li class="active">
						<a href="#investigation3" data-toggle="tab">被调查人1</a>
					</li>
					<li>
						<a href="#investigation4" data-toggle="tab">被调查人2</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="investigation3">
						<table>
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>被调查人身份:</td>
								<td style="display: inline-block; width: 900px; border: hidden;" >
									<form:radiobuttons path="respondentInfo.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
								</td>
							</tr>
							<tr >
								<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>姓名：</td>
								<td style="display: inline-block; width: 115px;border: hidden;">
									<form:input path="respondentInfo.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle="width:100px;"/>
								</td>
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 10px;width: 80px;"><font color="red">*</font>性别：</td>
								<td style=" display: inline-block;margin-left: -1px; width: 60px; border: hidden;">
									<form:select path="respondentInfo.respondentSex" class="input-medium" style="text-align:center;width: 60px;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid; display: inline-block;margin-left:1px;width: 80px;"><font color="red">*</font>年龄：</td>
								<td style="display: inline-block;margin-left: -1px; width: 65px;border: hidden;">
									<form:input path="respondentInfo.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;"/>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>联系方式：</td>
								<td style="display: inline-block;margin-left: -1px; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
							</tr>
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>工作单位:</td>
								<td style="display: inline-block; width: 445px; border: hidden;">
									<form:input path="respondentInfo.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 435px;"/>
								</td>

								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>职务:</td>
								<td style="display: inline-block; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="margin-left: 5px;"/>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane fade in active" id="investigation4">
						<table>
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>被调查人身份:</td>
								<td style="display: inline-block; width: 900px; border: hidden;" >
									<form:radiobuttons path="respondentInfo.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
								</td>
							</tr>
							<tr >
								<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>姓名：</td>
								<td style="display: inline-block; width: 115px;border: hidden;">
									<form:input path="respondentInfo.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle="width:100px;"/>
								</td>
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 10px;width: 80px;"><font color="red">*</font>性别：</td>
								<td style=" display: inline-block;margin-left: -1px; width: 60px; border: hidden;">
									<form:select path="respondentInfo.respondentSex" class="input-medium" style="text-align:center;width: 60px;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid; display: inline-block;margin-left:1px;width: 80px;"><font color="red">*</font>年龄：</td>
								<td style="display: inline-block;margin-left: -1px; width: 65px;border: hidden;">
									<form:input path="respondentInfo.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;"/>
								</td>
								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>联系方式：</td>
								<td style="display: inline-block;margin-left: -1px; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
							</tr>
							<tr >
								<td class="tit" style="border: hidden;border-right:1px #e2e2e2 solid;width: 30px;"><font color="red">*</font>工作单位:</td>
								<td style="display: inline-block; width: 445px; border: hidden;">
									<form:input path="respondentInfo.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 435px;"/>
								</td>

								<td class="tit"  style="border: hidden;border-right:1px #e2e2e2 solid;display: inline-block; margin-left: 1px;width: 120px; "><font color="red">*</font>职务:</td>
								<td style="display: inline-block; width: 302px; border: hidden;">
									<form:input path="respondentInfo.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="margin-left: 5px;"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">
					<tr>附件</tr>
				</table>


			</div>
		</div>


		<%--<div class="control-group">--%>
			<%--<label class="control-label">被调查类型 1 患方  2 医方：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="investigateType" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>

		<table class="table-form" style="margin-top: 20px;">
			<tr style=" border: hidden;">
				<td class="tit" width="100px" style="border: hidden;display: inline-block; border-right:1px #e2e2e2 solid; width: 140px;padding-top: 8px;height: 25px;"><font color="red">*</font>处理人：</td>
				<td style="width: 105px;border: hidden;display: inline-block;margin-left: 1px;">
					<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 90px;"/>
				</td>
				<td class="tit" width="100px" style="border: hidden;display: inline-block; border-right:1px #e2e2e2 solid; width: 130px;padding-top: 8px;height: 25px;margin-left: 330px;"><font color="red">*</font>处理日期：</td>

				<td width="195px;" style="width: 195px;border:hidden;display: inline-block;margin-left: 745px;margin-top: -60px;">
					<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						   value="<fmt:formatDate value="${investigateEvidence.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 170px; border: hidden;height: 25px;"/>
				</td>
			</tr>
			<tr  style="border: hidden;">
				<td class="tit" width="100px" style="border: hidden;display: inline-block; border-right:1px #e2e2e2 solid; width: 140px;padding-top: 8px;height: 25px;margin-top: -15px;"><font color="red">*</font>下一处理环节：</td>
				<td style="width: 105px;border: hidden;display: inline-block; margin-left:1px;margin-top: -15px; ">
					<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 90px;"/>
				</td>
				<td class="tit" width="100px" style="border: hidden;display: inline-block; border-right:1px #e2e2e2 solid; width: 130px;padding-top: 8px;height: 25px;margin-top: -15px;margin-left: 330px;"><font color="red">*</font>下一环节处理人：</td>

				<td width="195px;" style="border:hidden;display:inline-block;margin-top: -15px;margin-left: 5px;">
					<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 90px;"/>
				</td>

			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>