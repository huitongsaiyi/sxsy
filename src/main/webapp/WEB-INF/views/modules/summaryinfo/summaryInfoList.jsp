<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件总结管理</title>
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
		<li class="active"><a href="${ctx}/summaryinfo/summaryInfo/">案件总结列表</a></li>
<%--
		<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><li><a href="${ctx}/summaryinfo/summaryInfo/form">案件总结添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="summaryInfo" action="${ctx}/summaryinfo/summaryInfo/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="summaryInfo" varStatus="num">
			<tr>
				<td>${summaryInfo.complaintMain.caseNumber}</td>
				<td>${summaryInfo.complaintMain.hospital.name}</td>
				<td>${summaryInfo.complaintMain.hospitalGrade}</td>
				<td>${summaryInfo.area.name}</td>
				<td>${summaryInfo.auditAcceptance.policyNumber}</td>
				<td>${summaryInfo.reportRegistration.reportEmp}</td>
				<td>${summaryInfo.reportRegistration.disputeTime}</td>
				<td>${summaryInfo.complaintMain.patientName}</td>
				<td>${summaryInfo.reportRegistration.patientMobile}</td>
				<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><td>
    				<a href="${ctx}/summaryinfo/summaryInfo/form?id=${summaryInfo.summaryId}">处理</a>
					<a href="${ctx}/summaryinfo/summaryInfo/form?id=${summaryInfo.summaryId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>