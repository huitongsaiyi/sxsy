<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
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
		<li class="active"><a href="${ctx}/complaint/complaintInfo/">投诉接待列表</a></li>
		<shiro:hasPermission name="complaint:complaintInfo:edit"><li><a href="${ctx}/complaint/complaintInfo/form">投诉接待添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintInfo" action="${ctx}/complaint/complaintInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>案件编号：</label>
				<form:input path="caseNumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客姓名：</label>
				<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客电话：</label>
				<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>涉及医院：</label>
				<sys:treeselect id="involveHospital" name="involveHospital" value="${complaintInfo.involveHospital}" labelName="office.name" labelValue="${complaintInfo.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>涉及科室：</label>
				<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="office.name1" labelValue="${complaintInfo.office.name1}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
				<th>访客姓名</th>
				<th>访客电话</th>
				<th>与患者关系</th>
				<th>患者姓名</th>
				<th>患者性别</th>
				<th>患者年龄</th>
				<th>来访人数</th>
				<th>涉及医院</th>
				<th>涉及科室</th>
				<th>涉及人员</th>
				<th>投诉纠纷概要</th>
				<shiro:hasPermission name="complaint:complaintInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintInfo">
			<tr>
				<td><a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}">
					${complaintInfo.caseNumber}
				</a></td>
				<td>
					${complaintInfo.visitorName}
				</td>
				<td>
					${complaintInfo.visitorMobile}
				</td>
				<td>
					${complaintInfo.patientRelation}
				</td>
				<td>
					${complaintInfo.patientName}
				</td>
				<td>
					${complaintInfo.patientSex}
				</td>
				<td>
					${complaintInfo.patientAge}
				</td>
				<td>
					${complaintInfo.visitorNumber}
				</td>
				<td>
					${complaintInfo.office.name}
				</td>
				<td>
					${complaintInfo.office.name1}
				</td>
				<td>
					${complaintInfo.involveEmployee}
				</td>
				<td>
					${complaintInfo.summaryOfDisputes}
				</td>
				<shiro:hasPermission name="complaint:complaintInfo:edit"><td>
    				<a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}">修改</a>
					<a href="${ctx}/complaint/complaintInfo/delete?id=${complaintInfo.complaintId}" onclick="return confirmx('确认要删除该投诉接待吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>