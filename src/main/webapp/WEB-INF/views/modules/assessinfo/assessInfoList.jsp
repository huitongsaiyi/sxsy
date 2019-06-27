<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件评价管理</title>
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
		<li class="active"><a href="${ctx}/assessinfo/assessInfo/">案件评价列表</a></li>

		<%--<shiro:hasPermission name="assessinfo:assessInfo:edit"><li><a href="${ctx}/assessinfo/assessInfo/form">案件评价添加</a></li></shiro:hasPermission>--%>

	</ul>
	<form:form id="searchForm" modelAttribute="assessInfo" action="${ctx}/assessinfo/assessInfo/" method="post" class="breadcrumb form-search">
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
				<th class="sort-column r1.patient_mobile" style="text-align: center;">患方联系方式</th>
				<shiro:hasPermission name="assessinfo:assessInfo:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assessInfo">
			<tr>
				<td style="text-align: center;"><a href="${ctx}/casefeedback/caseFeedback/form?id=${assessInfo.assessId}">
						${assessInfo.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center;">
						${assessInfo.complaintMain.hospital.name}
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${assessInfo.complaintMain.hospitalGrade=='1'}">
							特等
						</c:when>
						<c:when test="${assessInfo.complaintMain.hospitalGrade=='2'}">
							甲等
						</c:when>
						<c:when test="${assessInfo.complaintMain.hospitalGrade=='3'}">
							乙等
						</c:when>
						<c:when test="${assessInfo.complaintMain.hospitalGrade=='4'}">
							丙等
						</c:when>
						<c:otherwise>
							无
						</c:otherwise>
					</c:choose>


				</td>
				<td style="text-align: center;">
						${assessInfo.area.name}
				</td>

				<td style="text-align: center;">
						${assessInfo.reportRegistration.reportEmp}
				</td>
				<td style="text-align: center;">
						${assessInfo.reportRegistration.disputeTime}
				</td>
				<td style="text-align: center;">
						${assessInfo.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${assessInfo.reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="assessinfo:assessInfo:edit"><td style="text-align: center;">
    				<a href="${ctx}/assessinfo/assessInfo/form?id=${assessInfo.assessId}">处理</a>
					<a href="${ctx}/assessinfo/assessInfo/form?id=${assessInfo.assessId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>