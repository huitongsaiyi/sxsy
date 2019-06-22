<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估鉴定管理</title>
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
	<li class="active"><a href="${ctx}/assessappraisal/assessAppraisal/">评估鉴定列表</a></li>
	<%--
            <shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><li><a href="${ctx}/assessappraisal/assessAppraisal/form">评估鉴定添加</a></li></shiro:hasPermission>
    --%>
</ul>
<form:form id="searchForm" modelAttribute="assessAppraisal" action="${ctx}/assessappraisal/assessAppraisal/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	<ul class="ul-form">
		<li><label>报案人姓名：</label>
			<form:input path="reportRegistration.reportEmp" htmlEscape="false" maxlength="32" class="input-medium"/>
		</li>
		<li><label>患者姓名：</label>
			<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="32" class="input-medium"/>
		</li>
		<li><label style="width: 100px;">患方联系方式：</label>
			<form:input path="reportRegistration.patientMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
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
		<th class="sort-column case_number" style="text-align:center;">案件编号</th>
		<th class="sort-column b.involve_hospital" style="text-align:center;">涉及医院</th>
		<th class="sort-column hospital_grade" style="text-align:center;">医院等级</th>
		<th class="sort-column sa.name" style="text-align:center;">所属城市</th>
		<th class="sort-column policy_number" style="text-align:center;">保单号</th>
		<th class="sort-column report_emp" style="text-align:center;">报案人姓名</th>
		<th class="sort-column dispute_time" style="text-align:center;">纠纷发生时间</th>
		<th class="sort-column b.patient_name" style="text-align:center;">患者姓名</th>
		<th class="sort-column r.patient_mobile" style="text-align:center;">患者联系电话</th>
		<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="assessAppraisal" varStatus="num">
		<tr>
			<td style="text-align:center;">${assessAppraisal.complaintMain.caseNumber}</td>
			<td style="text-align:center;">${assessAppraisal.complaintMain.hospital.name}</td>
			<td style="text-align:center;">
				<c:choose>
					<c:when test="${assessAppraisal.complaintMain.hospitalGrade=='1'}">
						特等
					</c:when>
					<c:when test="${assessAppraisal.complaintMain.hospitalGrade=='2'}">
						甲等
					</c:when>
					<c:when test="${assessAppraisal.complaintMain.hospitalGrade=='3'}">
						乙等
					</c:when>
					<c:when test="${assessAppraisal.complaintMain.hospitalGrade=='4'}">
						丙等
					</c:when>
					<c:otherwise>
						无
					</c:otherwise>
				</c:choose>
			</td>
			<td style="text-align:center;">${assessAppraisal.area.name}</td>
			<td style="text-align:center;">${assessAppraisal.auditAcceptance.policyNumber}</td>
			<td style="text-align:center;">${assessAppraisal.reportRegistration.reportEmp}</td>
			<td style="text-align:center;">${assessAppraisal.reportRegistration.disputeTime}</td>
			<td style="text-align:center;">${assessAppraisal.complaintMain.patientName}</td>
			<td style="text-align:center;">${assessAppraisal.reportRegistration.patientMobile}</td>

			<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td style="text-align:center;">
				<a href="${ctx}/assessappraisal/assessAppraisal/form?id=${assessAppraisal.assessAppraisalId}">处理</a>
				<a href="${ctx}/assessappraisal/assessAppraisal/form?id=${assessAppraisal.assessAppraisalId}&type=view">详情</a>
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>