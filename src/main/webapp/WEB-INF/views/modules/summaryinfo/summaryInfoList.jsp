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
		<%--<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><li><a href="${ctx}/summaryinfo/summaryInfo/form">案件总结添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="summaryInfo" action="${ctx}/summaryinfo/summaryInfo/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="summaryInfo" varStatus="num">
			<tr>
				<td>${num.index+1}</td>
				<td>${summaryInfo.complaintMain.caseNumber}</td>
				<td>${summaryInfo.complaintMain.hospital.name}</td>
				<td>${summaryInfo.complaintMain.hospitalGrade}</td>
				<td>${summaryInfo.area.name}</td>
				<td>${summaryInfo.auditAcceptance.policyNumber}</td>
				<td>${summaryInfo.reportRegistration.reportEmp}</td>
				<td>${summaryInfo.reportRegistration.disputeTime}</td>
				<td>${summaryInfo.complaintMain.patientName}</td>
				<td>${summaryInfo.complaintMain.patientMobile}</td>
				<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><td>
    				<a href="${ctx}/summaryinfo/summaryInfo/form?id=${summaryInfo.summaryId}">处理</a>
					<a href="${ctx}/summaryinfo/summaryInfo/delete?id=${summaryInfo.summaryId}" onclick="return confirmx('确认要删除该案件总结吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>