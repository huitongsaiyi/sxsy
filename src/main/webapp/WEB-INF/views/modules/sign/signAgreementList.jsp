<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签署协议管理</title>
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
		<li class="active"><a href="${ctx}/sign/signAgreement/">签署协议列表</a></li>
<%--
		<shiro:hasPermission name="sign:signAgreement:edit"><li><a href="${ctx}/sign/signAgreement/form">签署协议添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="signAgreement" action="${ctx}/sign/signAgreement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>协议号：</label>
				<form:input path="agreementNumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>赔付时间：</label>
				<input name="beginCompensateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${signAgreement.beginCompensateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCompensateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${signAgreement.endCompensateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th class="sort-column case_number" style="text-align:center;">案件编号</th>
				<th class="sort-column b.involve_hospital" style="text-align:center;">涉及医院</th>
				<th class="sort-column b.hospital_grade" style="text-align:center;">医院等级</th>
				<th class="sort-column sa.name" style="text-align:center;">保单号</th>
				<th class="sort-column report_emp" style="text-align:center;">报案人姓名</th>
				<th class="sort-column dispute_time" style="text-align:center;">纠纷发生时间</th>
				<th class="sort-column b.patient_name" style="text-align:center;">患者姓名</th>
				<th class="sort-column r.patient_mobile" style="text-align:center;">患方联系方式</th>
				<th class="sort-column a.agreement_number" style="text-align:center;">协议号</th>
				<th class="sort-column a.ratify_accord" style="text-align:center;">签署协议/判决时间</th>
				<th class="sort-column a.agreement_amount" style="text-align:center;">协议金额</th>
				<th class="sort-column a.insurance_amount" style="text-align:center;">保险金额</th>
				<%--<th class="sort-column a.compensate_time" style="text-align:center;">赔付时间</th>--%>
				<th class="sort-column a.create_date" style="text-align:center;">创建时间</th>
				<th class="sort-column a.update_date" style="text-align:center;">更新时间</th>
				<shiro:hasPermission name="sign:signAgreement:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="signAgreement">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/sign/signAgreement/form?id=${signAgreement.signAgreementId}&type=view">
						${signAgreement.complaintMain.caseNumber}
				</a></td>
				<td style="text-align:center;">
						${signAgreement.complaintMain.hospital.name}
				</td>
				<td style="text-align:center;">
					<c:choose>
						<c:when test="${signAgreement.complaintMain.hospitalGrade=='1'}">
							特等
						</c:when>
						<c:when test="${signAgreement.complaintMain.hospitalGrade=='2'}">
							甲等
						</c:when>
						<c:when test="${signAgreement.complaintMain.hospitalGrade=='3'}">
							乙等
						</c:when>
						<c:when test="${signAgreement.complaintMain.hospitalGrade=='4'}">
							丙等
						</c:when>
						<c:otherwise>
							无
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align:center;">
						${signAgreement.auditAcceptance.policyNumber}
				</td>
				<td style="text-align:center;">
						${signAgreement.reportRegistration.reportEmp}
				</td>
				<td style="text-align:center;">
						${signAgreement.reportRegistration.disputeTime}
				</td>
				<td style="text-align:center;">
						${signAgreement.complaintMain.patientName}
				</td>
				<td style="text-align:center;">
						${signAgreement.reportRegistration.patientMobile}
				</td>
				<td style="text-align:center;">
						${signAgreement.agreementNumber}
				</td>
				<td style="text-align:center;">
						${signAgreement.ratifyAccord}
				</td>
				<td style="text-align:center;">
						${signAgreement.agreementAmount}
				</td>
				<td style="text-align:center;">
						${signAgreement.insuranceAmount}
				</td>
				<%--<td style="text-align:center;">--%>
						<%--${signAgreement.compensateTime}--%>
				<%--</td>--%>
				<td style="text-align:center;">
					<fmt:formatDate value="${signAgreement.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${signAgreement.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sign:signAgreement:edit"><td style="text-align:center;">
					<c:if test="${fns:getUser().loginName eq signAgreement.complaintMain.act.assigneeName}">
						<a href="${ctx}/sign/signAgreement/form?id=${signAgreement.signAgreementId}">处理</a>
						<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${signAgreement.complaintMainId}&taskId=${signAgreement.complaintMain.act.taskId}&module=badj&url9=/sign/signAgreement/?repage">终止调解</a>
					</c:if>
					<a href="${ctx}/sign/signAgreement/form?id=${signAgreement.signAgreementId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>