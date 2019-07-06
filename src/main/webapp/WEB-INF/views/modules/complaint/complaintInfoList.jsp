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
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
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
				<sys:treeselect id="involveHospital" name="involveHospital" value="${complaintInfo.involveHospital}" labelName="hospitalName" labelValue="${complaintInfo.hospitalName}"
					title="部门" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>涉及科室：</label>
				<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="departmentName" labelValue="${complaintInfo.departmentName}"
					title="部门" url="/sys/office/treeData?type=2&officeType=2" isAll="true" pid="involveHospital" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>接待日期：</label>
				<input name="receptionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${complaintInfo.receptionDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 0:0',isShowClear:true});"/>
				　--　
				<input id="receptionEndDate" name="receptionEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					   value="${complaintInfo.receptionEndDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59'});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number">案件编号</th>
				<th class="sort-column visitor_name">访客姓名</th>
				<th class="sort-column visitor_mobile">访客电话</th>
				<th class="sort-column patient_relation">与患者关系</th>
				<th class="sort-column patient_name">患者姓名</th>
				<th class="sort-column patient_sex">患者性别</th>
				<th class="sort-column patient_age">患者年龄</th>
				<th class="sort-column visitor_number">来访人数</th>
				<th class="sort-column involve_hospital">涉及医院</th>
				<th class="sort-column involve_department">涉及科室</th>
				<th class="sort-column involve_employee">涉及人员</th>
				<th class="sort-column summary_of_disputes">投诉纠纷概要</th>
				<th class="sort-column is_mediate">是否进入医调委调解</th>
				<shiro:hasPermission name="complaint:complaintInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintInfo">
			<tr>
				<td>
					${complaintInfo.caseNumber}
				</td>
				<td>
					${complaintInfo.visitorName}
				</td>
				<td>
					${complaintInfo.visitorMobile}
				</td>
				<td>
					${complaintInfo.relationName}
				</td>
				<td>
					${complaintInfo.patientName}
				</td>
				<td>
					${complaintInfo.sexName}
				</td>
				<td>
					${complaintInfo.patientAge}
				</td>
				<td>
					${complaintInfo.visitorNumber}
				</td>
				<td>
					${complaintInfo.hospitalName}
				</td>
				<td>
					${complaintInfo.departmentName}
				</td>
				<td>
					${complaintInfo.employeeName}
				</td>
				<td>
					${complaintInfo.summaryOfDisputes}
				</td>
				<td>
					<c:if test="${complaintInfo.isMediate eq 1}">
						是
					</c:if>
					<c:if test="${complaintInfo.isMediate ne 1}">
						否
					</c:if>
				</td>
				<shiro:hasPermission name="complaint:complaintInfo:edit"><td>
					<c:if test="${complaintInfo.isMediate ne 1}">
    				<a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}">处理</a>
					<a href="${ctx}/complaint/complaintInfo/delete?id=${complaintInfo.complaintId}" onclick="return confirmx('确认要删除该投诉接待吗？', this.href)">删除</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>