<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>履行协议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/perform/performAgreement/">履行协议列表</a></li>
<%--
		<shiro:hasPermission name="perform:performAgreement:edit"><li><a href="${ctx}/perform/performAgreement/form">履行协议添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="performAgreement" action="${ctx}/perform/performAgreement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>案件编号：</label>
				<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>报案时间：</label>
				<input name="reportRegistration.reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value=""
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label style="width: 100px;">涉及医院：</label>
				<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${performAgreement.complaintMain.involveHospital}" labelName="hospitalName" labelValue="${performAgreement.complaintMain.hospital.name}"
								title="部门" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">受理时间</th>
				<th class="sort-column b.patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column involve_hospital" style="text-align: center;">涉及医院</th>
				<th class="sort-column b.hospital_grade" style="text-align: center;">保险公司</th>
				<th class="sort-column au.policy_number" style="text-align: center;">诊疗结果</th>
				<th class="sort-column r.report_emp" style="text-align: center;">协议编号</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">协议签署时间</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">协议生效时间</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">协议金额</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">送到患方时间</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">送到医方时间</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">交理赔时间</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">保险赔付金额</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">保险赔付时间</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">医院赔付金额</th>
				<th class="sort-column r.dispute_time" style="text-align: center;">医院赔付时间</th>
				<th class="sort-column r.patient_mobile" style="text-align: center;">是否重大</th>
				<th class="sort-column r.patient_mobile" style="text-align: center;">是否媒体介入</th>
				<th class="sort-column r.patient_mobile" style="text-align: center;">部门名称</th>
				<th class="sort-column r.patient_mobile" style="text-align: center;">调解员</th>
				<shiro:hasPermission name="perform:performAgreement:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="performAgreement">
			<tr ${not empty performAgreement.complaintMain.isMajor ? "style='background-color: red;'" : ""}>
				<td style="text-align: center;"><a href="${ctx}/perform/performAgreement/form?id=${performAgreement.performAgreementId}&type=view">
						${performAgreement.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center;">
					<fmt:formatDate value="${performAgreement.auditAcceptance.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align: center;">
						${performAgreement.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${performAgreement.complaintMain.hospital.name}
				</td>
				<td style="text-align: center;">
						${performAgreement.auditAcceptance.insuranceCompany}
				</td>
				<td style="text-align: center;">
						${performAgreement.auditAcceptance.treatmentOutcome}
				</td>

				<td style="text-align: center;">
						${performAgreement.signAgreement.agreementNumber}
				</td>
				<td style="text-align: center;">
						${performAgreement.signAgreement.ratifyAccord}
				</td>
				<td style="text-align: center;">
						${performAgreement.takeEffectTime}
				</td>
				<td style="text-align: center;">
						${empty performAgreement.agreementPayAmount ? performAgreement.signAgreement.agreementAmount : performAgreement.agreementPayAmount}
				</td>
				<td style="text-align: center;">
						${performAgreement.patientServiceTime}
				</td>
				<td style="text-align: center;">
						${performAgreement.hospitalServiceTime}
				</td>
				<td style="text-align: center;">
						${performAgreement.claimSettlementTime}
				</td>
				<td style="text-align: center;">
						${performAgreement.insurancePayAmount}
				</td>
				<td style="text-align: center;">
						${performAgreement.insurancePayTime}
				</td>
				<td style="text-align: center;">
						${performAgreement.hospitalPayAmount}
				</td>
				<td style="text-align: center;">
						${performAgreement.hospitalPayTime}
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${not empty performAgreement.complaintMain.isMajor}">
							<a href="${ctx}/major/majorInfo/form?id=${performAgreement.complaintMain.isMajor}&complaintMainId=${performAgreement.complaintMainId}">是</a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/major/majorInfo/form?id=${performAgreement.complaintMain.isMajor}&complaintMainId=${performAgreement.complaintMainId}">否</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;">

				</td>
				<td style="text-align: center;">
						${fns:getUserById(performAgreement.createUser).office.name}
				</td>
				<td style="text-align: center;">
						${fns:getUserById(performAgreement.createUser).name}
				</td>
				<shiro:hasPermission name="perform:performAgreement:edit"><td style="text-align: center;">
					<c:if test="${fns:getUser().loginName eq performAgreement.complaintMain.act.assigneeName}">
						<a href="${ctx}/perform/performAgreement/form?id=${performAgreement.performAgreementId}">处理</a>
						<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${performAgreement.complaintMainId}&taskId=${performAgreement.complaintMain.act.taskId}&module=badj&url10=/perform/performAgreement/?repage">终止调解</a>
					</c:if>
					<a href="${ctx}/perform/performAgreement/form?id=${performAgreement.performAgreementId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>