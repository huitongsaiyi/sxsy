<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成功管理</title>
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
		<li class="active"><a href="${ctx}/nestigateeividence/investigateEvidence/">调查取证列表</a></li>
		<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><li><a href="${ctx}/nestigateeividence/investigateEvidence/form">添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="investigateEvidence" action="${ctx}/nestigateeividence/investigateEvidence/" method="post" class="breadcrumb form-search">
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
				<%--<td>医方联系电话</td>--%>
				<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="investigateEvidence" varStatus="num">
			<tr>
				<td>
					${num.index+1}
				</td>
				<td>
					${investigateEvidence.complaintMain.caseNumber}
				</td>
				<td>
					${investigateEvidence.complaintMain.hospital.name}
				</td>
				<td>
					${investigateEvidence.complaintMain.hospitalGrade}
				</td>
				<td>
					${investigateEvidence.area.name}
				</td>
				<td>
					${investigateEvidence.auditAcceptance.policyNumber}
				</td>
				<td>
					${investigateEvidence.reportRegistration.reportEmp}
				</td>
				<td>
					${investigateEvidence.reportRegistration.disputeTime}
				</td>
				<td>
					${investigateEvidence.complaintMain.patientName}
				</td>
				<td>
					${investigateEvidence.complaintMain.patientMobile}
				</td>
				<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><td>
    				<a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.id}">修改</a>
					<a href="${ctx}/nestigateeividence/investigateEvidence/delete?id=${investigateEvidence.id}" onclick="return confirmx('确认要删除该成功吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>