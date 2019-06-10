<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核受理管理</title>
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
		<li class="active"><a href="${ctx}/auditacceptance/auditAcceptance/">审核受理列表</a></li>
<%--
		<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><li><a href="${ctx}/auditacceptance/auditAcceptance/form">审核受理添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="auditAcceptance" action="${ctx}/auditacceptance/auditAcceptance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>起保日期：</label>
				<input name="beginGuaranteeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${auditAcceptance.beginGuaranteeTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endGuaranteeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${auditAcceptance.endGuaranteeTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>保险公司：</label>
				<form:input path="insuranceCompany" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>案件编号</th>
				<th>医疗机构名称</th>
				<th>患者姓名</th>
				<th>案件来源</th>
				<th>起保日期</th>
				<th>保险公司</th>
				<th>保单号</th>
				<th>诊疗方式</th>
				<th>治疗结果</th>
				<th>患方受理通知书</th>
				<th>医方受理通知书</th>
				<th>修改时间</th>
				<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="auditAcceptance">
			<tr>
				<td><a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.auditAcceptanceId}">
					${auditAcceptance.complaintMain.caseNumber}
				</a></td>
				<td>
					${auditAcceptance.complaintMain.hospital.name}
				</td>
				<td>
					${auditAcceptance.complaintMain.patientName}
				</td>
				<td>
					${auditAcceptance.caseSource}
				</td>
				<td>
					${auditAcceptance.guaranteeTime}
				</td>
				<td>
					${auditAcceptance.insuranceCompany}
				</td>
				<td>
					${auditAcceptance.policyNumber}
				</td>
				<td>
					${auditAcceptance.diagnosisMode}
				</td>
				<td>
					${auditAcceptance.treatmentOutcome}
				</td>
				<td>
					${auditAcceptance.diagnosisMode}
				</td>
				<td>
					${auditAcceptance.diagnosisMode}
				</td>
				<td>
					<fmt:formatDate value="${auditAcceptance.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><td>
    				<a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.auditAcceptanceId}">处理</a>
    				<a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.auditAcceptanceId}&type=view">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>