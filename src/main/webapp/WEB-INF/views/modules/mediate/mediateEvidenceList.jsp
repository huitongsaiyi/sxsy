<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质证调解管理</title>
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
		<li class="active"><a href="${ctx}/mediate/mediateEvidence/">质证调解列表</a></li>
		<shiro:hasPermission name="mediate:mediateEvidence:edit"><li><a href="${ctx}/mediate/mediateEvidence/form">质证调解添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mediateEvidence" action="${ctx}/mediate/mediateEvidence/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>患方：</label>
				<form:input path="patient" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
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
				<shiro:hasPermission name="mediate:mediateEvidence:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mediateEvidence">
			<tr>
				<td>
						${num.index+1}
				</td>
				<td>
						${mediateEvidence.complaintMain.caseNumber}
				</td>
				<td>
						${mediateEvidence.complaintMain.hospital.name}
				</td>
				<td>
						${mediateEvidence.complaintMain.hospitalGrade}
				</td>
				<td>
						${mediateEvidence.areaName}
				</td>
				<td>
						${mediateEvidence.auditAcceptance.policyNumber}
				</td>
				<td>
						${mediateEvidence.reportRegistration.reportEmp}
				</td>
				<td>
						${mediateEvidence.reportRegistration.disputeTime}
				</td>
				<td>
						${mediateEvidence.complaintMain.patientName}
				</td>
				<td>
						${mediateEvidence.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="mediate:mediateEvidence:edit"><td>
    				<a href="${ctx}/mediate/mediateEvidence/form?id=${mediateEvidence.id}">处理</a>
					<a href="${ctx}/mediate/mediateEvidence/form?id=${mediateEvidence.id}type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>