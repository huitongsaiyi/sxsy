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
		<shiro:hasPermission name="registration:reportRegistration:edit"><li><a href="${ctx}/registration/reportRegistration/form">报案信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报案人姓名：</label>
				<form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>患方联系方式：</label>
				<form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>报案日期：</label>
				<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportRegistration.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>案件编号</th>
				<th>涉及医院</th>
				<th>医院等级</th>
				<th>所属地市</th>
				<th>报案人姓名</th>
				<th>纠纷发生时间</th>
				<th>患者姓名</th>
				<th>患方联系方式</th>
				<shiro:hasPermission name="registration:reportRegistration:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportRegistration">
			<tr>
				<td><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">
					${reportRegistration.complaintMain.caseNumber}
				</a></td>
				<td>
					${reportRegistration.complaintMain.involveHospital}
				</td>
				<td>
					${reportRegistration.complaintMain.hospitalGrade}
				</td>
				<td>
					${reportRegistration.areaName}
				</td>

				<td>
						${reportRegistration.reportEmp}
				</td>
				<td>
					${reportRegistration.disputeTime}
				</td>
				<td>
						${reportRegistration.complaintMain.patientName}
				</td>
				<td>
					${reportRegistration.patientMobile}
				</td>
				<shiro:hasPermission name="registration:reportRegistration:edit"><td>
    				<a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>