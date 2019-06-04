<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>纠纷调解管理</title>
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
		<li class="active"><a href="${ctx}/complaintmain/complaintMain/">纠纷调解列表</a></li>
		<shiro:hasPermission name="complaintmain:complaintMain:edit"><li><a href="${ctx}/complaintmain/complaintMain/form">纠纷调解添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintMain" action="${ctx}/complaintmain/complaintMain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>案件编号：</label>
				<form:input path="caseNumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>涉及医院：</label>
				<form:input path="involveHospital" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>涉及科室：</label>
				<form:input path="involveDepartment" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<th>患者姓名</th>
				<th>患者性别 字典</th>
				<th>涉及医院</th>
				<th>涉及科室</th>
				<th>涉及人员</th>
				<th>update_date</th>
				<shiro:hasPermission name="complaintmain:complaintMain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintMain">
			<tr>
				<td><a href="${ctx}/complaintmain/complaintMain/form?id=${complaintMain.id}">
					${complaintMain.caseNumber}
				</a></td>
				<td>
					${complaintMain.patientName}
				</td>
				<td>
					${complaintMain.patientSex}
				</td>
				<td>
					${complaintMain.involveHospital}
				</td>
				<td>
					${complaintMain.involveDepartment}
				</td>
				<td>
					${complaintMain.involveEmployee}
				</td>
				<td>
					<fmt:formatDate value="${complaintMain.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="complaintmain:complaintMain:edit"><td>
    				<a href="${ctx}/complaintmain/complaintMain/form?id=${complaintMain.id}">修改</a>
					<a href="${ctx}/complaintmain/complaintMain/delete?id=${complaintMain.id}" onclick="return confirmx('确认要删除该纠纷调解吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>