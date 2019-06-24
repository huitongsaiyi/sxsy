<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>达成调解管理</title>
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
		<li class="active"><a href="${ctx}/reachmediate/reachMediate/">达成调解列表</a></li>
<%--
		<shiro:hasPermission name="reachmediate:reachMediate:edit"><li><a href="${ctx}/reachmediate/reachMediate/form">达成调解添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="reachMediate" action="${ctx}/reachmediate/reachMediate/" method="post" class="breadcrumb form-search">
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
				<th class="sort-column policy_number">保单号</th>
				<th class="sort-column report_emp">报案人姓名</th>
				<th class="sort-column dispute_time">纠纷发生时间</th>
				<th class="sort-column patient_name">患者姓名</th>
				<th class="sort-column r1.patient_mobile">患者联系电话</th>
				<shiro:hasPermission name="reachmediate:reachMediate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reachMediate">
			<tr>
				<td><a href="${ctx}/perform/performAgreement/form?id=${reachMediate.reachMediateId}">
						${reachMediate.complaintMain.caseNumber}
				</a></td>
				<td>
						${reachMediate.complaintMain.hospital.name}
				</td>
				<td>
					<c:choose>
						<c:when test="${reachMediate.complaintMain.hospitalGrade=='1'}">
							特等
						</c:when>
						<c:when test="${reachMediate.complaintMain.hospitalGrade=='2'}">
							甲等
						</c:when>
						<c:when test="${reachMediate.complaintMain.hospitalGrade=='3'}">
							乙等
						</c:when>
						<c:when test="${reachMediate.complaintMain.hospitalGrade=='4'}">
							丙等
						</c:when>
						<c:otherwise>
							无
						</c:otherwise>
					</c:choose>
				</td>
				<td>
						${reachMediate.area.name}
				</td>
				<td>
						${reachMediate.auditAcceptance.policyNumber}
				</td>
				<td>
						${reachMediate.reportRegistration.reportEmp}
				</td>
				<td>
						${reachMediate.reportRegistration.disputeTime}
				</td>
				<td>
						${reachMediate.complaintMain.patientName}
				</td>
				<td>
						${reachMediate.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="reachmediate:reachMediate:edit"><td>
    				<a href="${ctx}/reachmediate/reachMediate/form?id=${reachMediate.reachMediateId}">处理</a>
					<a href="${ctx}/reachmediate/reachMediate/form?id=${reachMediate.reachMediateId}&type=view">详情</a>
					<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${reachMediate.complaintMainId}&module=badj&url8=/reachmediate/reachMediate/?repage">终止调解</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>