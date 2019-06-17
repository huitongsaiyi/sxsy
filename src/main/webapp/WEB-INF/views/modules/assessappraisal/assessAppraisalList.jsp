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
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>案件编号</th>
				<th>涉及医院</th>
				<th>医院等级</th>
				<th>所属城市</th>
				<th>保单号</th>
				<th>报案人姓名</th>
				<th>纠纷发生时间</th>
				<th>患者姓名</th>
				<th>患者联系电话</th>
				<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assessAppraisal" varStatus="num">
			<tr>
				<td>${num.index+1}</td>
				<td>${assessAppraisal.complaintMain.caseNumber}</td>
				<td>${assessAppraisal.complaintMain.hospital.name}</td>
				<td>${assessAppraisal.complaintMain.hospitalGrade}</td>
				<td>${assessAppraisal.area.name}</td>
				<td>${assessAppraisal.auditAcceptance.policyNumber}</td>
				<td>${assessAppraisal.reportRegistration.reportEmp}</td>
				<td>${assessAppraisal.reportRegistration.disputeTime}</td>
				<td>${assessAppraisal.complaintMain.patientName}</td>
				<td>${assessAppraisal.complaintMain.patientMobile}</td>
				<%--<td><a href="${ctx}/assessappraisal/assessAppraisal/form?id=${assessAppraisal.id}">--%>
					<%--<fmt:formatDate value="${assessAppraisal.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</a></td>--%>
				<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td>
    				<a href="${ctx}/assessappraisal/assessAppraisal/form?id=${assessAppraisal.assessAppraisalId}">修改</a>
					<a href="${ctx}/assessappraisal/assessAppraisal/delete?id=${assessAppraisal.assessAppraisalId}" onclick="return confirmx('确认要删除该评估鉴定吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>