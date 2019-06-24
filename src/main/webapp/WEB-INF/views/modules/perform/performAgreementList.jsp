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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number">案件编号</th>
				<th class="sort-column involve_hospital">涉及医院</th>
				<th class="sort-column hospital_grade">医院等级</th>
				<th class="sort-column sa.name">所属城市</th>
				<th class="sort-column au.policy_number">保单号</th>
				<th class="sort-column r.report_emp">报案人姓名</th>
				<th class="sort-column r.dispute_time">纠纷发生时间</th>
				<th class="sort-column b.patient_name">患者姓名</th>
				<th class="sort-column r.patient_mobile">患者联系电话</th>
				<shiro:hasPermission name="perform:performAgreement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="performAgreement">
			<tr>
				<td><a href="${ctx}/perform/performAgreement/form?id=${performAgreement.performAgreementId}">
						${performAgreement.complaintMain.caseNumber}
				</a></td>
				<td>
						${performAgreement.complaintMain.hospital.name}
				</td>
				<td>
						${performAgreement.complaintMain.hospitalGrade}
				</td>
				<td>
						${performAgreement.area.name}
				</td>
				<td>
						${performAgreement.auditAcceptance.policyNumber}
				</td>
				<td>
						${performAgreement.reportRegistration.reportEmp}
				</td>
				<td>
						${performAgreement.reportRegistration.disputeTime}
				</td>
				<td>
						${performAgreement.complaintMain.patientName}
				</td>
				<td>
						${performAgreement.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="perform:performAgreement:edit"><td>
    				<a href="${ctx}/perform/performAgreement/form?id=${performAgreement.performAgreementId}">处理</a>
					<a href="${ctx}/perform/performAgreement/form?id=${performAgreement.performAgreementId}&type=view">详情</a>
					<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${performAgreement.complaintMainId}&module=badj&url10=/perform/performAgreement/?repage">终止调解</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>