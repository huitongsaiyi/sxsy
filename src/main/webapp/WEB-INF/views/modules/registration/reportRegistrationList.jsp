<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报案信息管理</title>
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
		<li class="active"><a href="${ctx}/registration/reportRegistration/">报案信息列表</a></li>
<%--
		<shiro:hasPermission name="registration:reportRegistration:edit"><li><a href="${ctx}/registration/reportRegistration/form">报案信息添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>报案人姓名：</label>
				<form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label style="width: 100px;">患方联系方式：</label>
				<form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>报案日期：</label>
				<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="${reportRegistration.reportTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="button" class="btn btn-primary" type="reset" value="重置" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table  table-bordered table-condensed">
		<thead>
			<tr><th class="sort-column case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column involve_hospital" style="text-align: center;">涉及医院</th>
				<th class="sort-column b.hospital_grade" style="text-align: center;">医院等级</th>
				<th class="sort-column case_number" style="text-align: center;">所属地市</th>
				<th class="sort-column report_emp" style="text-align: center;">报案人姓名</th>
				<th class="sort-column dispute_time" style="text-align: center;">纠纷发生时间</th>
				<th class="sort-column patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column a.patient_mobile" style="text-align: center;">患方联系方式</th>
				<shiro:hasPermission name="registration:reportRegistration:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportRegistration">
			<tr ${reportRegistration.isMajor=='1' ? "style='background-color: red;'" : ""} >
				<td style="text-align: center;"><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}&type=view">
						${reportRegistration.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center;">
						${reportRegistration.complaintMain.hospital.name}
				</td>
				<td style="text-align: center;">
						${fns:getDictLabel(reportRegistration.complaintMain.hospitalGrade,"hospital_grade" ,"未知" )}
				</td>
				<td style="text-align: center;">
                        ${reportRegistration.complaintMain.hospital.area.name}
				</td>

				<td style="text-align: center;">
						${reportRegistration.reportEmp}
				</td>
				<td style="text-align: center;">
						${reportRegistration.disputeTime}
				</td>
				<td style="text-align: center;">
						${reportRegistration.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="registration:reportRegistration:edit"><td style="text-align: center;">
					<c:if test="${fns:getUser().loginName eq reportRegistration.complaintMain.act.assigneeName}">
						<a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">处理</a>
						<%--<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${reportRegistration.complaintMainId}&taskId=${reportRegistration.complaintMain.act.taskId}&module=badj&url1=/registration/reportRegistration/?repage">终止调解</a>--%>
					</c:if>
    				<a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>

