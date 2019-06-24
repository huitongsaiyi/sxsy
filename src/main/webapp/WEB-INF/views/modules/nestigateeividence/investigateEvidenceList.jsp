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
<%--
		<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><li><a href="${ctx}/nestigateeividence/investigateEvidence/form">添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="investigateEvidence" action="${ctx}/nestigateeividence/investigateEvidence/" method="post" class="breadcrumb form-search">
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
			<li><label>处理日期：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${investigateEvidence.startTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/> -
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${investigateEvidence.endTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
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
				<th class="sort-column case_number">案件编号</th>
				<th class="sort-column involve_hospital">涉及医院</th>
				<th class="sort-column hospital_grade">医院等级</th>
				<th class="sort-column sa.name">所属城市</th>
				<th class="sort-column policy_number">保单号</th>
				<th class="sort-column report_emp">报案人姓名</th>
				<th class="sort-column dispute_time">纠纷发生时间</th>
				<th class="sort-column patient_name">患者姓名</th>
				<th class="sort-column r.patient_mobile">患者联系方式</th>
				<%--<td>医方联系电话</td>--%>
				<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="investigateEvidence" varStatus="num">
			<tr>
				<td>
					${investigateEvidence.complaintMain.caseNumber}
				</td>
				<td>
					${investigateEvidence.complaintMain.hospital.name}
				</td>
				<td>
					<c:choose>
						<c:when test="${investigateEvidence.complaintMain.hospitalGrade=='1'}">
							特等
						</c:when>
						<c:when test="${investigateEvidence.complaintMain.hospitalGrade=='2'}">
							甲等
						</c:when>
						<c:when test="${investigateEvidence.complaintMain.hospitalGrade=='3'}">
							乙等
						</c:when>
						<c:when test="${investigateEvidence.complaintMain.hospitalGrade=='4'}">
							丙等
						</c:when>
						<c:otherwise>
							无
						</c:otherwise>
					</c:choose>
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
					${investigateEvidence.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><td>
    				<a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.investigateEvidenceId}">处理</a>
    				<a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.investigateEvidenceId}&type=view">详情</a>
					<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${investigateEvidence.complaintMainId}&module=badj&url3=/nestigateeividence/investigateEvidence/?repage">终止调解</a>
<%--
					<a href="${ctx}/nestigateeividence/investigateEvidence/delete?id=${investigateEvidence.id}" onclick="return confirmx('确认要删除该成功吗？', this.href)">删除</a>
--%>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
</html>