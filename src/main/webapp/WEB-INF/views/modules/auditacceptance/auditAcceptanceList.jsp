<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核受理管理</title>
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
		<li class="active"><a href="${ctx}/auditacceptance/auditAcceptance/">审核受理列表</a></li>
<%--
		<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><li><a href="${ctx}/auditacceptance/auditAcceptance/form">审核受理添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="auditAcceptance" action="${ctx}/auditacceptance/auditAcceptance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>起保日期：</label>
				<input name="beginGuaranteeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${auditAcceptance.beginGuaranteeTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/> -
				<input name="endGuaranteeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${auditAcceptance.endGuaranteeTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
			</li>
			<li><label>保险公司：</label>
				<form:input path="insuranceCompany" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number" style="text-align: center">案件编号</th>
				<th class="sort-column b.involve_hospital" style="text-align: center">医疗机构名称</th>
				<th class="sort-column b.patient_name" style="text-align: center">患者姓名</th>
				<th class="sort-column a.case_source" style="text-align: center">案件来源</th>
				<th class="sort-column a.guarantee_time" style="text-align: center">起保日期</th>
				<th class="sort-column a.insurance_company" style="text-align: center">保险公司</th>
				<th class="sort-column a.policy_number" style="text-align: center">保单号</th>
				<th class="sort-column a.diagnosis_mode" style="text-align: center">诊疗方式</th>
				<th class="sort-column a.treatment_outcome" style="text-align: center">治疗结果</th>
				<th class="sort-column a.diagnosis_mode" style="text-align: center">患方受理通知书</th>
				<th class="sort-column a.diagnosis_mode" style="text-align: center">医方受理通知书</th>
				<th class="sort-column a.update_date" style="text-align: center">修改时间</th>
				<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="auditAcceptance">
			<tr>
				<td style="text-align: center"><a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.auditAcceptanceId}&type=view">
					${auditAcceptance.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center">
					${auditAcceptance.complaintMain.hospital.name}
				</td>
				<td style="text-align: center">
					${auditAcceptance.complaintMain.patientName}
				</td>
				<td style="text-align: center">
					<c:choose>
						<c:when test="${auditAcceptance.caseSource == '1'}">
							当事人申请
						</c:when>
						<c:when test="${auditAcceptance.caseSource == '2'}">
							人民调解委员会主动申请
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: center">
					${auditAcceptance.guaranteeTime}
				</td>
				<td style="text-align: center">
					${auditAcceptance.insuranceCompany}
				</td>
				<td style="text-align: center">
					${auditAcceptance.policyNumber}
				</td>
				<td style="text-align: center">
					${auditAcceptance.diagnosisMode}
				</td>
				<td style="text-align: center">
					${auditAcceptance.treatmentOutcome}
				</td>
				<td style="text-align: center">
					${auditAcceptance.hfsltzs}
				</td>
				<td style="text-align: center">
					${auditAcceptance.yysltzs}
				</td>
				<td style="text-align: center">
					<fmt:formatDate value="${auditAcceptance.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><td style="text-align: center">
					<c:if test="${fns:getUser().loginName eq auditAcceptance.complaintMain.act.assigneeName}">
						<a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.auditAcceptanceId}">处理</a>
						<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${auditAcceptance.complaintMainId}&taskId=${auditAcceptance.complaintMain.act.taskId}&module=badj&url2=/auditacceptance/auditAcceptance/?repage">终止调解</a>
					</c:if>
					<a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.auditAcceptanceId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>