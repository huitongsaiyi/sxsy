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
		<shiro:hasPermission name="sign:signAgreement:edit"><li><a href="${ctx}/sign/signAgreement/form">签署协议添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="signAgreement" action="${ctx}/sign/signAgreement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>案件编号</th>
				<th>涉及医院</th>
				<th>机构等级</th>
				<th>保单号</th>
				<th>报案人姓名</th>
				<th>纠纷发生时间</th>
				<th>患者姓名</th>
				<th>患方联系方式</th>
				<th>协议号</th>
				<th>签署协议/判决时间</th>
				<th>协议金额</th>
				<th>保险金额</th>
				<th>赔付时间</th>
				<th>create_date</th>
				<th>update_date</th>
				<shiro:hasPermission name="sign:signAgreement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="signAgreement">
			<tr>
				<td><a href="${ctx}/sign/signAgreement/form?id=${signAgreement.signAgreementId}">
						${signAgreement.complaintMain.caseNumber}
				</a></td>
				<td>
						${signAgreement.complaintMain.hospital.name}
				</td>
				<td>
						${signAgreement.complaintMain.hospitalGrade}
				</td>
				<td>
						${signAgreement.auditAcceptance.policyNumber}
				</td>
				<td>
						${signAgreement.reportRegistration.reportEmp}
				</td>
				<td>
						${signAgreement.reportRegistration.disputeTime}
				</td>
				<td>
						${signAgreement.complaintMain.patientName}
				</td>
				<td>
						${signAgreement.reportRegistration.patientMobile}
				</td>
				<td>
						${signAgreement.agreementNumber}
				</td>
				<td>
						${signAgreement.ratifyAccord}
				</td>
				<td>
						${signAgreement.agreementAmount}
				</td>
				<td>
						${signAgreement.insuranceAmount}
				</td>
				<td>
						${signAgreement.compensateTime}
				</td>
				<td>
					<fmt:formatDate value="${signAgreement.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${signAgreement.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sign:signAgreement:edit"><td>
    				<a href="${ctx}/sign/signAgreement/form?id=${signAgreement.signAgreementId}">修改</a>
    				<a href="${ctx}/sign/signAgreement/form?id=${signAgreement.signAgreementId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>