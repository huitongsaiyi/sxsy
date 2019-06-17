<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件总结管理</title>
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
		<li><a href="${ctx}/summaryinfo/summaryInfo/">案件总结列表</a></li>
		<%--<li class="active"><a href="${ctx}/summaryinfo/summaryInfo/form?id=${summaryInfo.id}">案件总结<shiro:hasPermission name="summaryinfo:summaryInfo:edit">${not empty summaryInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="summaryinfo:summaryInfo:edit">查看</shiro:lacksPermission></a></li>--%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="summaryInfo" action="${ctx}/summaryinfo/summaryInfo/save" method="post" class="form-horizontal">
		<form:hidden path="summaryId"/>
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
				<a href="#Summary" data-toggle="tab">案件总结</a>
			</li>
			<li>
				<a href="#attachment" data-toggle="tab">补录附件</a>
			</li>
			<li>
				<a href="#details" data-toggle="tab">详情</a>
			</li>

		</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="Summary">
			<table class="table-form">
				<tr>
					<td style="text-align: center;" >卷宗编号:</td>
					<td >
						<form:input path="fileNumber" htmlEscape="false" maxlength="10" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">归档时间:</td>
					<td>
						<input name="filingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="${summaryInfo.filingTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:270px;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">总结人:</td>
					<td>
						<form:input path="summaryEmp" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" >总结时间:</td>
					<td>
						<input name="summaryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="${summaryInfo.summaryTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:270px;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="1">总结:</td>
					<td>
						<form:textarea path="summary" htmlEscape="false" rows="5" maxlength="500" class="input-xxlarge " cssStyle="width: 1500px;"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="tab-pane fade" id="attachment">
			<ul id="myTab1" class="nav nav-tabs">
				<li class="active">
					<a href="#huanfang" data-toggle="tab">患方相关附件</a>
				</li>
				<li>
					<a href="#yifang" data-toggle="tab">医方相关附件</a>
				</li>
			</ul>
			<div id="myTabContent1" class="tab-content">
			<div class="tab-pane fade in active" id="huanfang">
				<table class="table-form">
					<tr style=" " >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方调解申请：</td>
					<input type="hidden"  name="fjtype1" value="25">
					<td style="width: 450px; ">

						<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
						<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/mediation" selectMultiple="true" /></div>
					</td>
				</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗纠纷材料：</td>
						<input type="hidden"  name="fjtype2" value="26">
						<td style="width: 450px; ">

							<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"  value="${files2}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/medicalDisputes" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">当事人身份相关证件：</td>
						<input type="hidden"  name="fjtype3" value="27">
						<td style="width: 450px; ">

							<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"  value="${files3}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/partyDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">授权委托书：</td>
						<input type="hidden"  name="fjtype4" value="28">
						<td style="width: 450px; ">

							<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"  value="${files4}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/powerOfAttorney" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">委托人、被委托人相关证件：</td>
						<input type="hidden"  name="fjtype5" value="29">
						<td style="width: 450px; ">

							<input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge"  value="${files5}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/PrincipalRelatedDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">病例及相关检查资料：</td>
						<input type="hidden"  name="fjtype6" value="30">
						<td style="width: 450px; ">

							<input type="hidden" id="files6" name="files6" htmlEscape="false" class="input-xlarge"  value="${files6}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/caseInformation" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗费用及其他相关费用票据：</td>
						<input type="hidden"  name="fjtype7" value="31">
						<td style="width: 450px; ">

							<input type="hidden" id="files7" name="files7" htmlEscape="false" class="input-xlarge"  value="${files7}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/feeNotes" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">误工证明：</td>
						<input type="hidden"  name="fjtype8" value="32">
						<td style="width: 450px; ">

							<input type="hidden" id="files8" name="files8" htmlEscape="false" class="input-xlarge"  value="${files8}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files8" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/proofOfMissingWork" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患者死亡相关证明：</td>
						<input type="hidden"  name="fjtype9" value="33">
						<td style="width: 450px; ">

							<input type="hidden" id="files9" name="files9" htmlEscape="false" class="input-xlarge"  value="${files9}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files9" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/patientDeathCertificate" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">新生儿出生证明：</td>
						<input type="hidden"  name="fjtype10" value="34">
						<td style="width: 450px; ">

							<input type="hidden" id="files10" name="files10" htmlEscape="false" class="input-xlarge"  value="${files10}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files10" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/neonatal" selectMultiple="true" /></div>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade " id="yifang">
				<table class="table-form">
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方调解申请：</td>
						<input type="hidden"  name="fjtype11" value="35">
						<td style="width: 450px; ">

							<input type="hidden" id="files11" name="files11" htmlEscape="false" class="input-xlarge"  value="${files11}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files11" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiMediation" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">法定代表人身份证明：</td>
						<input type="hidden"  name="fjtype12" value="36">
						<td style="width: 450px; ">

							<input type="hidden" id="files12" name="files12" htmlEscape="false" class="input-xlarge"  value="${files12}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files12" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiLegalRepresentative" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">授权委托书：</td>
						<input type="hidden"  name="fjtype13" value="37">
						<td style="width: 450px; ">

							<input type="hidden" id="files13" name="files13" htmlEscape="false" class="input-xlarge"  value="${files13}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files13" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiPowerOfAttorney" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">委托人、被委托人相关证件：</td>
						<input type="hidden"  name="fjtype14" value="38">
						<td style="width: 450px; ">

							<input type="hidden" id="files14" name="files14" htmlEscape="false" class="input-xlarge"  value="${files14}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files14" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiPrincipalDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">执业许可证副本：</td>
						<input type="hidden"  name="fjtype15" value="39">
						<td style="width: 450px; ">

							<input type="hidden" id="files15" name="files15" htmlEscape="false" class="input-xlarge"  value="${files15}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files15" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiLicenseToPractise" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">诊疗分析意见：</td>
						<input type="hidden"  name="fjtype16" value="40">
						<td style="width: 450px; ">

							<input type="hidden" id="files16" name="files16" htmlEscape="false" class="input-xlarge"  value="${files16}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files16" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiZhenLiaoAnalysis" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">出险医务人员相关证件
							（身份证、执业证书）：</td>
						<input type="hidden"  name="fjtype17" value="41">
						<td style="width: 450px; ">

							<input type="hidden" id="files17" name="files17" htmlEscape="false" class="input-xlarge"  value="${files17}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files17" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiChuXianRenDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">相关证据材料：</td>
						<input type="hidden"  name="fjtype18" value="42">
						<td style="width: 450px; ">

							<input type="hidden" id="files18" name="files18" htmlEscape="false" class="input-xlarge"  value="${files18}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files18" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiRelevantEvidence" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">病历资料：</td>
						<input type="hidden"  name="fjtype19" value="43">
						<td style="width: 450px; ">

							<input type="hidden" id="files19" name="files19" htmlEscape="false" class="input-xlarge"  value="${files19}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files19" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiMedicalRecords" selectMultiple="true" /></div>
						</td>
					</tr>
				</table>
			</div>
			</div>
		</div>
		<div class="tab-pane fade in active" id="details">
		</div>
	</div>

		<table class="table-form">
			<%--<tr>--%>
				<%--<td class="tit">--%>
					<%--处理人：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<%--</td>--%>
				<%--<td class="tit">--%>
					<%--处理日期：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
						<%--value="${assessAppraisal.handleTime}"--%>
						<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>--%>
				<%--</td>--%>
			<%--</tr>--%>
			<tr>
				<td class="tit">
					下一处理环节：
				</td>
				<td>
					<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
				</td>
				<td class="tit">
					下一环节处理人：
				</td>
				<td>
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${summaryInfo.nextLinkMan}" labelName="" labelValue="${summaryInfo.linkEmployee.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true" checked="true"/>
				</td>
	</tr>
		</table>


		<div class="form-actions">
			<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>