<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件反馈管理</title>
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
		<li class="active"><a href="${ctx}/casefeedback/caseFeedback/">案件反馈列表</a></li>
		<%--<shiro:hasPermission name="casefeedback:caseFeedback:edit">--%>
			<%--<li><a href="${ctx}/casefeedback/caseFeedback/form">案件反馈添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="caseFeedback" action="${ctx}/casefeedback/caseFeedback/" method="post" class="breadcrumb form-search">
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column involve_hospital" style="text-align: center;">涉及医院</th>
				<th class="sort-column hospital_grade" style="text-align: center;">医院等级</th>
				<th class="sort-column case_number" style="text-align: center;">所属地市</th>
				<th class="sort-column report_emp" style="text-align: center;">报案人姓名</th>
				<th class="sort-column dispute_time" style="text-align: center;">纠纷发生时间</th>
				<th class="sort-column patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column r.patient_mobile" style="text-align: center;">患方联系方式</th>
				<shiro:hasPermission name="casefeedback:caseFeedback:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="caseFeedback">
			<tr>
				<td style="text-align: center;"><a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.feedbackId}&type=view">
						${caseFeedback.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center;">
						${caseFeedback.complaintMain.hospital.name}
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${caseFeedback.complaintMain.hospitalGrade=='1'}">
							特等
						</c:when>
						<c:when test="${caseFeedback.complaintMain.hospitalGrade=='2'}">
							甲等
						</c:when>
						<c:when test="${caseFeedback.complaintMain.hospitalGrade=='3'}">
							乙等
						</c:when>
						<c:when test="${caseFeedback.complaintMain.hospitalGrade=='4'}">
							丙等
						</c:when>
						<c:otherwise>
							无
						</c:otherwise>
					</c:choose>


				</td>
				<td style="text-align: center;">
						${caseFeedback.area.name}
				</td>

				<td style="text-align: center;">
						${caseFeedback.reportRegistration.reportEmp}
				</td>
				<td style="text-align: center;">
						${caseFeedback.reportRegistration.disputeTime}
				</td>
				<td style="text-align: center;">
						${caseFeedback.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${caseFeedback.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="casefeedback:caseFeedback:edit"><td style="text-align: center;">
    				<a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.feedbackId}">反馈</a>
					<a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.feedbackId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>