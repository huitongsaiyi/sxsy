<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估鉴定审批管理</title>
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
		<li class="active"><a href="${ctx}/assessaudit/assessAudit/">评估鉴定审批列表</a></li>
<%--
		<shiro:hasPermission name="assessaudit:assessAudit:edit"><li><a href="${ctx}/assessaudit/assessAudit/form">评估鉴定审批添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="assessAudit" action="${ctx}/assessaudit/assessAudit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>案件编号</th>
				<th>涉及医院</th>
				<th>医院等级</th>
				<th>所属城市</th>
				<th>保单号</th>
				<th>报案人姓名</th>
				<th>纠纷发生时间</th>
				<th>患者姓名</th>
				<th>患者联系电话</th>
				<shiro:hasPermission name="assessaudit:assessAudit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assessAudit">
			<tr>
				<td><a href="${ctx}/assessaudit/assessAudit/form?id=${assessAudit.assessAuditId}">
						${assessAudit.complaintMain.caseNumber}
				</a></td>
				<td>
						${assessAudit.complaintMain.hospital.name}
				</td>
				<td>
						${assessAudit.complaintMain.hospitalGrade}
				</td>
				<td>
						${assessAudit.area.name}
				</td>
				<td>
						${assessAudit.auditAcceptance.policyNumber}
				</td>
				<td>
						${assessAudit.reportRegistration.reportEmp}
				</td>
				<td>
						${assessAudit.reportRegistration.disputeTime}
				</td>
				<td>
						${assessAudit.complaintMain.patientName}
				</td>
				<td>
						${assessAudit.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="assessaudit:assessAudit:edit"><td>
    				<a href="${ctx}/assessaudit/assessAudit/form?id=${assessAudit.assessAuditId}">处理</a>
					<a href="${ctx}/assessaudit/assessAudit/form?id=${assessAudit.assessAuditId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>